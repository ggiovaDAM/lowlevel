package console;

import java.io.PrintWriter;
import java.time.LocalDateTime;

public abstract class Logger {
    private final PrintWriter output;
    private final PrintWriter error;
    private final PrintWriter debug;
    
    public Logger(PrintWriter output, PrintWriter error, PrintWriter debug) {
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null");
        }
        if (error == null) {
            throw new IllegalArgumentException("error cannot be null");
        }
        if (debug == null) {
            throw new IllegalArgumentException("debug cannot be null");
        }
        this.output = output;
        this.error = error;
        this.debug = debug;
    }
    
    public void out(String msg) {
        this.output.append(msg).append("\n").flush();
    }

    public void err(String msg) {
        this.error.append(this.formatMessage(msg)).flush();
    }
   
    public void log(String msg) {
        this.debug.append(this.formatMessage(msg)).flush();
    }
    
    protected String formatMessage(String msg) {
        return String.format(
                 "%-29s: %s%n",
                 LocalDateTime.now().toString(),
                 msg
               );
    }
    
    public void err(String format, Object... args) {
        this.err(String.format(format, args));
    }
}
