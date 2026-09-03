package console.command.queueable.operations.move;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.operations.OperationCommand;

public final class MoveCommand
             extends OperationCommand
             implements Buildable<MoveCommand, MoveCommandBuilder>{
    public static final String COMMAND_HEADER = "MOVE";
    
    private String dest;
    protected MoveCommand(boolean hasIf, String source, String dest, int cycle, int lastCycle) {
        super(hasIf, source, cycle, lastCycle);
        this.dest = dest;
    }
    
    public static MoveCommandBuilder builder() {
        return new MoveCommandBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_HEADER).append(' ');
        if (this.hasIf) { sb.append("IF").append(' '); }
        sb.append(this.source).append(' ');
        sb.append(this.dest);
        if (this.cycle != 0) { sb.append(' ').append(this.cycle); }
        return sb.toString();
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        context.registry().moveRegistry(this.source, this.dest, context.logger());
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
