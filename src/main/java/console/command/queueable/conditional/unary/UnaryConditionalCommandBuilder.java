package console.command.queueable.conditional.unary;

import console.command.Buildable;
import console.command.Builder;
import console.command.Command;

public abstract class UnaryConditionalCommandBuilder<
                        C extends Command & Buildable<C, B>,
                        B extends UnaryConditionalCommandBuilder<C, B>
                > implements Builder<C, B> {
    protected int cycle = 0;
    protected int lastCycle = 0;
    
    public B setCycle(int cycle, int lastCycle) {
        this.cycle = cycle;
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
