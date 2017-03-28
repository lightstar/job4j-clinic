package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>UpdateClientPetName</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class UpdateClientPetNameTest extends ActionTest {

    /**
     * Constructs <code>UpdateClientPetNameTest</code> object.
     */
    public UpdateClientPetNameTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new UpdateClientPetName(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("updatePetName"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Update client pet's name"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Arrays.asList("Masha", "Beauty").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Client's name:",
                "Client pet's new name:",
                "Client pet's name updated."
        })));
        assertThat(this.clinicService.getClientByPosition(1).getPet().getName(), is("Beauty"));
    }

    /**
     * Test thrown exception on incorrect user input.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithIncorrectInputThenException() throws ActionException {
        this.input.setIterator(Arrays.asList("Vova", "Beauty").iterator());
        this.action.run();
    }
}