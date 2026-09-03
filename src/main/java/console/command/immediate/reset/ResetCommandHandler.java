package console.command.immediate.reset;

import console.command.CommandHandler;
import console.command.ConsoleCommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

@ConsoleCommandHandler
public final class ResetCommandHandler extends CommandHandler {
    @Override
    protected String getCommandHeader() {
        return ResetCommand.COMMAND_HEADER;
    }

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                default -> { return ParsingResult.malformedCommand(tokens); }
            }
        }
        return Optional.empty();
    }
    
    @Override
    public ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        ResetCommandBuilder ecb = ResetCommand.builder();
        for (TokenType tt : tokens) {
            switch(tt) {
                case TokenType.Command ignored -> {}
                default -> { return ParsingResult.malformedCommand(this.getCommandHeader(), tokens); }
            }
        }
        return ParsingResult.success(ecb.build());
    }

    @Override
    protected List<TokenOrder> getTokenOrder() {
        return List.of(
                new TokenOrder.FirstOrSecond(new TokenType.Command(null))
        );
    }
}
