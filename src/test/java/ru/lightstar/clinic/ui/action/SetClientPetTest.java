package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>SetClientPet</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class SetClientPetTest extends ActionTest {

    /**
     * Constructs <code>SetClientPetTest</code> object.
     */
    public SetClientPetTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new SetClientPet(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("setPet"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Set client's pet"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Arrays.asList("Vasya", "dog", "Bobik").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Client's name:",
                "Pet's type (bird, fish, cat, dog, cat-dog):",
                "Pet's name:",
                "Pet was set."
        })));
        assertThat(this.clinicService.getClientByPosition(0).getPet().getType(), is("dog"));
        assertThat(this.clinicService.getClientByPosition(0).getPet().getName(), is("Bobik"));
    }

    /**
     * Test thrown exception on incorrect user input.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithIncorrectInputThenException() throws ActionException {
        this.input.setIterator(Arrays.asList("Vasya", "unknown", "Name").iterator());
        this.action.run();
    }
}
