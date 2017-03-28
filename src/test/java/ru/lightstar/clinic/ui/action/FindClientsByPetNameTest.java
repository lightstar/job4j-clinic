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
 * <code>FindClientsByPetName</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class FindClientsByPetNameTest extends ActionTest {

    /**
     * Constructs <code>FindClientsByPetNameTest</code> object.
     */
    public FindClientsByPetNameTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new FindClientsByPetName(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("findByPet"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Find clients by pet's name"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Collections.singletonList("Murka").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Pet's name:",
                "2. Masha with cat 'Murka'."
        })));
    }

    /**
     * Test correctness of <code>run</code> method with non-existent pet name input.
     */
    @Test
    public void whenRunWithNonExistentPetNameThenNotFound() throws ActionException {
        this.input.setIterator(Collections.singletonList("Bobik").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Pet's name:",
                "No clients found."
        })));
    }
}