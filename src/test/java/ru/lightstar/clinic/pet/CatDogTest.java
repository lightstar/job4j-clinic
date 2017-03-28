package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.ByteArrayOutput;

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
     * <code>ByteArrayOutput</code> used for test of cat-dog's output.
     */
    final ByteArrayOutput byteArrayOutput;

    /**
     * <code>CatDog</code> object used in all tests.
     */
    final CatDog catDog;

    /**
     * Constructs <code>CatDog</code> object.
     */
    public CatDogTest() {
        this.byteArrayOutput = new ByteArrayOutput();
        this.catDog = new CatDog(new Cat("Tom", this.byteArrayOutput),
                new Dog("Rex", this.byteArrayOutput));
    }

    /**
     * Test correctness of cat-dog's name generation.
     */
    @Test
    public void whenCatDogCreatedThenItHasExpectedName() {
        assertThat(this.catDog.getName(), is("Tom-Rex"));
    }

    /**
     * Test exception on incorrect input in <code>setName</code> method.
     */
    @Test(expected = NameException.class)
    public void whenSetCatDogNameWithoutHyphenThenException() throws NameException {
        this.catDog.setName("Murka");
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetCatDogNameThenItChanges() throws NameException {
        this.catDog.setName("Murka-Bobik");
        assertThat(this.catDog.getName(), is("Murka-Bobik"));
        assertThat(this.catDog.getCat().getName(), is("Murka"));
        assertThat(this.catDog.getDog().getName(), is("Bobik"));
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenMewMewGavGav() {
        this.catDog.makeSound();
        assertThat(this.byteArrayOutput.toString(), is(String.format("Mew, mew!%nGav, gav!%n")));
    }

    /**
     * Test correctness of <code>getType</code> method.
     */
    @Test
    public void whenGetTypeThenCatDog() {
        assertThat(this.catDog.getType(), is("cat-dog"));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.catDog.toString(), is("cat-dog 'Tom-Rex'"));
    }
}
