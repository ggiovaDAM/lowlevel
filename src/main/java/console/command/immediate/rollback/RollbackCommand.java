package console.command.immediate.rollback;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.immediate.ImmediateCommand;

public final class RollbackCommand
             implements ImmediateCommand,
                        Buildable<RollbackCommand, RollbackCommandBuilder> {
    public static final String COMMAND_HEADER = "ROLLBACK";
    private final int count;
    
    RollbackCommand(int count) {
        this.count = count;
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        for (int ii = 0; ii < this.count; ii++) {
            context.registry().rollback(context.logger());
        }
        return new ExecutionResult.Empty();
    }

    public static RollbackCommandBuilder builder() {
        return new RollbackCommandBuilder();
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.getHeader(), this.count);
    }
}
