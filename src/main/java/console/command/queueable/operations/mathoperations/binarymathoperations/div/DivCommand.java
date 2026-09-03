package console.command.queueable.operations.mathoperations.binarymathoperations.div;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommand;

public final class DivCommand
                   extends BinaryMathOperationCommand
                   implements Buildable<DivCommand, DivCommandBuilder> {
    public static final String COMMAND_HEADER = "DIV";

    protected DivCommand(boolean hasIf, String source, String target, String dest, int cycle, int lastCycle) {
        super(hasIf, source, target, dest, cycle, lastCycle);
    }

    public static DivCommandBuilder builder() {
        return new DivCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer ft, Integer st) {
        return ft / st;
    }

    @Override
    protected String getSymbol() {
        return "/";
    }
}
