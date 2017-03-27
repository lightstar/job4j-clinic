package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.DummyOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>CatDog</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class CatDogTest {

    /**
     * Test correctness of cat-dog's name generation.
     */
    @Test
    public void whenCatDogCreatedThenItHasExpectedName() {
        final DummyOutput dummyOutput = new DummyOutput();
        final CatDog catDog = new CatDog(new Cat("Tom", dummyOutput), new Dog("Rex", dummyOutput));

        assertThat(catDog.getName(), is("Tom-Rex"));
    }

    /**
     * Test exception on incorrect input in <code>setName</code> method.
     */
    @Test(expected = NameException.class)
    public void whenSetCatDogNameWithoutHyphenThenException() throws NameException {
        final DummyOutput dummyOutput = new DummyOutput();
        final CatDog catDog = new CatDog(new Cat("Tom", dummyOutput), new Dog("Rex", dummyOutput));

        catDog.setName("Murka");
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetCatDogNameThenItChanges() throws NameException {
        final DummyOutput dummyOutput = new DummyOutput();
        final CatDog catDog = new CatDog(new Cat("Tom", dummyOutput), new Dog("Rex", dummyOutput));

        catDog.setName("Murka-Bobik");

        assertThat(catDog.getName(), is("Murka-Bobik"));
        assertThat(catDog.getCat().getName(), is("Murka"));
        assertThat(catDog.getDog().getName(), is("Bobik"));
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenMewMewGavGav() {
        final ByteArrayOutput byteArrayOutput = new ByteArrayOutput();
        final CatDog catDog = new CatDog(new Cat("Tom", byteArrayOutput), new Dog("Rex", byteArrayOutput));

        catDog.makeSound();

        assertThat(byteArrayOutput.toString(), is(String.format("Mew, mew!%nGav, gav!%n")));
    }
}
