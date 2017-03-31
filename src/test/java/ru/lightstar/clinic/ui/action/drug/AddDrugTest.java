package ru.lightstar.clinic.ui.action.drug;

import org.junit.Test;
import ru.lightstar.clinic.drug.Aspirin;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.ui.action.Action;
import ru.lightstar.clinic.ui.action.ActionTest;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>AddDrug</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class AddDrugTest extends ActionTest {

    /**
     * Constructs <code>AddDrugTest</code> object.
     */
    public AddDrugTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new AddDrug(this.clinicService, this.drugService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("add"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Add new drug"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Collections.singletonList("aspirin").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Drug's name (aspirin, glucose):",
                "Drug added."
        })));
        assertThat(this.drugService.getAllDrugs().get(new Aspirin()), is(3));
    }

    /**
     * Test thrown exception on incorrect user input.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithIncorrectInputThenException() throws ActionException {
        this.input.setIterator(Collections.singletonList("unknown").iterator());
        this.action.run();
    }
}
