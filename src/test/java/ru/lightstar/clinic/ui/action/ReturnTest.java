package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ReturnException;
import ru.lightstar.clinic.exception.ServiceException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Return</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ReturnTest extends ActionTest {

    /**
     * Constructs <code>ReturnTest</code> object.
     */
    public ReturnTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new Return(this.output);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("return"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Return back"));
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
            assertThat(this.output.toString(), is(String.format("Returning back.%n")));
        }
    }
}
