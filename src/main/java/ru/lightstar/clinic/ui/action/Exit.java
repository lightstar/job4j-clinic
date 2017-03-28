package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.exception.ExitException;
import ru.lightstar.clinic.io.Output;

/**
 * Exit action.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Exit implements Action {

    /**
     * <code>Output</code> object used by this action.
     */
    private final Output output;

    /**
     * Constructs <code>Exit</code> object.
     *
     * @param output <code>Output</code> object used by this action.
     */
    public Exit(final Output output) {
        this.output = output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "exit";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Exit from program";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ExitException {
        this.output.println("Bye, bye!");
        throw new ExitException();
    }
}
