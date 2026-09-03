package console.command.queueable.conditional.binary.and;

import console.command.queueable.conditional.binary.BinaryConditionalCommandBuilder;

public final class AndCommandBuilder
             extends BinaryConditionalCommandBuilder<AndCommand, AndCommandBuilder> {
    @Override
    public AndCommand build() {
        return new AndCommand(bool, cycle, lastCycle);
    }

    @Override
    protected AndCommandBuilder self() {
        return this;
    }
}
