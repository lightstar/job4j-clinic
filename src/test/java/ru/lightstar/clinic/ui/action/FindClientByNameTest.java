package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>FindClientByName</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class FindClientByNameTest extends ActionTest {

    /**
     * Constructs <code>FindClientByNameTest</code> object.
     */
    public FindClientByNameTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new FindClientByName(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("find"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Find client by name"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Collections.singletonList("Vasya").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Client's name:",
                "1. Vasya with no pet."
        })));
    }

    /**
     * Test thrown exception on incorrect user input.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithIncorrectInputThenException() throws ActionException {
        this.input.setIterator(Collections.singletonList("Vova").iterator());
        this.action.run();
    }
}
