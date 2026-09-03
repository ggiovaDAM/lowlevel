package console.command.queueable.conditional;

import console.command.queueable.ExpirableCommand;

public abstract class ConditionalCommand
                extends ExpirableCommand {
    
    protected ConditionalCommand(int cycle, int lastCycle) {
        super(cycle, lastCycle);
    }
}
