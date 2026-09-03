package console.command;

public interface Builder<
                         C extends Command & Buildable<C, B>,
                         B extends Builder<C, B>
                        > {
    C build();
}
