package console.command.queueable.conditional.binary.and;

import console.command.Buildable;
import console.command.queueable.conditional.binary.BinaryConditionalCommand;
import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;

public final class AndCommand
             extends BinaryConditionalCommand
             implements Buildable<AndCommand, AndCommandBuilder>{
    public static final String COMMAND_HEADER = "AND";
    
    protected AndCommand(boolean value, int cycle, int lastCycle) {
        super(value, cycle, lastCycle);
    }
    
    public static BinaryConditionalCommandBuilder<?, ?> builder() {
        return new AndCommandBuilder();
    }
    
    public boolean operate(boolean current) {
        return current & this.value;
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
