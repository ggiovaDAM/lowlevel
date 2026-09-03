package console.command.queueable;

import console.Cycle;

public abstract class ExpirableCommand implements QueueableCommand {
    protected int cycle;
    protected int lastCycle;
    
    protected ExpirableCommand(int cycle, int lastCycle) {
        this.cycle = cycle;
        this.lastCycle = lastCycle;
    }
    
    protected boolean canExecute(Cycle cycle) {
        return this.cycle + this.lastCycle <= cycle.getCycle();
    }
}
