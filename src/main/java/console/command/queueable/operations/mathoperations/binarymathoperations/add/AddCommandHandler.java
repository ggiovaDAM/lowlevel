package console.command.queueable.operations.mathoperations.binarymathoperations.add;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class AddCommandHandler
             extends BinaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return AddCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryMathOperationCommandBuilder<?, ?> builder() {
        return AddCommand.builder();
    }
}
