package console.command.immediate.exec;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.Command;
import console.command.ExecutionResult;
import console.command.immediate.ImmediateCommand;

public final class ExecuteCommand implements ImmediateCommand, Buildable<ExecuteCommand, ExecuteCommandBuilder> {
    public static final String COMMAND_HEADER = "EXEC";
    private final int cycle;
    
    ExecuteCommand(int cycle) {
        this.cycle = cycle;
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        context.logger().log("Execution initialized");
        context.cycle().increaseCycle(cycle);
        for (Command cmd : context.queue().getPendingCopy()) {
            ExecutionResult er = cmd.execute(context);
            switch (er) {
                case ExecutionResult.Success(String msg) -> {
                    context.logger().out(msg);
                    context.queue().remove(cmd);
                }
                case ExecutionResult.Failure(String msg) -> {
                    context.logger().log(msg);
                    context.queue().remove(cmd);
                }
                case ExecutionResult.CycleLock() -> {}
                case ExecutionResult.PredicateLock() -> {}
                case ExecutionResult.Empty() -> { context.queue().remove(cmd); }
            }
        }
        return new ExecutionResult.Empty();
    }

    public static ExecuteCommandBuilder builder() {
        return new ExecuteCommandBuilder();
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }

    @Override
    public String toString() {
        return String.format("%s %d", COMMAND_HEADER, this.cycle);
    }
}
