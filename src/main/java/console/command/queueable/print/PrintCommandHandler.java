package console.command.queueable.print;

import console.command.CommandHandler;
import console.command.ConsoleCommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

@ConsoleCommandHandler
public final class PrintCommandHandler extends CommandHandler {
    
    @Override
    protected String getCommandHeader() {
        return PrintCommand.COMMAND_HEADER;
    }

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command tc -> {}
                case TokenType.If ti -> {}
                default -> ParsingResult.malformedCommand(tokens);
            }
        }
        return Optional.empty();
    }

    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        PrintCommandBuilder scb = PrintCommand.builder();
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.If ti -> { scb.setHasIf(true); }
                default -> {}
            }
        }
        return ParsingResult.success(scb.build());
    }

    @Override
    protected List<TokenOrder> getTokenOrder() {
        return List.of(
                new TokenOrder.FirstOrSecond(new TokenType.Command(null)),
                new TokenOrder.OptativeFirstOrSecond(new TokenType.If())
        );
    }

}
