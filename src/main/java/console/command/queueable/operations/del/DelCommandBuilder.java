package console.command.queueable.operations.del;

import console.command.Builder;

public final class DelCommandBuilder
             implements Builder<DelCommand, DelCommandBuilder> {
    private boolean hasIf = false;
    private String registry = null;
    private int cycle = 0;
    private int lastCycle = 0;
    
    @Override
    public DelCommand build() {
        return new DelCommand(hasIf, registry, cycle, lastCycle);
    }
    
    public DelCommandBuilder setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this;
    }
    
    public DelCommandBuilder setRegistry(String registry) {
        this.registry = registry;
        return this;
    }
    
    public DelCommandBuilder setCycle(int cycle) {
        this.cycle = cycle;
        return this;
    }
    
    public DelCommandBuilder setLastCycle(int lastCycle) {
        this.lastCycle = lastCycle;
        return this;
    }
}
