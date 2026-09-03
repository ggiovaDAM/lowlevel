package console.command.queueable.operations.set;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.operations.OperationCommand;

public final class SetCommand
             extends OperationCommand
             implements Buildable<SetCommand, SetCommandBuilder>{
    public static final String COMMAND_HEADER = "SET";
    
    private int value;
    protected SetCommand(boolean hasIf, String source, int value, int cycle, int lastCycle) {
        super(hasIf, source, cycle, lastCycle);
        this.value = value;
    }
    
    public static SetCommandBuilder builder() {
        return new SetCommandBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_HEADER).append(' ');
        if (this.hasIf) { sb.append("IF").append(' '); }
        sb.append(this.source).append(' ');
        sb.append(this.value);
        if (this.cycle != 0) { sb.append(' ').append(this.cycle); }
        return sb.toString();
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        context.registry().setInteger(this.source, this.value, context.logger());
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
