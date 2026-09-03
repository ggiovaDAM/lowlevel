package console.registry;

import console.Logger;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class Storage {
    private static final boolean DEFAULT_FLAG = true;
    
    private final Map<String, Integer> registries;
    private boolean flag;
    
    Storage() {
        this.flag = DEFAULT_FLAG;
        this.registries = Registry.ALLOWED_KEYS.stream()
                                          .collect(
                                              HashMap::new,
                                              (m, s) -> m.put(s, null),
                                              HashMap::putAll
                                          );
    }
    
    private Storage(Storage copy) {
        this.registries = copy.copyIntgerRegistry();
        this.flag = copy.copyFlag();
    }
    
    private Map<String, Integer> copyIntgerRegistry() {
        return new HashMap<>(this.registries);
    }
    
    private boolean copyFlag() {
        return this.flag;
    }
    
    static class Alterer {
        private final Storage toModify;
        private final java.util.List<KeyValue> kv;
        private boolean flag;
        
        private record KeyValue(String key, Integer value) {}
        
        Alterer(Storage current) {
            this.toModify = current;
            this.kv = new java.util.ArrayList<>();
            this.flag = current.copyFlag();
        }
        
        public Storage build() {
            Storage copy = new Storage(this.toModify);
            kv.forEach(kv -> copy.registries.put(kv.key, kv.value));
            copy.flag = this.flag;
            return copy;
        }
        
        public Alterer changeRegistry(String key, Integer value) {
            Storage.isValidKeyOrThrow(key);
            this.kv.add(new KeyValue(key, value));
            return this;
        }
        
        public Alterer changeFlag(boolean flag) {
            this.flag = flag;
            return this;
        }
    }
    
    Alterer alter() {
        return new Alterer(this);
    }
    
    static boolean isValidKey(String key) {
        return Registry.ALLOWED_KEYS.contains(key);
    }
    
    static void isValidKeyOrThrow(String key) {
        if (! Storage.isValidKey(key)) {
            throw new IllegalKeyException(key);
        }
    }
    
    private Integer getUnchecked(String key) {
        return this.registries.get(key);
    }
    
    public Integer getInteger(String key) {
        Storage.isValidKeyOrThrow(key);
        return this.getUnchecked(key);
    }
    
    public Integer getIntegerOrLog(String key, Logger logger) {
        if (! Storage.isValidKey(key)) {
            logger.err(String.format("Key (%s) is unrecognized.", key));
            return null;
        }
        return this.getUnchecked(key);
    }
    
    public boolean getFlag() {
        return this.flag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.registries.entrySet()
                       .stream()
                       .sorted(new Comparator<Entry<String, Integer>>() {
                            @Override
                            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                                return o1.getKey().compareTo(o2.getKey());
                            }
                       })
                       .map(e ->  e.getKey() + ": " + (e.getValue() == null ? "NOT INITIALIZED" : e.getValue()) + "\n")
                       .forEach(sb::append);
        sb.append("FLAG: ").append(this.flag ? "TRUE" : "FALSE");
        return sb.toString();
    }
}
