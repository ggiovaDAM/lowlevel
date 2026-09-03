package console.command.queueable.operations.mathoperations.binarymathoperations.mod;

import console.command.Buildable;
import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommand;

public final class ModCommand
                   extends BinaryMathOperationCommand
                   implements Buildable<ModCommand, ModCommandBuilder> {
    public static final String COMMAND_HEADER = "MOD";

    protected ModCommand(boolean hasIf, String source, String target, String dest, int cycle, int lastCycle) {
        super(hasIf, source, target, dest, cycle, lastCycle);
    }

    public static ModCommandBuilder builder() {
        return new ModCommandBuilder();
    }
    
    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
    
    @Override
    protected Integer operate(Integer ft, Integer st) {
        return ft % st;
    }

    @Override
    protected String getSymbol() {
        return "mod";
    }
}
