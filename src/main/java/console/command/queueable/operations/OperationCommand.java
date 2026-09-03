package console.command.queueable.operations;

import console.command.queueable.ExpirableCommand;
import console.command.queueable.PredicatedCommand;

public abstract class OperationCommand
                extends ExpirableCommand
                implements PredicatedCommand {
    protected boolean hasIf;
    protected String source;
    
    protected OperationCommand(boolean hasIf, String source, int cycle, int lastCycle) {
        super(cycle, lastCycle);
        this.hasIf = hasIf;
        this.source = source;
    }
}
