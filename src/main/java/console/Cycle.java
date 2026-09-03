package console;

public final class Cycle {
    private int currentCycle;
    
    public Cycle() {
        this.currentCycle = 0;
    }
    
    public void increaseCycle(int count) {
        this.currentCycle += count;
    }
    
    public int getCycle() {
        return this.currentCycle;
    }
}
