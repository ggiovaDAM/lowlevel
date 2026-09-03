package console.command.queueable.conditional.binary;

import console.ExecutionContext;
import console.command.ExecutionResult;
import console.command.queueable.conditional.ConditionalCommand;

public abstract class BinaryConditionalCommand
                extends ConditionalCommand {
    protected final boolean value;
    
    protected BinaryConditionalCommand(boolean value, int cycle, int lastCycle) {
        super(cycle, lastCycle);
        this.value = value;
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        
        boolean current = context.registry().getFlag();
        context.registry().setFlag(
                    this.operate(current)
                );
        return new ExecutionResult.Success(this.getHeader() + " executed successfully");
    }
    
    protected abstract boolean operate(boolean current);

    @Override
    public String toString() {
        return this.getHeader() + " " + this.value;
    }
}
