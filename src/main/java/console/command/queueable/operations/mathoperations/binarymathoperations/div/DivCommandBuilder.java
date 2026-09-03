package console.command.queueable.operations.mathoperations.binarymathoperations.div;

import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;

public final class DivCommandBuilder
             extends BinaryMathOperationCommandBuilder<DivCommand, DivCommandBuilder> {
    @Override
    public DivCommand build() {
        return new DivCommand(this.hasIf, this.source, this.target, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected DivCommandBuilder self() {
        return this;
    }
}
