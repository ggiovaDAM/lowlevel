package console.command.immediate.undo;

import console.command.Builder;

public final class UndoCommandBuilder
             implements Builder<UndoCommand, UndoCommandBuilder> {
    public UndoCommandBuilder() {}
    
    @Override
    public UndoCommand build() {
        return new UndoCommand();
    }
    
}
