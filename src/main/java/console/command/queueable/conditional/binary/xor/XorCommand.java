package console.command.queueable.conditional.binary.xor;

import console.command.Buildable;
import console.command.queueable.conditional.binary.BinaryConditionalCommand;
import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;

public final class XorCommand
             extends BinaryConditionalCommand
             implements Buildable<XorCommand, XorCommandBuilder>{
    public static final String COMMAND_HEADER = "XOR";
    
    protected XorCommand(boolean value, int cycle, int lastCycle) {
        super(value, cycle, lastCycle);
    }
    
    public static BinaryConditionalCommandBuilder<?, ?> builder() {
        return new XorCommandBuilder();
    }
    
    public boolean operate(boolean current) {
        return current ^ this.value;
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
