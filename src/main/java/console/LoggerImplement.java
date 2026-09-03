package console;

import java.io.PrintWriter;

public final class LoggerImplement extends Logger {
    public LoggerImplement() {
        super(
              new PrintWriter(System.out),
              new PrintWriter(System.err),
              new PrintWriter(System.out)
             );
    }
}
