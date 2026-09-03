package console.command.queueable.operations.mathoperations.unarymathoperations.abs;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommand;

public final class AbsCommand
                   extends UnaryMathOperationCommand
                   implements Buildable<AbsCommand, AbsCommandBuilder> {
    public static final String COMMAND_HEADER = "ABS";

    protected AbsCommand(boolean hasIf, String source, String dest, int cycle, int lastCycle) {
        super(hasIf, source, dest, cycle, lastCycle);
    }

    public static AbsCommandBuilder builder() {
        return new AbsCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer t) {
        return Math.abs(t);
    }

    @Override
    protected String getSymbol() {
        return "|" + this.source + "|";
    }
}
