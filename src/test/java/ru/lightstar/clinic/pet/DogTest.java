package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.DummyOutput;

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
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenGavGav() {
        final ByteArrayOutput byteArrayOutput = new ByteArrayOutput();
        final Dog dog = new Dog("Rex", byteArrayOutput);

        dog.makeSound();

        assertThat(byteArrayOutput.toString(), is(String.format("Gav, gav!%n")));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        final DummyOutput dummyOutput = new DummyOutput();
        final Dog dog = new Dog("Rex", dummyOutput);

        dog.setName("Bobik");

        assertThat(dog.getName(), is("Bobik"));
    }
}
