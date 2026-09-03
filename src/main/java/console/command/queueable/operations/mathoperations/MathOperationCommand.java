package console.command.queueable.operations.mathoperations;

import console.command.queueable.operations.OperationCommand;

public abstract class MathOperationCommand
                extends OperationCommand {
    protected String dest;
    
    protected MathOperationCommand(boolean hasFlag, String source, String dest, int cycle, int lastCycle) {
        super(hasFlag, source, cycle, lastCycle);
        this.dest = dest;
    }
}
