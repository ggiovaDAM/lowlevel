package console.command.queueable.operations.mathoperations.binarymathoperations.sub;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class SubCommandHandler
             extends BinaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return SubCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryMathOperationCommandBuilder<?, ?> builder() {
        return SubCommand.builder();
    }
}
