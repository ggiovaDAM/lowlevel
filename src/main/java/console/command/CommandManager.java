package console.command;

import console.Cycle;
import console.ExecutionContext;
import console.Logger;
import console.command.immediate.ImmediateCommand;
import console.command.queueable.QueueableCommand;
import console.registry.Registry;

public final class CommandManager {
    private ExecutionContext ctx;
    private ParserContext parserContext;
    //private CommandParser parser;
    
    public CommandManager(Logger logger) {
        this.ctx = new ExecutionContext(
            Registry.getInstance(),
            new CommandQueue(),
            logger,
            new Cycle()
        );
        this.parserContext = new ParserContext(this.ctx.cycle());
        //this.parser = CommandParser.getInstance();
    }

    // Llamar 'process'
    public void handle(String instruction) {
        ParsingResult ps = CommandParser.getInstance().parse(instruction, this.parserContext);
        
        switch (ps) {
            case ParsingResult.Success(Command cmd):
                this.ctx.logger().log(
                        String.format(
                                "Command '%s' built successfully: %s",
                                cmd.getHeader(),
                                cmd.toString()
                                )
                );
            
                if (cmd instanceof ImmediateCommand) {
                    cmd.execute(this.ctx);
                    return;
                }
                if (cmd instanceof QueueableCommand qc) {
                    this.ctx.queue().addCommand(qc);
                }
                break;
            case ParsingResult.Failure(String message):
                this.ctx.logger().err(message); // Debería haber sido 'err'
                break;
        }
    }

    public Integer getInteger(String registry) {
        return this.ctx.registry().getInteger(registry);
    }
    
    public Boolean getFlag() {
        return this.ctx.registry().getFlag();
    }
}
