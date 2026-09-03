package console.command.queueable.conditional.binary.and;

import console.command.ConsoleCommandHandler;
import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;
import console.command.queueable.conditional.binary.BinaryConditionalCommandHandler;

@ConsoleCommandHandler
public final class AndCommandHandler
             extends BinaryConditionalCommandHandler {
    @Override
    protected String getCommandHeader() {
        return AndCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryConditionalCommandBuilder<?, ?> builder() {
        return AndCommand.builder();
    }
}
