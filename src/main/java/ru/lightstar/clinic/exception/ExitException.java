package ru.lightstar.clinic.exception;

/**
 * Exception thrown when user wants to exit.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ExitException extends ActionException {

    /**
     * Constructs <code>ExitException</code> object.
     */
    public ExitException() {
        super("");
    }
}
