package console.command.queueable.operations.mathoperations.unarymathoperations.abs;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class AbsCommandHandler
             extends UnaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return AbsCommand.COMMAND_HEADER;
    }

    @Override
    protected UnaryMathOperationCommandBuilder<?, ?> builder() {
        return AbsCommand.builder();
    }
}
