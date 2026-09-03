package console.command.queueable.operations.mathoperations.binarymathoperations.div;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class DivCommandHandler
             extends BinaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return DivCommand.COMMAND_HEADER;
    }

    @Override
    protected BinaryMathOperationCommandBuilder<?, ?> builder() {
        return DivCommand.builder();
    }
}
