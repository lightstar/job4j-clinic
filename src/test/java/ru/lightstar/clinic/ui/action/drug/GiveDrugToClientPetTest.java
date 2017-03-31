package ru.lightstar.clinic.ui.action.drug;

import org.junit.Test;
import ru.lightstar.clinic.drug.Aspirin;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.ui.action.Action;
import ru.lightstar.clinic.ui.action.ActionTest;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>GiveDrugToClientPet</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class GiveDrugToClientPetTest extends ActionTest {

    /**
     * Constructs <code>GiveDrugToClientPetTest</code> object.
     */
    public GiveDrugToClientPetTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new GiveDrugToClientPet(this.clinicService, this.drugService);
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is("give"));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is("Give drug to client's pet"));
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() throws ActionException, ServiceException {
        this.input.setIterator(Arrays.asList("Masha", "aspirin").iterator());
        this.action.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Client's name:",
                "Drug's name (aspirin, glucose):",
                "Gave aspirin (2) to cat 'Murka'."
        })));
        assertThat(this.drugService.getAllDrugs().get(new Aspirin()), is(1));
    }

    /**
     * Test thrown exception if client doesn't have pet.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithNonExistentPetThenException() throws ActionException {
        this.input.setIterator(Arrays.asList("Vasya", "aspirin").iterator());
        this.action.run();
    }

    /**
     * Test thrown exception if drug doesn't exists in clinic.
     */
    @Test(expected = ActionException.class)
    public void whenRunWithNonExistentDrugThenException() throws ActionException {
        this.input.setIterator(Arrays.asList("Masha", "non-existed").iterator());
        this.action.run();
    }
}
