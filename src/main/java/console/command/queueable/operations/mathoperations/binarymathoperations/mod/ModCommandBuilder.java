package console.command.queueable.operations.mathoperations.binarymathoperations.mod;

import console.command.queueable.operations.mathoperations.binarymathoperations.BinaryMathOperationCommandBuilder;

public final class ModCommandBuilder
             extends BinaryMathOperationCommandBuilder<ModCommand, ModCommandBuilder> {
    @Override
    public ModCommand build() {
        return new ModCommand(this.hasIf, this.source, this.target, this.destination, this.cycle, this.lastCycle);
    }

    @Override
    protected ModCommandBuilder self() {
        return this;
    }
}
