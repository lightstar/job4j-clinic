package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Fish</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class FishTest {

    /**
     * <code>ByteArrayOutput</code> used for test of fish's output.
     */
    final ByteArrayOutput byteArrayOutput;

    /**
     * <code>Fish</code> object used in all tests.
     */
    final Fish fish;

    /**
     * Constructs <code>CatTest</code> object.
     */
    public FishTest() {
        super();
        this.byteArrayOutput = new ByteArrayOutput();
        this.fish = new Fish("Beauty", this.byteArrayOutput);
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenSilence() {
        this.fish.makeSound();
        assertThat(this.byteArrayOutput.toString(), is(String.format("<silence>%n")));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.fish.setName("VeryBeauty");
        assertThat(this.fish.getName(), is("VeryBeauty"));
    }

    /**
     * Test correctness of <code>getType</code> method.
     */
    @Test
    public void whenGetTypeThenFish() {
        assertThat(this.fish.getType(), is("fish"));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.fish.toString(), is("fish 'Beauty'"));
    }
}
