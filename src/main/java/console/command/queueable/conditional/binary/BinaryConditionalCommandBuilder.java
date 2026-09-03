package console.command.queueable.conditional.binary;

import console.command.Buildable;
import console.command.Builder;
import console.command.Command;

public abstract class BinaryConditionalCommandBuilder<
                        C extends Command & Buildable<C, B>,
                        B extends BinaryConditionalCommandBuilder<C, B>
                > implements Builder<C, B> {
    protected boolean bool = false;
    protected int cycle = 0;
    protected int lastCycle = 0;
    
    public B setBool(boolean bool) {
        this.bool = bool;
        return this.self();
    }
    
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
