package console.command.queueable.operations.mathoperations.binarymathoperations;

import console.ExecutionContext;
import console.command.ExecutionResult;
import console.command.queueable.operations.mathoperations.MathOperationCommand;

public abstract class BinaryMathOperationCommand
                extends MathOperationCommand {
    protected String target;
        
    protected BinaryMathOperationCommand(boolean hasFlag, String source, String target, String dest, int cycle, int lastCycle) {
        super(hasFlag, source, dest, cycle, lastCycle);
        this.target = target;
    }
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        if (this.failsPredicate(hasIf, context)) { return new ExecutionResult.PredicateLock(); }
        if (! this.canExecute(context.cycle())) { return new ExecutionResult.PredicateLock(); }
        
        Integer sourceTermValue = context.registry().getInteger(this.source, context.logger());
        Integer targetTermValue = context.registry().getInteger(this.target, context.logger());
        if (sourceTermValue == null || targetTermValue == null) {
            return new ExecutionResult.Failure("Registries have not been initialized");
        }
        context.registry().setInteger(
                this.dest,
                this.operate(
                        sourceTermValue,
                        targetTermValue
                ),
                context.logger()
        );
        return new ExecutionResult.Success(this.getHeader() + " has been executed successfully");
    }
    
    @Override
    public String toString() {
        String command = this.getHeader() + ": " + this.source + " " + this.getSymbol() + " " + this.target + " --en--> " + this.dest;
        if (this.cycle == 0) { return command; }
        return command + " in " + this.cycle + " cycles.";
    }
    
    protected abstract Integer operate(Integer ft, Integer st);
    
    protected abstract String getSymbol();
}
