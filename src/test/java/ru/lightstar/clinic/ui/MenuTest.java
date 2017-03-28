package ru.lightstar.clinic.ui;

import org.junit.Test;
import ru.lightstar.clinic.IoTestHelper;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.MenuException;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.IteratorInput;
import ru.lightstar.clinic.ui.action.Action;

import java.util.Collections;

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
        this.helper = new IoTestHelper();
        this.input = new IteratorInput();
        this.output = new ByteArrayOutput();
        this.menu = new Menu(input, output);
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
    }

    /**
     * Test correctness of <code>addAction</code> and <code>getActions</code> methods.
     */
    @Test
    public void whenAddActionThenItAdds() {
        this.menu.addAction(this.action);
        assertThat(this.menu.getActions(), is(Collections.singletonMap(TEST_ACTION_NAME, this.action)));
    }

    /**
     * Test correctness of <code>show</code> method.
     */
    @Test
    public void whenShowTheItShows() {
        this.menu.addAction(this.action);

        this.menu.show();

        assertThat(this.output.toString(),
                is(this.helper.joinLines(new String[]{
                        "Menu:",
                        "-----",
                        String.format("%s (%s).", TEST_ACTION_DESCRIPTION, TEST_ACTION_NAME),
                        ""
                }))
        );
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenItRuns() throws Exception {
        this.menu.addAction(this.action);

        this.input.setIterator(Collections.singletonList(this.action.getName()).iterator());
        this.menu.run();

        assertThat(this.output.toString(), is(this.helper.joinLines(new String[]{
                "Choose action:",
                TEST_ACTION_RUN_STRING
        })));
    }

    /**
     * Test that method <code>rune</code> throws exception for unknown user action.
     */
    @Test(expected = MenuException.class)
    public void whenWrongActionThenException() throws Exception {
        this.menu.addAction(this.action);

        this.input.setIterator(Collections.singletonList("Unknown").iterator());
        this.menu.run();
    }
}
