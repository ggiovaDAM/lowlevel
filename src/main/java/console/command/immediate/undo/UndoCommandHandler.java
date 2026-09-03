package console.command.immediate.undo;

import console.command.CommandHandler;
import console.command.ConsoleCommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

@ConsoleCommandHandler
public final class UndoCommandHandler
             extends CommandHandler {
    @Override
    protected String getCommandHeader() {
        return UndoCommand.COMMAND_NAME;
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
    protected List<TokenOrder> getTokenOrder() {
        return List.of(new TokenOrder.FirstOrSecond(new TokenType.Command(null)));
    }
    
    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        UndoCommandBuilder ecb = UndoCommand.builder();
        for (TokenType tt : tokens) {
            switch(tt) {
                case TokenType.Command ignored -> {}
                default -> { return ParsingResult.malformedCommand(this.getCommandHeader(), tokens); }
            }
        }
        return ParsingResult.success(ecb.build());
    }
}
