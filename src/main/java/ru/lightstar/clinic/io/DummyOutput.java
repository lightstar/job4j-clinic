package ru.lightstar.clinic.io;

/**
 * <code>Output</code> interface realization, which does nothing.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DummyOutput implements Output {

    /**
     * {@inheritDoc}
     */
    @Override
    public void println(String line) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
    }
}
