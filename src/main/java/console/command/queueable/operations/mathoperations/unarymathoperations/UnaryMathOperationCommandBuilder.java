package console.command.queueable.operations.mathoperations.unarymathoperations;

import console.command.Buildable;
import console.command.Builder;
import console.command.Command;

public abstract class UnaryMathOperationCommandBuilder<
                                                        C extends Command & Buildable<C, B>,
                                                        B extends UnaryMathOperationCommandBuilder<C, B>
                                                       > implements Builder<C, B> {
    protected boolean hasIf = false;
    protected String source = null;
    protected String destination = null;
    protected int cycle = 0;
    protected int lastCycle = 0;
    
    public B setSource(String source) {
        this.source = source;
        if (this.destination == null) { this.destination = source; }
        return this.self();
    }
    
    public B setDestination(String destination) {
        this.destination = destination;
        return this.self();
    }
    
    public B setCycle(int cycle) {
        this.cycle = cycle;
        return this.self();
    }
    
    public B setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this.self();
    }
    
    public B setLastCycle(int lastCycle) {
        this.lastCycle = lastCycle;
        return this.self();
    }
    
    /**
     * Returns itself, as in {@code this}. Made so no casting is required.
     * 
     * @return this
     */
    protected abstract B self();
}
