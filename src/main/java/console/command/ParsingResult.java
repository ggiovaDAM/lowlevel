package console.command;

import console.type.TokenType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public sealed interface ParsingResult
              permits ParsingResult.Success,
                      ParsingResult.Failure {
    record Success(Command command) implements ParsingResult {}
    record Failure( String message) implements ParsingResult {}

    
    
    static ParsingResult success(Command command) {
        return new Success(command);
    }
    
    static Optional<ParsingResult> malformedCommand(List<TokenType> tokens) {
        return Optional.of(
            new Failure(
                String.format(
                    "Unknown or malformed command: %s",
                    tokens.stream().map(Object::toString).collect(Collectors.joining(" "))
                )
            )
        );
    }
    
    static ParsingResult malformedCommand(String header, List<TokenType> tokens) {
        return new Failure(
            String.format(
                "Malformed %s command: %s",
                header,
                tokens.stream().map(Object::toString).collect(Collectors.joining(" "))
            )
        );
    }
    
    static ParsingResult illegalValue(String header, Object arg, String rule) {
        return new Failure(
            String.format(
                "Invalid argument for %s: %s, must be %s",
                header,
                arg,
                rule
            )
        );
    }
    
    static ParsingResult badType(String header, Class<?> expected, Class<?> got) {
        return new Failure(
                String.format(
                    "Bad type for %s function. Expected %s, got %s.",
                    header,
                    expected,
                    got
                )
        );
    }
}
