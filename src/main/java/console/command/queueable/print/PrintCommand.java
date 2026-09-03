package console.command.queueable.print;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.queueable.PredicatedCommand;

public final class PrintCommand
             implements PredicatedCommand,
                        Buildable<PrintCommand, PrintCommandBuilder>{
    public static final String COMMAND_HEADER = "PRINT";
    
    private final boolean hasIf;
    protected PrintCommand(boolean hasIf) {
        this.hasIf = hasIf;
    }
    
    public static PrintCommandBuilder builder() {
        return new PrintCommandBuilder();
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
        context.registry().print(context.logger());
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
