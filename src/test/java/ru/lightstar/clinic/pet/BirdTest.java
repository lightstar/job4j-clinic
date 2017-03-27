package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.DummyOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>Bird</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class BirdTest {

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenChirpChirp() {
        final ByteArrayOutput byteArrayOutput = new ByteArrayOutput();
        final Bird bird = new Bird("Beauty", byteArrayOutput);

        bird.makeSound();

        assertThat(byteArrayOutput.toString(), is(String.format("Chirp, chirp!%n")));

    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        final DummyOutput dummyOutput = new DummyOutput();
        final Bird bird = new Bird("Beauty", dummyOutput);

        bird.setName("VeryBeauty");

        assertThat(bird.getName(), is("VeryBeauty"));
    }
}