package console.command.queueable.conditional.binary.or;

import console.command.Buildable;
import console.command.queueable.conditional.binary.BinaryConditionalCommand;
import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;

public final class OrCommand
             extends BinaryConditionalCommand
             implements Buildable<OrCommand, OrCommandBuilder>{
    public static final String COMMAND_HEADER = "OR";
    
    protected OrCommand(boolean value, int cycle, int lastCycle) {
        super(value, cycle, lastCycle);
    }
    
    public static BinaryConditionalCommandBuilder<?, ?> builder() {
        return new OrCommandBuilder();
    }
    
    public boolean operate(boolean current) {
        return current | this.value;
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
