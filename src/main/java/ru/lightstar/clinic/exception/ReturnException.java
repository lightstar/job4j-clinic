package ru.lightstar.clinic.exception;

/**
 * Exception thrown when user wants to return to previous menu.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ReturnException extends ActionException {

    /**
     * Constructs <code>ReturnException</code> object.
     */
    public ReturnException() {
        super("");
    }
}
