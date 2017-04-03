package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>ShowAllPets</code> action tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ShowAllPetsTest extends ActionTest {

    /**
     * Constructs <code>ShowAllPetsTest</code> object.
     */
    public ShowAllPetsTest() throws NameException, ServiceException {
        super();
    }

    @Override
    protected Action createAction() {
        return new ShowAllPets(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("showPets"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Show all pets"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException {
        this.action.run();
        assertThat(this.output.toString(), is(String.format("Pets: cat 'Murka'.%n")));
    }
}
