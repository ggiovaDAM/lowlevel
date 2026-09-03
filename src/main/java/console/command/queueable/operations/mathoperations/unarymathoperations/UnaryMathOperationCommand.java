package console.command.queueable.operations.mathoperations.unarymathoperations;

import console.ExecutionContext;
import console.command.ExecutionResult;
import console.command.queueable.operations.mathoperations.MathOperationCommand;

public abstract class UnaryMathOperationCommand
                extends MathOperationCommand {
    protected UnaryMathOperationCommand(boolean hasFlag, String source, String dest, int cycle, int lastCycle) {
        super(hasFlag, source, dest, cycle, lastCycle);
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.CycleLock(); }
        
        Integer sourceTermValue = context.registry().getInteger(this.source, context.logger());
        if (sourceTermValue == null) {
            return new ExecutionResult.Failure("Registries have not been initialized");
        }
        context.registry().setInteger(
                this.dest,
                this.operate(sourceTermValue),
                context.logger()
        );
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }
    
    @Override
    public String toString() {
        String command = this.getHeader() + ": " + this.getSymbol() + " --en--> " + this.dest;
        if (this.cycle == 0) { return command; }
        return command + " in " + this.cycle + " cycles.";
    }
    
    protected abstract Integer operate(Integer t);
    
    protected abstract String getSymbol();
}
