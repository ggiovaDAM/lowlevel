package console.command.queueable.operations.mathoperations.unarymathoperations.root;

import console.command.ConsoleCommandHandler;
import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommandBuilder;
import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommandHandler;

@ConsoleCommandHandler
public final class RootCommandHandler
             extends UnaryMathOperationCommandHandler {
    @Override
    protected String getCommandHeader() {
        return RootCommand.COMMAND_HEADER;
    }

    @Override
    protected UnaryMathOperationCommandBuilder<?, ?> builder() {
        return RootCommand.builder();
    }
}
