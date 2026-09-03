package console.command.queueable.operations.del;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.operations.OperationCommand;

public final class DelCommand
             extends OperationCommand
             implements Buildable<DelCommand, DelCommandBuilder>{
    public static final String COMMAND_HEADER = "DEL";
    
    protected DelCommand(boolean hasIf, String source, int cycle, int lastCycle) {
        super(hasIf, source, cycle, lastCycle);
    }
    
    public static DelCommandBuilder builder() {
        return new DelCommandBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_HEADER).append(' ');
        if (this.hasIf) { sb.append("IF").append(' '); }
        sb.append(this.source);
        if (this.cycle != 0) { sb.append(' ').append(this.cycle); }
        return sb.toString();
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        context.registry().deleteRegistry(this.source, context.logger());
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
