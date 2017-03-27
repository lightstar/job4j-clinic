package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.DummyOutput;

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
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenMewMew() {
        final ByteArrayOutput byteArrayOutput = new ByteArrayOutput();
        final Cat cat = new Cat("Murka", byteArrayOutput);

        cat.makeSound();

        assertThat(byteArrayOutput.toString(), is(String.format("Mew, mew!%n")));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        final DummyOutput dummyOutput = new DummyOutput();
        final Cat cat = new Cat("Murka", dummyOutput);

        cat.setName("Barsik");

        assertThat(cat.getName(), is("Barsik"));
    }
}
