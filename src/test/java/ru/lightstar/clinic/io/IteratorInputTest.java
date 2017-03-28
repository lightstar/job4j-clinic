package ru.lightstar.clinic.io;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        final IteratorInput iteratorInput = new IteratorInput(Arrays.asList("Hello", "World!").iterator());

        assertThat(iteratorInput.next(), is("Hello"));
        assertThat(iteratorInput.next(), is("World!"));
    }

    /**
     * Test correctness of <code>setIterator</code> method.
     */
    @Test
    public void whenSetIteratorThenResult() {
        final IteratorInput iteratorInput = new IteratorInput();

        iteratorInput.setIterator(Arrays.asList("Hello", "World!").iterator());

        assertThat(iteratorInput.next(), is("Hello"));
        assertThat(iteratorInput.next(), is("World!"));
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
