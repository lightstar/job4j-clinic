package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.Client;
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
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.bird.setId(1);
        assertThat(this.bird.getId(), is(1));
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
     * Test correctness of <code>setAge</code> and <code>getAge</code> methods.
     */
    @Test
    public void whenSetAgeThenItChanges() {
        this.bird.setAge(10);
        assertThat(this.bird.getAge(), is(10));
    }

    /**
     * Test correctness of <code>setSex</code> and <code>getSex</code> methods.
     */
    @Test
    public void whenSetSexThenItChanges() {
        this.bird.setSex(Sex.F);
        assertThat(this.bird.getSex(), is(Sex.F));
    }

    /**
     * Test correctness of <code>setClient</code> and <code>getClient</code> methods.
     */
    @Test
    public void whenSetClientThenItChanges() {
        final Client client = new Client();
        this.bird.setClient(client);
        assertThat(this.bird.getClient(), is(client));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.bird.toString(), is("bird 'Beauty'"));
    }
}
