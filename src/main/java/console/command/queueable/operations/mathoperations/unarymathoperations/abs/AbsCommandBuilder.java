package console.command.queueable.operations.mathoperations.unarymathoperations.abs;

import console.command.queueable.operations.mathoperations.unarymathoperations.UnaryMathOperationCommandBuilder;

public final class AbsCommandBuilder
             extends UnaryMathOperationCommandBuilder<AbsCommand, AbsCommandBuilder> {
    @Override
    public AbsCommand build() {
        return new AbsCommand(this.hasIf, this.source, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected AbsCommandBuilder self() {
        return this;
    }
}
