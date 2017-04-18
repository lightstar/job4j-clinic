package ru.lightstar.clinic.io;

import org.junit.Test;
import ru.lightstar.clinic.IoTestHelper;

import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Console</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ConsoleTest {

    /**
     * Helper object used for I/O.
     */
    private final IoTestHelper helper;

    /**
     * Constructs <code>ConsoleTest</code> object.
     */
    public ConsoleTest() {
        super();
        this.helper = new IoTestHelper();
    }

    /**
     * Test correctness of <code>println</code> method.
     */
    @Test
    public void whenPrintlnThenResult() {
        final OutputStream mockedOutput = this.helper.mockStandardOutput();
        final Console console = new Console();

        console.println("Hello, world!");

        assertThat(mockedOutput.toString(), is(String.format("Hello, world!%n")));
    }

    /**
     * Test correctness of <code>next</code> method.
     */
    @Test
    public void whenNextThenResult() {
        this.helper.mockStandardInput(this.helper.joinLines(new String[]{
                "Hello!"
        }).getBytes());

        final Console console = new Console();
        final String result = console.next();

        assertThat(result, is("Hello!"));
    }

    /**
     * Test correctness of <code>ask</code> method.
     */
    @Test
    public void whenAskThenResult() {
        this.helper.mockStandardInput(this.helper.joinLines(new String[]{
                "Boris"
        }).getBytes());
        final OutputStream mockedOutput = this.helper.mockStandardOutput();

        final Console console = new Console();
        final String result = console.ask(console, "What is your name?");

        assertThat(mockedOutput.toString(), is(String.format("What is your name?%n")));
        assertThat(result, is("Boris"));
    }

    /**
     * Test correctness of <code>waitEnter</code> method.
     */
    @Test
    public void whenWaitEnterThenResult() {
        this.helper.mockStandardInput(new byte[]{});
        final OutputStream mockedOutput = this.helper.mockStandardOutput();

        new Console().waitEnter();

        assertThat(mockedOutput.toString(), is(""));
    }
}
