package console.command.queueable.list;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.PredicatedCommand;

public final class ListCommand
             implements PredicatedCommand,
                        Buildable<ListCommand, ListCommandBuilder>{
    public static final String COMMAND_HEADER = "LIST";
    
    private final boolean hasIf;
    protected ListCommand(boolean hasIf) {
        this.hasIf = hasIf;
    }
    
    public static ListCommandBuilder builder() {
        return new ListCommandBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_HEADER).append(' ');
        if (this.hasIf) { sb.append("IF"); }
        return sb.toString();
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        context.queue().getPending().forEach(c -> context.logger().out(c.toString()));
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
