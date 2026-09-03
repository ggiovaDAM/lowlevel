package console.command;

public sealed interface ExecutionResult
              permits ExecutionResult.Empty,
                      ExecutionResult.Success,
                      ExecutionResult.Failure,
                      ExecutionResult.CycleLock,
                      ExecutionResult.PredicateLock {
    record Empty() implements ExecutionResult {}
    record Success(String msg) implements ExecutionResult {}
    record Failure(String msg) implements ExecutionResult {}
    record CycleLock() implements ExecutionResult {}
    record PredicateLock() implements ExecutionResult {}
}
