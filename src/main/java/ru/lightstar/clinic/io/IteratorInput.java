package ru.lightstar.clinic.io;

import java.util.Iterator;

/**
 * Input data from given string iterator.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class IteratorInput implements Input {
    /**
     * Inner iterator.
     */
    private final Iterator<String> iterator;

    /**
     * Constructs <code>IteratorInput</code> object.
     *
     * @param iterator iterator.
     */
    public IteratorInput(final Iterator<String> iterator) {
        super();
        this.iterator = iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String next() {
        return this.iterator.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String ask(final Output output, final String question) {
        output.println(question);
        return this.iterator.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
    }
}
