package ru.lightstar.clinic.exception;

/**
 * Error in some's name.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class NameException extends Exception {

    /**
     * Constructs <code>NameException</code> object.
     *
     * @param message description of error.
     */
    public NameException(String message) {
        super(message);
    }
}
