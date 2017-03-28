package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>UpdateClientName</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class UpdateClientNameTest extends ActionTest {

    /**
     * Constructs <code>UpdateClientNameTest</code> object.
     */
    public UpdateClientNameTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new UpdateClientName(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("updateName"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Update client's name"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Arrays.asList("Masha", "Nastya").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Client's name:",
                "Client's new name:",
                "Client's name updated."
        })));
        assertThat(this.clinicService.getClientByPosition(1).getName(), is("Nastya"));
    }

    /**
     * Test thrown exception on incorrect user input.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithIncorrectInputThenException() throws ActionException {
        this.input.setIterator(Arrays.asList("Masha", "Vasya").iterator());
        this.action.run();
    }
}