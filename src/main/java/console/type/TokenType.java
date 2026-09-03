package console.type;

import console.registry.Registry;

public sealed interface TokenType
              permits TokenType.Invalid,
                      TokenType.RegistryKey,
                      TokenType.Value,
                      TokenType.If,
                      TokenType.Command,
                      TokenType.Bool {
    record Invalid(String message) implements TokenType {}

    record RegistryKey(String key) implements TokenType {
        @Override
        public String toString() { return this.key; }
    }

    record Value(int value) implements TokenType {
        @Override
        public String toString() { return Integer.toString(this.value); }
    }

    record If() implements TokenType {
        @Override
        public String toString() { return "IF"; }
    }

    record Command(String command) implements TokenType {
        @Override
        public String toString() { return this.command; }
    }
    
    record Bool(boolean value) implements TokenType {
        @Override
        public String toString() { return this.value ? "TRUE" : "FALSE"; }
    }
    
    
    
    public static TokenType getType(String input) {
        return TokenType.checkEmpty(input);
    }
    
    private static TokenType checkEmpty(String input) {
        if (input.isEmpty()) { return new Invalid("Too many spaces"); }
        return TokenType.checkIf(input);
    }
    
    private static TokenType checkIf(String input) {
        if (input.equals("IF")) { return new If(); }
        return TokenType.checkBool(input);
    }
    
    private static TokenType checkBool(String input) {
        if (input.equals("TRUE" )) { return new Bool(true ); }
        if (input.equals("FALSE")) { return new Bool(false); }
        return TokenType.checkInteger(input);
    }
    
    private static TokenType checkInteger(String input) {
        try {
            Integer value = Integer.valueOf(input);
            return new Value(value);
        } catch (NumberFormatException nfe) {
            return TokenType.checkRegistry(input);
        }
    }
    
    private static TokenType checkRegistry(String input) {
        if (input.startsWith("R")) {
            String number = input.substring(1);
            try {
                int value = Integer.valueOf(number);
                if (value < Registry.MIN_REGISTRY_ID) {
                    return new Invalid("Non-existant registry: " + input);
                }
                if (value > Registry.MAX_REGISTRY_ID) {
                    return new Invalid("Non-existant registry: " + input);
                }
                return new RegistryKey(input);
            } catch (NumberFormatException ignored) {}
        }
        return TokenType.checkCommand(input);
    }
    
    private static TokenType checkCommand(String input) {
        return new Command(input);
    }
}
