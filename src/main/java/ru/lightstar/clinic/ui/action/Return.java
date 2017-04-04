package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ReturnException;
import ru.lightstar.clinic.io.Output;

/**
 * Return from menu action.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Return implements Action {

    /**
     * <code>Output</code> object used by this action.
     */
    private final Output output;

    /**
     * Constructs <code>Return</code> object.
     *
     * @param output <code>Output</code> object used by this action.
     */
    public Return(final Output output) {
        super();
        this.output = output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "return";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Return back";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        this.getOutput().println("Returning back.");
        throw new ReturnException();
    }

    /**
     * Get <code>Output</code> object used by this action.
     *
     * @return <code>Output</code> object.
     */
    public Output getOutput() {
        return this.output;
    }
}
