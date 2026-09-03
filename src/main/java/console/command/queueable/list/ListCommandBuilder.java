package console.command.queueable.list;

import console.command.Builder;

public final class ListCommandBuilder
             implements Builder<ListCommand, ListCommandBuilder> {
    private boolean hasIf = false;

    @Override
    public ListCommand build() {
        return new ListCommand(hasIf);
    }
    
    public ListCommandBuilder setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this;
    }
}
