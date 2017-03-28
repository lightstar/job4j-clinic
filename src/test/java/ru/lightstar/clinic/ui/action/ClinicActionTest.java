package ru.lightstar.clinic.ui.action;

import org.junit.Test;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>ClinicAction</code> generic action tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicActionTest extends ActionTest {

    /**
     * Name of generic action used for tests.
     */
    private final static String TEST_ACTION_NAME = "TestAction";

    /**
     * Description of generic action used for tests.
     */
    private final static String TEST_ACTION_DESCRIPTION = "Test Clinic Action";

    /**
     * Constructs <code>ClinicActionTest</code> object.
     */
    public ClinicActionTest() throws NameException, ServiceException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Action createAction() {
        return new ClinicAction(TEST_ACTION_NAME, TEST_ACTION_DESCRIPTION, this.clinicService) {
            @Override
            public void run() {
            }
        };
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.action.getName(), is(TEST_ACTION_NAME));
    }

    /**
     * Test correctness of <code>getDescription</code> object.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.action.getDescription(), is(TEST_ACTION_DESCRIPTION));
    }

    /**
     * Test correctness of <code>getInput</code> method.
     */
    @Test
    public void whenGetInputThenResult() {
        assertThat(((ClinicAction)this.action).getInput(), is(this.input));
    }

    /**
     * Test correctness of <code>getOutput</code> method.
     */
    @Test
    public void whenGetOutputThenResult() {
        assertThat(((ClinicAction)this.action).getOutput(), is(this.output));
    }

    /**
     * Test correctness of <code>getClinicService</code> method.
     */
    @Test
    public void whenGetClinicServiceThenResult() {
        assertThat(((ClinicAction)this.action).getClinicService(), is(this.clinicService));
    }

    /**
     * Test correctness of <code>ask</code> method.
     */
    @Test
    public void whenAskThenResult() {
        this.input.setIterator(Collections.singletonList("answer").iterator());
        String answer = ((ClinicAction)this.action).ask("Test question");

        assertThat(this.output.toString(), is(String.format("Test question%n")));
        assertThat(answer, is("answer"));
    }

    /**
     * Test correctness of <code>askPosition</code> method.
     */
    @Test
    public void whenAskPositionThenResult() throws ActionException {
        this.input.setIterator(Collections.singletonList("5").iterator());
        int position = ((ClinicAction)this.action).askPosition();

        assertThat(this.output.toString(), is(String.format("Client's position:%n")));
        assertThat(position, is(4));
    }

    /**
     * Test exception in <code>askPosition</code> method thrown on incorrect input.
     */
    @Test(expected = ActionException.class)
    public void whenAskPositionAndAnswerNonNumberThenException() throws ActionException {
        this.input.setIterator(Collections.singletonList("non-number").iterator());
        ((ClinicAction)this.action).askPosition();
    }
}
