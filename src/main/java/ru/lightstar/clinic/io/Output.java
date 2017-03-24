package ru.lightstar.clinic.io;

/**
 * Generic output interface.
 *
 * @author LightStar
 * @since 0.0.1
 */
public interface Output {

    /**
     * Output line of text.
     *
     * @param line line of text.
     */
    void println(String line);

    /**
     * Close this output.
     */
    void close();
}
