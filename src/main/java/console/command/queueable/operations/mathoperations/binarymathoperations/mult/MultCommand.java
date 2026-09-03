package console.command.queueable.operations.mathoperations.binarymathoperations.mult;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommand;

public final class MultCommand
                   extends BinaryMathOperationCommand
                   implements Buildable<MultCommand, MultCommandBuilder> {
    public static final String COMMAND_HEADER = "MULT";

    protected MultCommand(boolean hasIf, String source, String target, String dest, int cycle, int lastCycle) {
        super(hasIf, source, target, dest, cycle, lastCycle);
    }

    public static MultCommandBuilder builder() {
        return new MultCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer ft, Integer st) {
        return ft * st;
    }

    @Override
    protected String getSymbol() {
        return "×";
    }
}
