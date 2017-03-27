package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.DummyOutput;

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
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenSilence() {
        final ByteArrayOutput byteArrayOutput = new ByteArrayOutput();
        final Fish fish = new Fish("Beauty", byteArrayOutput);

        fish.makeSound();

        assertThat(byteArrayOutput.toString(), is(String.format("<silence>%n")));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        final DummyOutput dummyOutput = new DummyOutput();
        final Fish fish = new Fish("Beauty", dummyOutput);

        fish.setName("VeryBeauty");

        assertThat(fish.getName(), is("VeryBeauty"));
    }
}
