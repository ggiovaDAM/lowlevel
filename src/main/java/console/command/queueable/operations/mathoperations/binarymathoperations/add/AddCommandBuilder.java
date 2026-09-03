package console.command.queueable.operations.mathoperations.binarymathoperations.add;

import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;

public final class AddCommandBuilder
             extends BinaryMathOperationCommandBuilder<AddCommand, AddCommandBuilder> {
    @Override
    public AddCommand build() {
        return new AddCommand(this.hasIf, this.source, this.target, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected AddCommandBuilder self() {
        return this;
    }
}
