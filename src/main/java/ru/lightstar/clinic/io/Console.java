package ru.lightstar.clinic.io;

import java.io.IOException;
import java.util.Scanner;

/**
 * Console class. It does IO through standard input/output.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Console implements Input, Output {

    /**
     * Console scanner.
     */
    private final Scanner scanner;

    /**
     * Constructs <code>Console</code> object.
     */
    public Console() {
        super();
        this.scanner = new Scanner(System.in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String next() {
        return this.scanner.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void waitEnter() {
        try {
            System.in.mark(1);
            System.in.read();
            System.in.reset();
        } catch (IOException e) {
            throw new IllegalStateException("I/O error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String ask(final Output output, final String question) {
        output.println(question);
        return this.scanner.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void println(final String line) {
        System.out.println(line);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.scanner.close();
    }
}
