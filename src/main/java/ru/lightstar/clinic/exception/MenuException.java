package ru.lightstar.clinic.exception;

/**
 * Error in menu processing.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class MenuException extends Exception {

    /**
     * Constructs <code>MenuException</code> object.
     *
     * @param message description of error.
     */
    public MenuException(final String message) {
        super(message);
    }
}
