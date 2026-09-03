package console.command.queueable;

import console.ExecutionContext;

public interface PredicatedCommand extends QueueableCommand {

    public default boolean failsPredicate(boolean hasIf, ExecutionContext ec) {
        return hasIf && ! ec.registry().getFlag();
    }
}
