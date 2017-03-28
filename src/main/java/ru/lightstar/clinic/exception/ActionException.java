package ru.lightstar.clinic.exception;

/**
 * Error in UI actions.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ActionException extends Exception {

    /**
     * Constructs <code>ActionException</code> object.
     *
     * @param message description of error.
     */
    public ActionException(final String message) {
        super(message);
    }
}
