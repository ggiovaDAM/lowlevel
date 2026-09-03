package console.command.queueable.operations.mathoperations.binarymathoperations.add;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommand;

public final class AddCommand
                   extends BinaryMathOperationCommand
                   implements Buildable<AddCommand, AddCommandBuilder> {
    public static final String COMMAND_HEADER = "ADD";

    protected AddCommand(boolean hasIf, String source, String target, String dest, int cycle, int lastCycle) {
        super(hasIf, source, target, dest, cycle, lastCycle);
    }

    public static AddCommandBuilder builder() {
        return new AddCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer ft, Integer st) {
        return ft + st;
    }

    @Override
    protected String getSymbol() {
        return "+";
    }
}
