package console.command.queueable.conditional.binary.or;

import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;

public final class OrCommandBuilder
             extends BinaryConditionalCommandBuilder<OrCommand, OrCommandBuilder> {
    @Override
    public OrCommand build() {
        return new OrCommand(bool, cycle, lastCycle);
    }

    @Override
    protected OrCommandBuilder self() {
        return this;
    }
}
