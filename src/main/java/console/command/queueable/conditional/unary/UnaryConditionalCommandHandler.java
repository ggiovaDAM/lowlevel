package console.command.queueable.conditional.unary;

import console.command.CommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

public abstract class UnaryConditionalCommandHandler
                      extends CommandHandler {
    protected abstract UnaryConditionalCommandBuilder<?, ?> builder();

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command tc -> {}
                case TokenType.Value tv -> {}
                default -> ParsingResult.malformedCommand(tokens);
            }
        }
        return Optional.empty();
    }

    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        UnaryConditionalCommandBuilder<?, ?> scb = this.builder();
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.Value(int value) -> { scb.setCycle(value, parserContext.cycle().getCycle()); }
                default -> {}
            }
        }
        return ParsingResult.success(scb.build());
    }

    @Override
    protected List<TokenOrder> getTokenOrder() {
        return List.of(
                new TokenOrder.FirstOrSecond(new TokenType.Command(null)),
                new TokenOrder.Optative(new TokenType.Value(0))
        );
    }
}
