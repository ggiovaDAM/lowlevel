package console.command;

import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

public abstract class CommandHandler {
    protected CommandHandler next = null;
    
    public CommandHandler setNext(CommandHandler next) {
        this.next = next;
        return this.next;
    }
    
    private final ParsingResult runNext(List<TokenType> tokens, ParserContext parserContext) {
        if (this.next != null) {
            return this.next.handle(tokens, parserContext);
        }
        return ParsingResult.malformedCommand(tokens).get();
    }
    
    public final ParsingResult handle(List<TokenType> tokens, ParserContext parserContext) {
        if (! this.claims(tokens)) { return this.runNext(tokens, parserContext); }

        Optional<ParsingResult> validation = this.validate(tokens);
        if (validation.isPresent()) { return validation.get(); }
        
        Optional<ParsingResult> orderValidation = this.validateOrder(tokens);
        if (orderValidation.isPresent()) { return orderValidation.get(); }
        
        return this.build(tokens, parserContext);
    }
    
    protected final boolean claims(List<TokenType> tokens) {
        return tokens.stream().anyMatch(t -> t instanceof TokenType.Command c && c.command().equals(this.getCommandHeader()));
    }
    
    protected abstract String getCommandHeader();

    protected final Optional<ParsingResult> validateOrder(List<TokenType> tokens) {
        List<TokenOrder> grammar = this.getTokenOrder();
        int tokenIndex = 0;
        
        for (TokenOrder rule : grammar) {
            switch (rule) {
                case TokenOrder.Required(TokenType expected) -> {
                    if (tokenIndex >= tokens.size()) {
                        return ParsingResult.malformedCommand(tokens);
                    }
                    if (! matches(tokens.get(tokenIndex), expected)) {
                        return ParsingResult.malformedCommand(tokens);
                    }
                    tokenIndex++;
                }
                case TokenOrder.Optative(TokenType expected) -> {
                    if (tokenIndex < tokens.size() && matches(tokens.get(tokenIndex), expected)) {
                        tokenIndex++;
                    }
                }
                case TokenOrder.FirstOrSecond(TokenType expected) -> {
                    if (tokenIndex < tokens.size() && matches(tokens.get(tokenIndex), expected)) {
                        tokenIndex++;
                    } else if (tokenIndex + 1 < tokens.size() && matches(tokens.get(tokenIndex + 1), expected)) {
                        tokenIndex += 2;
                    } else {
                        return ParsingResult.malformedCommand(tokens);
                    }
                }
                case TokenOrder.OptativeFirstOrSecond(TokenType expected) -> {
                    if (tokenIndex < tokens.size() && matches(tokens.get(tokenIndex), expected)) {
                        tokenIndex++;
                    } else if (tokenIndex + 1 < tokens.size() && matches(tokens.get(tokenIndex + 1), expected)) {
                        tokenIndex += 2;
                    }
                }
                case TokenOrder.OneOrMore(TokenType expected) -> {
                    if (tokenIndex >= tokens.size() || !matches(tokens.get(tokenIndex), expected)) {
                        return ParsingResult.malformedCommand(tokens);
                    }
                    while (tokenIndex < tokens.size() && matches(tokens.get(tokenIndex), expected)) {
                        tokenIndex++;
                    }
                }
            }
        }
        
        if (tokenIndex < tokens.size()) {
            return ParsingResult.malformedCommand(tokens);
        }
        return Optional.empty();
    }
    
    private boolean matches(TokenType token, TokenType expected) {
        return token.getClass().equals(expected.getClass());
    }
    
    protected abstract List<TokenOrder> getTokenOrder();
    
    protected abstract Optional<ParsingResult> validate(List<TokenType> tokens);
    
    protected abstract ParsingResult build(List<TokenType> tokens, ParserContext parserContext);
}
