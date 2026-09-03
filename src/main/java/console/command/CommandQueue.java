package console.command;

import console.command.queueable.QueueableCommand;

public final class CommandQueue {
    private final java.util.Deque<QueueableCommand> pending;
    
    public CommandQueue() {
        this.pending = new java.util.ArrayDeque<>();
    }
    
    public void addCommand(QueueableCommand command) {
        this.pending.add(command);
    }
    
    public java.util.Deque<QueueableCommand> getPending() {
        return this.pending;
    }
    
    public java.util.Deque<QueueableCommand> getPendingCopy() {
        return new java.util.ArrayDeque<>(this.pending);
    }

    public void clear() {
        this.pending.clear();
    }

    public void remove(Command command) {
        this.pending.remove(command);
    }
}
