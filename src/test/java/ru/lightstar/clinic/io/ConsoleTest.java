package ru.lightstar.clinic.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>Console</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ConsoleTest {

    /**
     * Test correctness of <code>println</code> method.
     */
    @Test
    public void whenPrintlnThenResult() {
        final OutputStream mockedOutput = this.mockStandardOutput();
        final Console console = new Console();

        console.println("Hello, world!");

        assertThat(mockedOutput.toString(), is(String.format("Hello, world!%n")));
    }

    /**
     * Test correctness of <code>next</code> method.
     */
    @Test
    public void whenNextThenResult() {
        this.mockStandardInput(this.joinLines(new String[]{
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
        this.mockStandardInput(this.joinLines(new String[]{
                "Boris"
        }).getBytes());
        final OutputStream mockedOutput = this.mockStandardOutput();

        final Console console = new Console();
        final String result = console.ask(console, "What is your name?");

        assertThat(mockedOutput.toString(), is(String.format("What is your name?%n")));
        assertThat(result, is("Boris"));
    }

    /**
     * Substitutes standard output stream with byte array stream
     *
     * @return output stream backed by byte array
     */
    private OutputStream mockStandardOutput() {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }

    /**
     * Substitutes standard input stream with byte array stream
     *
     * @param buf byte buffer used as content of byte array stream
     */
    private void mockStandardInput(final byte[] buf) {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
        System.setIn(inputStream);
    }

    /**
     * Helper method used to join lines in one string
     *
     * @param lines joined lines
     * @return result string
     */
    private String joinLines(String[] lines) {
        return String.format("%s%n", String.join(String.format("%n"), lines));
    }
}
