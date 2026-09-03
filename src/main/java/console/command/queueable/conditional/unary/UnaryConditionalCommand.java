package console.command.queueable.conditional.unary;

import console.ExecutionContext;
import console.command.ExecutionResult;
import console.command.queueable.conditional.ConditionalCommand;

public abstract class UnaryConditionalCommand
                extends ConditionalCommand {
    protected UnaryConditionalCommand(int cycle, int lastCycle) {
        super(cycle, lastCycle);
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        
        boolean current = context.registry().getFlag();
        context.registry().setFlag(
                    this.operate(current)
                );
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }
    
    protected abstract boolean operate(boolean current);

    @Override
    public String toString() {
        return this.getHeader();
    }
}
