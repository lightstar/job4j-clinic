package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>ShowAllClients</code> action tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ShowAllClientsTest extends ActionTest {

    /**
     * {@inheritDoc}
     */
    public ShowAllClientsTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new ShowAllClients(this.clinicService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("showAll"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Show all clients"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException {
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Clinic size: 3.",
                "1. Vasya with no pet.",
                "2. Masha with cat 'Murka'.",
                "3. VACANT."
        })));
    }
}
