package console.command.queueable.operations.mathoperations.binarymathoperations.sub;

import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;

public final class SubCommandBuilder
             extends BinaryMathOperationCommandBuilder<SubCommand, SubCommandBuilder> {
    @Override
    public SubCommand build() {
        return new SubCommand(this.hasIf, this.source, this.target, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected SubCommandBuilder self() {
        return this;
    }
}
