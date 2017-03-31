package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Exit</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ExitTest extends ActionTest {

    /**
     * Constructs <code>ExitTest</code> object.
     */
    public ExitTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new Exit(this.output);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("exit"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Exit from program"));
    }

    /**
     * Test correct exception thrown in <code>run</code> method.
     */
    @Test(expected = ReturnException.class)
    public void whenRunThenException() throws ActionException {
        this.action.run();
    }

    /**
     * Test correct output in <code>run</code> method.
     */
    @Test
    public void whenRunThenByeBye() throws ActionException {
        try {
            this.action.run();
        } catch(ReturnException e) {
            assertThat(this.output.toString(), is(String.format("Bye, bye!%n")));
        }
    }
}
