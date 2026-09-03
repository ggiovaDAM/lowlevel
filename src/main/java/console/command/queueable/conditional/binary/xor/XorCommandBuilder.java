package console.command.queueable.conditional.binary.xor;

import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;

public final class XorCommandBuilder
             extends BinaryConditionalCommandBuilder<XorCommand, XorCommandBuilder> {
    @Override
    public XorCommand build() {
        return new XorCommand(bool, cycle, lastCycle);
    }

    @Override
    protected XorCommandBuilder self() {
        return this;
    }
}
