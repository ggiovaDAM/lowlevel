package console;

import console.command.CommandManager;

public final class Console {
    private CommandManager manager;
    
    public Console(Logger logger) {
        this.manager = new CommandManager(logger);
    }
    
    public Console() {
        this(new LoggerImplement());
    }
    
    public Console addInstruction(String instruction) {
        this.manager.handle(instruction);
        return this;
    }
    
    // No deberían estar
    public Integer getRegistryValue(String registry) {
        return this.manager.getInteger(registry);
    }

    public boolean getFlag() {
        return this.manager.getFlag();
    }
}
