package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Cat</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class CatTest {

    /**
     * <code>ByteArrayOutput</code> used for test of cat's output.
     */
    final ByteArrayOutput byteArrayOutput;

    /**
     * <code>Cat</code> object used in all tests.
     */
    final Cat cat;

    /**
     * Constructs <code>CatTest</code> object.
     */
    public CatTest() {
        this.byteArrayOutput = new ByteArrayOutput();
        this.cat = new Cat("Murka", this.byteArrayOutput);
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenMewMew() {
        this.cat.makeSound();
        assertThat(this.byteArrayOutput.toString(), is(String.format("Mew, mew!%n")));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.cat.setName("Barsik");
        assertThat(this.cat.getName(), is("Barsik"));
    }

    /**
     * Test correctness of <code>getType</code> method.
     */
    @Test
    public void whenGetTypeThenCat() {
        assertThat(this.cat.getType(), is("cat"));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.cat.toString(), is("cat 'Murka'"));
    }
}
