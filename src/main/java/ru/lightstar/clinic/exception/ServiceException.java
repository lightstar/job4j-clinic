package ru.lightstar.clinic.exception;

/**
 * Error in clinic service execution.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ServiceException extends Exception {

    /**
     * Constructs <code>ServiceException</code> object.
     *
     * @param message description of error.
     */
    public ServiceException(String message) {
        super(message);
    }
}
