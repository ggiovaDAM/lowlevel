package console.command.queueable.operations.mathoperations.unarymathoperations.root;

import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommandBuilder;

public final class RootCommandBuilder
             extends UnaryMathOperationCommandBuilder<RootCommand, RootCommandBuilder> {
    @Override
    public RootCommand build() {
        return new RootCommand(this.hasIf, this.source, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected RootCommandBuilder self() {
        return this;
    }
}
