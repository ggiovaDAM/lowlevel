package console;

import console.command.CommandQueue;
import console.registry.Registry;

public record ExecutionContext(
    Registry registry,
    CommandQueue queue,
    Logger logger,
    Cycle cycle
) {}
