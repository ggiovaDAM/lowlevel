package console.command.queueable.operations.set;

import console.command.Builder;

public final class SetCommandBuilder
             implements Builder<SetCommand, SetCommandBuilder> {
    private boolean hasIf = false;
    private String registry = null;
    private int value = 0;
    private int cycle = 0;
    private int lastCycle = 0;
    
    @Override
    public SetCommand build() {
        return new SetCommand(hasIf, registry, value, cycle, lastCycle);
    }
    
    public SetCommandBuilder setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this;
    }
    public SetCommandBuilder setRegistry(String registry) {
        this.registry = registry;
        return this;
    }
    
    public SetCommandBuilder setValue(int value) {
        this.value = value;
        return this;
    }
    
    public SetCommandBuilder setCycle(int cycle) {
        this.cycle = cycle;
        return this;
    }
    
    public SetCommandBuilder setLastCycle(int lastCycle) {
        this.lastCycle = lastCycle;
        return this;
    }
}
