package console.registry;

public class IllegalKeyException extends IllegalArgumentException {
    
    private static final long serialVersionUID = 3457280750017909914L;

    public IllegalKeyException(String key) {
        super(String.format("Key (%s) is unrecognized.", key));
    }
}
