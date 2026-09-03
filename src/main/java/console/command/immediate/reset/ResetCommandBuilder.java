package console.command.immediate.reset;

import console.command.Builder;

public final class ResetCommandBuilder implements Builder<ResetCommand, ResetCommandBuilder> {
    public ResetCommandBuilder() {}
    
    @Override
    public ResetCommand build() {
        return new ResetCommand();
    }
}
