package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Bird</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class BirdTest {

    /**
     * <code>ByteArrayOutput</code> used for test of bird's output.
     */
    final ByteArrayOutput byteArrayOutput;

    /**
     * <code>Bird</code> object used in all tests.
     */
    final Bird bird;

    /**
     * Constructs <code>BirdTest</code> object.
     */
    public BirdTest() {
        super();
        this.byteArrayOutput = new ByteArrayOutput();
        this.bird = new Bird("Beauty", this.byteArrayOutput);
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenChirpChirp() {
        this.bird.makeSound();
        assertThat(this.byteArrayOutput.toString(), is(String.format("Chirp, chirp!%n")));

    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.bird.setName("VeryBeauty");
        assertThat(this.bird.getName(), is("VeryBeauty"));
    }

    /**
     * Test correctness of <code>getType</code> method.
     */
    @Test
    public void whenGetTypeThenBird() {
        assertThat(this.bird.getType(), is("bird"));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.bird.toString(), is("bird 'Beauty'"));
    }
}
