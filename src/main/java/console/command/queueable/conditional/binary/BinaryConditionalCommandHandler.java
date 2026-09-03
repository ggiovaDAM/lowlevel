package console.command.queueable.conditional.binary;

import console.command.CommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

public abstract class BinaryConditionalCommandHandler
                      extends CommandHandler {
    protected abstract BinaryConditionalCommandBuilder<?, ?> builder();

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command tc -> {}
                case TokenType.Bool tb -> {}
                case TokenType.Value tb -> {}
                default -> ParsingResult.malformedCommand(tokens);
            }
        }
        return Optional.empty();
    }

    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        BinaryConditionalCommandBuilder<?, ?> scb = this.builder();
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.Bool(boolean bool) -> { scb.setBool(bool); }
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
                new TokenOrder.Required(new TokenType.Bool(false)),
                new TokenOrder.Optative(new TokenType.Value(0))
        );
    }
}
