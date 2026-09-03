package console.command.queueable.conditional.unary.not;

import console.command.ConsoleCommandHandler;
import console.command.queueable.conditional.unary.UnaryConditionalCommandBuilder;
import console.command.queueable.conditional.unary.UnaryConditionalCommandHandler;

@ConsoleCommandHandler
public final class NotCommandHandler
             extends UnaryConditionalCommandHandler {
    @Override
    protected String getCommandHeader() {
        return NotCommand.COMMAND_HEADER;
    }

    @Override
    protected UnaryConditionalCommandBuilder<?, ?> builder() {
        return NotCommand.builder();
    }
}
