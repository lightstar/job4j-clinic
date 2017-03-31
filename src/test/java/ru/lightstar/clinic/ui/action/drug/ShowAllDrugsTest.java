package ru.lightstar.clinic.ui.action.drug;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.ui.action.Action;
import ru.lightstar.clinic.ui.action.ActionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>ShowAllDrugs</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ShowAllDrugsTest extends ActionTest {

    /**
     * Constructs <code>ShowAllDrugsTest</code> object.
     */
    public ShowAllDrugsTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new ShowAllDrugs(this.clinicService, this.drugService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("show"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Show all drugs"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException {
        this.action.run();
        assertThat(this.output.toString(), is(String.format("Drugs: aspirin (2) - 2, glucose (1).%n")));
    }
}
