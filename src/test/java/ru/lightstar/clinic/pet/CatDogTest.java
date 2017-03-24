package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.Console;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>CatDog</code> class tests.
 *
 * @author LightStar
 */
public class CatDogTest {

    /**
     * Test correctness of cat-dog's name generation.
     */
    @Test
    public void whenCatDogCreatedThenItHasExpectedName() {
        Console console = new Console();
        CatDog catDog = new CatDog(new Cat("Tom", console), new Dog("Rex", console));
        assertThat(catDog.getName(), is("Tom-Rex"));
    }

    /**
     * Test exception on incorrect name of cat-dog.
     */
    @Test(expected = NameException.class)
    public void whenSetCatDogNameWithoutHyphenThenException() throws NameException {
        Console console = new Console();
        CatDog catDog = new CatDog(new Cat("Tom", console), new Dog("Rex", console));
        catDog.setName("Murka");
    }

    /**
     * Test setting of cat-dog's name.
     */
    @Test
    public void whenSetCatDogNameThenItChanges() throws NameException {
        Console console = new Console();
        CatDog catDog = new CatDog(new Cat("Tom", console), new Dog("Rex", console));
        catDog.setName("Murka-Bobik");
        assertThat(catDog.getName(), is("Murka-Bobik"));
        assertThat(catDog.getCat().getName(), is("Murka"));
        assertThat(catDog.getDog().getName(), is("Bobik"));
    }
}