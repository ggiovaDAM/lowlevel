package console.command.queueable.operations.mathoperations.binarymathoperations.sub;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommand;

public final class SubCommand
                   extends BinaryMathOperationCommand
                   implements Buildable<SubCommand, SubCommandBuilder> {
    public static final String COMMAND_HEADER = "SUB";

    protected SubCommand(boolean hasIf, String source, String target, String dest, int cycle, int lastCycle) {
        super(hasIf, source, target, dest, cycle, lastCycle);
    }

    public static SubCommandBuilder builder() {
        return new SubCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer ft, Integer st) {
        return ft - st;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }
}
