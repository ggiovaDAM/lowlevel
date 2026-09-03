package console.command.queueable.flag;

import console.command.Builder;

public final class FlagCommandBuilder
             implements Builder<FlagCommand, FlagCommandBuilder> {
    private boolean bool = false;
    private int cycle = 0;
    private int lastCycle = 0;

    @Override
    public FlagCommand build() {
        return new FlagCommand(bool, cycle, lastCycle);
    }
    
    public FlagCommandBuilder setBool(boolean bool) {
        this.bool = bool;
        return this;
    }
    
    public FlagCommandBuilder setCycle(int cycle, int lastCycle) {
        this.cycle = cycle;
        this.lastCycle = lastCycle;
        return this;
    }
}
