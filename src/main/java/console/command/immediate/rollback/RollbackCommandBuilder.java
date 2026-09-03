package console.command.immediate.rollback;

import console.command.Builder;

public final class RollbackCommandBuilder
             implements Builder<RollbackCommand, RollbackCommandBuilder> {
    private static final int DEFAULT_COUNT = 1;
    
    private int count;
    
    public RollbackCommandBuilder() {
        this.count = DEFAULT_COUNT;
    }
    
    public RollbackCommandBuilder setCount(int count) {
        this.count = count;
        return this;
    }
    
    @Override
    public RollbackCommand build() {
        return new RollbackCommand(this.count);
    }
    
}
