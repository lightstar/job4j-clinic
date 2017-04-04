package ru.lightstar.clinic.ui;

import org.junit.Test;
import ru.lightstar.clinic.IoTestHelper;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.MenuException;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.IteratorInput;
import ru.lightstar.clinic.ui.action.Action;
import ru.lightstar.clinic.ui.action.Return;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Menu</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class MenuTest {

    /**
     * Name of test menu.
     */
    private final static String TEST_MENU_NAME = "test";

    /**
     * Description of test menu.
     */
    private final static String TEST_MENU_DESCRIPTION = "Test menu";

    /**
     * Name of action used in tests.
     */
    private final static String TEST_ACTION_NAME = "TestAction";

    /**
     * Description of action used in tests.
     */
    private final static String TEST_ACTION_DESCRIPTION = "This is a Test Action";

    /**
     * String used inside run method of test action.
     */
    private final static String TEST_ACTION_RUN_STRING = "Running Test Action";

    /**
     * Helper object used for I/O.
     */
    private final IoTestHelper helper;

    /**
     * <code>Input</code> object used for I/O.
     */
    private final IteratorInput input;

    /**
     * <code>Output</code> object used for I/O.
     */
    private final ByteArrayOutput output;

    /**
     * <code>Menu</code> object used in all tests.
     */
    private final Menu menu;

    /**
     * <code>Action</code> object used in all tests.
     */
    private final Action action;

    /**
     * Constructs <code>MenuTest</code> object.
     */
    public MenuTest() {
        super();
        this.helper = new IoTestHelper();
        this.input = new IteratorInput();
        this.output = new ByteArrayOutput();
        this.menu = new Menu(TEST_MENU_NAME, TEST_MENU_DESCRIPTION, input, output);
        this.action = new Action() {
            @Override
            public String getName() {
                return TEST_ACTION_NAME;
            }

            @Override
            public String getDescription() {
                return TEST_ACTION_DESCRIPTION;
            }

            @Override
            public void run() throws ActionException {
                MenuTest.this.output.println(TEST_ACTION_RUN_STRING);
            }
        };
        this.menu.addAction(this.action);
        this.menu.addAction(new Return(this.output));
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.menu.getName(), is(TEST_MENU_NAME));
    }

    /**
     * Test correctness of <code>getDescription</code> method.
     */
    @Test
    public void whenGetDescriptionThenResult() {
        assertThat(this.menu.getDescription(), is(TEST_MENU_DESCRIPTION));
    }

    /**
     * Test correctness of <code>addAction</code> and <code>getActions</code> methods.
     */
    @Test
    public void whenAddActionThenItAdds() {
        assertThat(this.menu.getActions().get(TEST_ACTION_NAME), is(this.action));
        assertThat(this.menu.getActions().get("return"), instanceOf(Return.class));
    }

    /**
     * Test correctness of <code>show</code> method.
     */
    @Test
    public void whenShowTheItShows() {
        this.menu.show();

        assertThat(this.output.toString(),
                is(this.helper.joinLines(new String[]{
                        "Test menu:",
                        "----------",
                        String.format("%s (%s).", TEST_ACTION_DESCRIPTION, TEST_ACTION_NAME),
                        "Return back (return).",
                        ""
                }))
        );
    }

    /**
     * Test correctness of <code>runAction</code> method.
     */
    @Test
    public void whenRunActionThenItRuns() throws ActionException, MenuException {
        this.input.setIterator(Collections.singletonList(TEST_ACTION_NAME).iterator());
        this.menu.runAction();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Choose action:",
                TEST_ACTION_RUN_STRING
        })));
    }

    /**
     * Test that method <code>runAction</code> throws exception for unknown user action.
     */
    @Test(expected = MenuException.class)
    public void whenWrongActionThenException() throws ActionException, MenuException {
        this.input.setIterator(Collections.singletonList("unknown").iterator());
        this.menu.runAction();
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenItRuns() {
        this.input.setIterator(Arrays.asList(new String[]{
                TEST_ACTION_NAME,
                "return"
        }).iterator());

        this.menu.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Test menu:",
                "----------",
                String.format("%s (%s).", TEST_ACTION_DESCRIPTION, TEST_ACTION_NAME),
                "Return back (return).",
                "",
                "Choose action:",

                TEST_ACTION_RUN_STRING,
                "Press 'Enter' to continue...",

                "Test menu:",
                "----------",
                String.format("%s (%s).", TEST_ACTION_DESCRIPTION, TEST_ACTION_NAME),
                "Return back (return).",
                "",
                "Choose action:",

                "Returning back."
        })));
    }
}
