package console.registry;

import console.Logger;

import java.util.List;

public final class Registry {
    public static final int MIN_REGISTRY_ID = 0;
    public static final int MAX_REGISTRY_ID = 7;
    public static final int TOTAL_REGISTRIES = MAX_REGISTRY_ID + 1 - MIN_REGISTRY_ID;
    static final List<String> ALLOWED_KEYS;
    
    static {
        ALLOWED_KEYS = new java.util.ArrayList<>(TOTAL_REGISTRIES);
        for (int ii = Registry.MIN_REGISTRY_ID; ii <= Registry.MAX_REGISTRY_ID; ii++) {
            ALLOWED_KEYS.add("R" + ii);
        }
    }

    private static Registry instance = null;
    
    private final java.util.Deque<Storage> history;
    
    private Registry() {
        this.history = new java.util.ArrayDeque<>();
        this.history.add(new Storage());
    }
    
    public static Registry getInstance() {
        if (Registry.instance == null) {
            Registry.instance = new Registry();
        }
        return Registry.instance;
    }
    
    private Storage getCurrentStorage() {
        return this.history.getLast();
    }
    
    public Integer getInteger(String key, Logger logger) {
        if (!Storage.isValidKey(key)) {
            logger.err("Invalid key: %s", key);
            return null;
        }
        return this.getCurrentStorage().getInteger(key);
    }
    
    public Integer getInteger(String key) {
        if (!Storage.isValidKey(key)) {
            throw new IllegalKeyException(String.format("Invalid key: %s", key));
        }
        return this.getCurrentStorage().getInteger(key);
    }
    
    public boolean getFlag() {
        Storage curr = this.getCurrentStorage();
        return curr.getFlag();
    }
    
    public void setInteger(String key, Integer value, Logger logger) {
        if (!Storage.isValidKey(key)) {
            logger.err("Invalid key: %s", key);
            return;
        }
        Storage.Alterer sa = this.getCurrentStorage().alter();
        sa.changeRegistry(key, value);
        this.mori(sa.build());
    }
    
    public void setFlag(boolean value) {
        Storage.Alterer sa = this.getCurrentStorage().alter();
        sa.changeFlag(value);
        this.mori(sa.build());
    }
    
    public void moveRegistry(String key, String dest, Logger logger) {
        if (!Storage.isValidKey(key)) {
            logger.err("Invalid key: %s", key);
            return;
        }
        if (!Storage.isValidKey(dest)) {
            logger.err("Invalid key: %s", dest);
            return;
        }
        Storage current = this.getCurrentStorage();
        Storage.Alterer sa = current.alter();
        sa.changeRegistry(key, null);
        sa.changeRegistry(dest, current.getInteger(key));
        this.mori(sa.build());
    }
    
    public void deleteRegistry(String key, Logger logger) {
        if (!Storage.isValidKey(key)) {
            logger.err("Invalid key: %s", key);
            return;
        }
        Storage.Alterer sa = this.getCurrentStorage().alter();
        sa.changeRegistry(key, null);
        this.mori(sa.build());
    }
    
    public void copy(String key, List<String> dests, Logger logger) {
        if (!Storage.isValidKey(key)) {
            logger.err("Invalid key: %s", key);
            return;
        }
        for (String dest : dests) {
            if (! Storage.isValidKey(dest)) {
                logger.err("Invalid key: %s", key);
                return;
            }
        }
        Storage current = this.getCurrentStorage();
        Integer toSet = current.getInteger(key);
        
        Storage.Alterer sa = current.alter();
        for (String dest : dests) {
            sa.changeRegistry(dest, toSet);
        }
        this.mori(sa.build());
    }
    
    public void print(Logger logger) {
        Storage curr = this.getCurrentStorage();
        logger.out(curr.toString());
    }
    
    private void mori(Storage latest) {
        this.history.addLast(latest);
    }
    
    public void rollback(Logger logger) {
        if (this.history.size() == 1) {
            logger.err("Nothing to revert to");
            return;
        }
        this.history.removeLast();
    }

    public void reset() {
        this.history.clear();
        this.history.add(new Storage());
    }
}
