package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Dog</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DogTest {

    /**
     * <code>ByteArrayOutput</code> used for test of dog's output.
     */
    final ByteArrayOutput byteArrayOutput;

    /**
     * <code>Dog</code> object used in all tests.
     */
    final Dog dog;

    /**
     * Constructs <code>CatTest</code> object.
     */
    public DogTest() {
        this.byteArrayOutput = new ByteArrayOutput();
        this.dog = new Dog("Rex", this.byteArrayOutput);
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenGavGav() {
        this.dog.makeSound();
        assertThat(this.byteArrayOutput.toString(), is(String.format("Gav, gav!%n")));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.dog.setName("Bobik");
        assertThat(this.dog.getName(), is("Bobik"));
    }

    /**
     * Test correctness of <code>getType</code> method.
     */
    @Test
    public void whenGetTypeThenDog() {
        assertThat(this.dog.getType(), is("dog"));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.dog.toString(), is("dog 'Rex'"));
    }
}
