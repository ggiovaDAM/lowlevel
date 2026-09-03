package console.command.queueable.conditional.unary.not;

import console.command.Buildable;
import console.command.queueable.conditional.unary.UnaryConditionalCommand;
import console.command.queueable.conditional.unary.UnaryConditionalCommandBuilder;

public final class NotCommand
             extends UnaryConditionalCommand
             implements Buildable<NotCommand, NotCommandBuilder>{
    public static final String COMMAND_HEADER = "NOT";
    
    protected NotCommand(int cycle, int lastCycle) {
        super(cycle, lastCycle);
    }
    
    public static UnaryConditionalCommandBuilder<?, ?> builder() {
        return new NotCommandBuilder();
    }
    
    public boolean operate(boolean current) {
        return ! current;
    }

    @Override
    public String getHeader() {
        return COMMAND_HEADER;
    }
}
