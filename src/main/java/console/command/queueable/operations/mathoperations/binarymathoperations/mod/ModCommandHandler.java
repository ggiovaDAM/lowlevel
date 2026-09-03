package console.command.queueable.operations.mathoperations.binarymathoperations.mod;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class ModCommandHandler
             extends BinaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return ModCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryMathOperationCommandBuilder<?, ?> builder() {
        return ModCommand.builder();
    }
}
