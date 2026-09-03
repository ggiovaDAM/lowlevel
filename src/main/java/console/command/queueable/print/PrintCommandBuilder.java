package console.command.queueable.print;

import console.command.Builder;

public final class PrintCommandBuilder
             implements Builder<PrintCommand, PrintCommandBuilder> {
    private boolean hasIf = false;
    
    @Override
    public PrintCommand build() {
        return new PrintCommand(hasIf);
    }
    
    public PrintCommandBuilder setHasIf(boolean hasIf) {
        this.hasIf = hasIf;
        return this;
    }
}
