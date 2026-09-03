package console.command.queueable.conditional.unary.not;

import console.command.queueable.conditional.unary.UnaryConditionalCommandBuilder;

public final class NotCommandBuilder
             extends UnaryConditionalCommandBuilder<NotCommand, NotCommandBuilder> {
    @Override
    public NotCommand build() {
        return new NotCommand(cycle, lastCycle);
    }

    @Override
    protected NotCommandBuilder self() {
        return this;
    }
}
