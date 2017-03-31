package ru.lightstar.clinic.ui.action.drug;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.ui.action.Action;
import ru.lightstar.clinic.ui.action.ActionTest;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>DrugAction</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DrugActionTest extends ActionTest {

    /**
     * Name of generic action used for tests.
     */
    private final static String TEST_ACTION_NAME = "TestAction";

    /**
     * Description of generic action used for tests.
     */
    private final static String TEST_ACTION_DESCRIPTION = "Test Drug Action";

    /**
     * Constructs <code>DrugActionTest</code> object.
     */
    public DrugActionTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new DrugAction(TEST_ACTION_NAME, TEST_ACTION_DESCRIPTION, this.clinicService, this.drugService) {
            @Override
            public void run() {
            }
        };
    }

    /**
     * Test correctness of <code>getDrugService</code> method.
     */
    @Test
    public void whenGetDrugServiceThenResult() {
        assertThat(((DrugAction)this.action).getDrugService(), is(this.drugService));
    }

    /**
     * Test correctness of <code>askDrugName</code> method.
     */
    @Test
    public void whenAskDrugNameThenResult() throws ActionException {
        this.input.setIterator(Collections.singletonList("aspirin").iterator());
        final String drugName = ((DrugAction)this.action).askDrugName();

        assertThat(this.output.toString(), is(String.format("Drug's name (aspirin, glucose):%n")));
        assertThat(drugName, is("aspirin"));
    }
}
