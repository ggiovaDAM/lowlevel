package console.command.queueable.operations.mathoperations.binarymathoperations.mult;

import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;

public final class MultCommandBuilder
             extends BinaryMathOperationCommandBuilder<MultCommand, MultCommandBuilder> {
    @Override
    public MultCommand build() {
        return new MultCommand(this.hasIf, this.source, this.target, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected MultCommandBuilder self() {
        return this;
    }
}
