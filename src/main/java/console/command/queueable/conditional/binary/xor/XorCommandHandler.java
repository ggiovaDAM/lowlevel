package console.command.queueable.conditional.binary.xor;

import console.command.ConsoleCommandHandler;
import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;
import console.command.queueable.conditional.binary.BinaryConditionalCommandHandler;

@ConsoleCommandHandler
public final class XorCommandHandler
             extends BinaryConditionalCommandHandler {
    @Override
    protected String getCommandHeader() {
        return XorCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryConditionalCommandBuilder<?, ?> builder() {
        return XorCommand.builder();
    }
}
