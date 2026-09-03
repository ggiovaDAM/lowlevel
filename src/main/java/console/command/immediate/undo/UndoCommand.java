package console.command.immediate.undo;

import console.ExecutionContext;
import console.command.Buildable;
import console.command.ExecutionResult;
import console.command.immediate.ImmediateCommand;
import console.command.queueable.QueueableCommand;

public final class UndoCommand implements ImmediateCommand, Buildable<UndoCommand, UndoCommandBuilder>  {
    public static final String COMMAND_NAME = "UNDO";
    
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        java.util.Deque<QueueableCommand> deque = context.queue().getPending();
        if (deque.isEmpty()) {
            return new ExecutionResult.Failure("Can't undo as there are no commands left to undo.");
        }
        QueueableCommand cmd = deque.pollLast();
        return new ExecutionResult.Success("Undid command " + cmd);
    }

    @Override
    public String getHeader() {
        return COMMAND_NAME;
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }

    public static UndoCommandBuilder builder() {
        return new UndoCommandBuilder();
    }
}
