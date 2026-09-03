package console.command;

import console.type.TokenType;
import org.reflections.Reflections;

import java.util.List;
import java.util.Optional;

public final class CommandParser {
    private static CommandHandler HEAD;

    static {
        Reflections reflections = new Reflections("console");
        
        List<CommandHandler> handlers = reflections.getTypesAnnotatedWith(ConsoleCommandHandler.class)
                .stream()
                .map(c -> {
                    try {
                        return (CommandHandler) c.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to instantiate " + c.getName(), e);
                    }
                })
                .toList();
        
        CommandParser.HEAD = handlers.getFirst();
        
        handlers.stream()
                .skip(1)
                .reduce(HEAD, CommandHandler::setNext);
    }
    
    private static CommandParser instance = null;
    
    private CommandParser() {}
    
    public static CommandParser getInstance() {
        if (instance == null) {
            instance = new CommandParser();
        }
        return instance;
    }
    
    public ParsingResult parse(String input, ParserContext parserContext) {
        return this.parse(
                input.trim()
                     .toUpperCase()
                     .split(" "),
                parserContext
        );
    }
    
    private ParsingResult parse(String[] input, ParserContext parserContext) {
        List<TokenType> tokens = java.util.Arrays.stream(input)
                        .map(TokenType::getType)
                        .toList();
        
        Optional<ParsingResult> filtered = this.filterMalformedSyntax(tokens);
        if (filtered.isPresent()) { return filtered.get(); }
        
        return HEAD.handle(tokens, parserContext);
    }
    
    private Optional<ParsingResult> filterMalformedSyntax(List<TokenType> tokens) {
        int ifIndex = -1;
        int commandIndex = -1;
        for (int ii = 0; ii < tokens.size(); ii++) {
            switch (tokens.get(ii)) {
                case TokenType.Invalid(String msg) -> {
                    return Optional.of(new ParsingResult.Failure(msg));
                }
                case TokenType.Command tc -> {
                    if (commandIndex != -1 || ii > 1) {
                        return ParsingResult.malformedCommand(tokens);
                    }
                    commandIndex = ii;
                }
                case TokenType.If() -> {
                    if (ifIndex != -1 || ii > 1) {
                        return ParsingResult.malformedCommand(tokens);
                    }
                    ifIndex = ii;
                }
                default -> {}
            }
        }
        return Optional.empty();
    }
}
