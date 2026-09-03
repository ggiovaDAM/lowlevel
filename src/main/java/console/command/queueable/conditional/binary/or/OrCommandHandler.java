package console.command.queueable.conditional.binary.or;

import console.command.ConsoleCommandHandler;
import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;
import console.command.queueable.conditional.binary.BinaryConditionalCommandHandler;

@ConsoleCommandHandler
public final class OrCommandHandler
             extends BinaryConditionalCommandHandler {
    @Override
    protected String getCommandHeader() {
        return OrCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryConditionalCommandBuilder<?, ?> builder() {
        return OrCommand.builder();
    }
}
