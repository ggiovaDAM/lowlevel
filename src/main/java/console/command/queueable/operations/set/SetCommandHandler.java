package console.command.queueable.operations.set;

import console.command.CommandHandler;
import console.command.ConsoleCommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

@ConsoleCommandHandler
public final class SetCommandHandler extends CommandHandler {
    
    @Override
    protected String getCommandHeader() {
        return SetCommand.COMMAND_HEADER;
    }

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command tc -> {}
                case TokenType.If ti -> {}
                case TokenType.RegistryKey tr -> {}
                case TokenType.Value tv -> {}
                default -> ParsingResult.malformedCommand(tokens);
            }
        }
        return Optional.empty();
    }

    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        SetCommandBuilder scb = SetCommand.builder();
        int valueStage = 0;
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.If ti -> { scb.setHasIf(true); }
                case TokenType.RegistryKey(String key) -> { scb.setRegistry(key); }
                case TokenType.Value(int value) -> {
                    if (valueStage == 0) { scb.setValue(value); }
                    else {
                        if (value <= 0) {
                            return ParsingResult.illegalValue(this.getCommandHeader(), value, "Cycle > 0");
                        }
                        scb.setCycle(value)
                           .setLastCycle(parserContext.cycle().getCycle());
                    }
                    valueStage++;
                }
                default -> {}
            }
        }
        return ParsingResult.success(scb.build());
    }

    @Override
    protected List<TokenOrder> getTokenOrder() {
        return List.of(
                new TokenOrder.FirstOrSecond(new TokenType.Command(null)),
                new TokenOrder.OptativeFirstOrSecond(new TokenType.If()),
                new TokenOrder.Required(new TokenType.RegistryKey(null)),
                new TokenOrder.Required(new TokenType.Value(0)),
                new TokenOrder.Optative(new TokenType.Value(0))
        );
    }

}
