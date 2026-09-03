package console.command.immediate.reset;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.immediate.ImmediateCommand;

public final class ResetCommand implements ImmediateCommand, Buildable<ResetCommand, ResetCommandBuilder> {
    public static final String COMMAND_HEADER = "RESET";
    
    ResetCommand() {}
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        context.registry().reset();
        context.queue().clear();
        context.logger().out("Cleared system");
        return new ExecutionResult.Empty();
    }

    public static ResetCommandBuilder builder() {
        return new ResetCommandBuilder();
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }

    @Override
    public String toString() {
        return this.getHeader();
    }
}
