package console.command.queueable.operations.copy;

import console.command.Builder;

public final class CopyCommandBuilder
             implements Builder<CopyCommand, CopyCommandBuilder> {
    private boolean hasIf = false;
    private String registry = null;
    private java.util.List<String> keys = new java.util.ArrayList<>();
    private int cycle = 0;
    private int lastCycle = 0;
    
    @Override
    public CopyCommand build() {
        return new CopyCommand(hasIf, registry, keys, cycle, lastCycle);
    }
    
    public CopyCommandBuilder setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this;
    }
    
    public CopyCommandBuilder setRegistry(String registry) {
        this.registry = registry;
        return this;
    }
    
    public CopyCommandBuilder addKey(String key) {
        this.keys.add(key);
        return this;
    }
    
    public CopyCommandBuilder setCycle(int cycle, int lastCycle) {
        this.cycle = cycle;
        this.lastCycle = lastCycle;
        return this;
    }
}
