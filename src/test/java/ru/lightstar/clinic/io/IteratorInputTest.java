package ru.lightstar.clinic.io;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>IteratorInput</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class IteratorInputTest {

    /**
     * Test correctness of <code>next</code> method.
     */
    @Test
    public void whenNextThenResult() {
        final ArrayList<String> input = new ArrayList<>();
        input.add("Hello");
        input.add("World!");
        final IteratorInput iteratorInput = new IteratorInput(input.iterator());

        final String resultHello = iteratorInput.next();
        final String resultWorld = iteratorInput.next();

        assertThat(resultHello, is("Hello"));
        assertThat(resultWorld, is("World!"));
    }

    /**
     * Test correctness of <code>ask</code> method.
     */
    @Test
    public void whenAskThenResult() {
        final ArrayList<String> input = new ArrayList<>();
        input.add("Hello");
        final IteratorInput iteratorInput = new IteratorInput(input.iterator());
        final ByteArrayOutput byteArrayOutput = new ByteArrayOutput();

        final String resultHello = iteratorInput.ask(byteArrayOutput, "Hi!");

        assertThat(resultHello, is("Hello"));
        assertThat(byteArrayOutput.toString(), is(String.format("Hi!%n")));
    }
}
