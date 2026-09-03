package console.command;

import console.ExecutionContext;

public interface Command {
    public ExecutionResult execute(ExecutionContext context);
    public String getHeader();
    //public void process(ExecutionContext context);
}
