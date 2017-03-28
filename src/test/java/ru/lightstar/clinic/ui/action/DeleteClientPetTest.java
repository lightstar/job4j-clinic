package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.pet.Pet;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>DeleteClientPet</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DeleteClientPetTest extends ActionTest {

    /**
     * Constructs <code>DeleteClientPetTest</code> object.
     */
    public DeleteClientPetTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new DeleteClientPet(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("deletePet"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Delete client's pet"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Collections.singletonList("Masha").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Client's name:",
                "Client's pet deleted."
        })));
        assertThat(this.clinicService.getClientByPosition(1).getPet(), is(Pet.NONE));
    }

    /**
     * Test thrown exception on incorrect user input.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithIncorrectInputThenException() throws ActionException {
        this.input.setIterator(Collections.singletonList("Vasya").iterator());
        this.action.run();
    }
}