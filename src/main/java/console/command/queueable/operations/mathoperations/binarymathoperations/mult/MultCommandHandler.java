package console.command.queueable.operations.mathoperations.binarymathoperations.mult;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class MultCommandHandler
             extends BinaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return MultCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryMathOperationCommandBuilder<?, ?> builder() {
        return MultCommand.builder();
    }
}
