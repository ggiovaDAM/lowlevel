package console.command.queueable.operations.mathoperations.unarymathoperations.root;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommand;

public final class RootCommand
                   extends UnaryMathOperationCommand
                   implements Buildable<RootCommand, RootCommandBuilder> {
    public static final String COMMAND_HEADER = "ROOT";

    protected RootCommand(boolean hasIf, String source, String dest, int cycle, int lastCycle) {
        super(hasIf, source, dest, cycle, lastCycle);
    }

    public static RootCommandBuilder builder() {
        return new RootCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer t) {
        return (int) Math.sqrt(t);
    }

    @Override
    protected String getSymbol() {
        return "√(" + this.source + ")";
    }
}
