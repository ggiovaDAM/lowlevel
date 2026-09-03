package console.command.immediate.exec;

import console.command.Builder;

public final class ExecuteCommandBuilder implements Builder<ExecuteCommand, ExecuteCommandBuilder> {
    private static final int DEFAULT_CYCLE = 1;
    
    private int cycle;
    
    public ExecuteCommandBuilder() {
        this.cycle = DEFAULT_CYCLE;
    }
    
    public ExecuteCommandBuilder setCycle(int cycle) {
        this.cycle = cycle;
        return this;
    }
    
    @Override
    public ExecuteCommand build() {
        return new ExecuteCommand(this.cycle);
    }
    
}
