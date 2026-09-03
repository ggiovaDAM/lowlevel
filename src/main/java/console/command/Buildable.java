package console.command;

public interface Buildable<
                           C extends Command & Buildable<C, B>,
                           B extends Builder<C, B>
                          > {
}
