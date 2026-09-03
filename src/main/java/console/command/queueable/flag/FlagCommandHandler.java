package console.command.queueable.flag;

import console.command.CommandHandler;
import console.command.ConsoleCommandHandler;
import console.command.ParserContext;
import console.command.ParsingResult;
import console.type.TokenOrder;
import console.type.TokenType;

import java.util.List;
import java.util.Optional;

@ConsoleCommandHandler
public final class FlagCommandHandler extends CommandHandler {
    
    @Override
    protected String getCommandHeader() {
        return FlagCommand.COMMAND_HEADER;
    }

    @Override
    protected Optional<ParsingResult> validate(List<TokenType> tokens) {
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command tc -> {}
                case TokenType.Bool tb -> {}
                default -> ParsingResult.malformedCommand(tokens);
            }
        }
        return Optional.empty();
    }

    @Override
    protected ParsingResult build(List<TokenType> tokens, ParserContext parserContext) {
        FlagCommandBuilder scb = FlagCommand.builder();
        for (TokenType tt : tokens) {
            switch (tt) {
                case TokenType.Command ignored -> {}
                case TokenType.Bool(boolean bool) -> { scb.setBool(bool); }
                default -> {}
            }
        }
        return ParsingResult.success(scb.build());
    }

    @Override
    protected List<TokenOrder> getTokenOrder() {
        return List.of(
                new TokenOrder.FirstOrSecond(new TokenType.Command(null)),
                new TokenOrder.Required(new TokenType.Bool(false))
        );
    }
}
