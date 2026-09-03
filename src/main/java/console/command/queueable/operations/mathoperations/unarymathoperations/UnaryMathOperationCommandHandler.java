package console.command.queueable.operations.mathoperations.unarymathoperations;

import console.command.CommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

public abstract class UnaryMathOperationCommandHandler
                extends CommandHandler {
    protected abstract UnaryMathOperationCommandBuilder<?, ?> builder();

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt: tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.If ignored -> {}
                case TokenType.RegistryKey ignored -> {}
                case TokenType.Value ignored -> {}
                default -> { return ParsingResult.malformedCommand(tokens); }
            }
        }
        return Optional.empty();
    }

    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        UnaryMathOperationCommandBuilder<?, ?> builder = this.builder();
        
        int registryStage = 0;
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.If ti -> { builder.setHasIf(true); }
                case TokenType.RegistryKey(String key) -> {
                    if (registryStage == 0) { builder.setSource(key); }
                    else                    { builder.setDestination(key); }
                    registryStage++;
                }
                case TokenType.Value tv -> {
                    builder.setCycle(tv.value())
                           .setLastCycle(parserContext.cycle().getCycle());
                }
                default -> {}
            }
        }
        return ParsingResult.success(builder.build());
    }

    @Override
    protected List<TokenOrder> getTokenOrder() {
        return List.of(
                new TokenOrder.FirstOrSecond(new TokenType.Command(null)),
                new TokenOrder.OptativeFirstOrSecond(new TokenType.If()),
                new TokenOrder.Required(new TokenType.RegistryKey(null)),
                new TokenOrder.Optative(new TokenType.RegistryKey(null)),
                new TokenOrder.Optative(new TokenType.Value(0))
        );
    }
}
