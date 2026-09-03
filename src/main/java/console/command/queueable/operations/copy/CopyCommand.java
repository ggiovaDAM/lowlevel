package console.command.queueable.operations.copy;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.operations.OperationCommand;

import java.util.List;

public final class CopyCommand
             extends OperationCommand
             implements Buildable<CopyCommand, CopyCommandBuilder>{
    public static final String COMMAND_HEADER = "COPY";
    
    private List<String> keys;
    protected CopyCommand(boolean hasIf, String source, List<String> keys, int cycle, int lastCycle) {
        super(hasIf, source, cycle, lastCycle);
        this.keys = keys;
    }
    
    public static CopyCommandBuilder builder() {
        return new CopyCommandBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_HEADER).append(' ');
        if (this.hasIf) { sb.append("IF").append(' '); }
        sb.append(this.source);
        for (String str : this.keys) {
            sb.append(' ').append(str);
        }
        if (this.cycle != 0) { sb.append(' ').append(this.cycle); }
        return sb.toString();
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        context.registry().copy(this.source, this.keys, context.logger());
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
