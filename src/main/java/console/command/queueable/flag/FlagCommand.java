package console.command.queueable.flag;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.ExpirableCommand;

public final class FlagCommand
             extends ExpirableCommand
             implements Buildable<FlagCommand, FlagCommandBuilder>{
    public static final String COMMAND_HEADER = "FLAG";
    
    private final boolean value;
    protected FlagCommand(boolean value, int cycle, int lastCycle) {
        super(cycle, lastCycle);
        this.value = value;
    }
    
    public static FlagCommandBuilder builder() {
        return new FlagCommandBuilder();
    }

    @Override
    public String toString() {
        return this.getHeader() + " " + this.value;
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        context.registry().setFlag(this.value);
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully.");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
