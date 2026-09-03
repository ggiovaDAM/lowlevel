package console.command.queueable.operations.move;

import console.command.Builder;

public final class MoveCommandBuilder
             implements Builder<MoveCommand, MoveCommandBuilder> {
    private boolean hasIf = false;
    private String registry = null;
    private String dest = null;
    private int cycle = 0;
    private int lastCycle = 0;
    
    @Override
    public MoveCommand build() {
        return new MoveCommand(hasIf, registry, dest, cycle, lastCycle);
    }
    
    public MoveCommandBuilder setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this;
    }
    
    public MoveCommandBuilder setRegistry(String registry) {
        this.registry = registry;
        return this;
    }
    
    public MoveCommandBuilder setDestination(String dest) {
        this.dest = dest;
        return this;
    }
    
    public MoveCommandBuilder setCycle(int cycle) {
        this.cycle = cycle;
        return this;
    }
    
    public MoveCommandBuilder setLastCycle(int lastCycle) {
        this.lastCycle = lastCycle;
        return this;
    }
}
