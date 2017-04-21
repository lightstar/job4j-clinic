package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.model.Client;
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
        super();
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
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.dog.setId(1);
        assertThat(this.dog.getId(), is(1));
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
     * Test correctness of <code>setAge</code> and <code>getAge</code> methods.
     */
    @Test
    public void whenSetAgeThenItChanges() {
        this.dog.setAge(10);
        assertThat(this.dog.getAge(), is(10));
    }

    /**
     * Test correctness of <code>setSex</code> and <code>getSex</code> methods.
     */
    @Test
    public void whenSetSexThenItChanges() {
        this.dog.setSex(Sex.F);
        assertThat(this.dog.getSex(), is(Sex.F));
    }

    /**
     * Test correctness of <code>setClient</code> and <code>getClient</code> methods.
     */
    @Test
    public void whenSetClientThenItChanges() {
        final Client client = new Client();
        this.dog.setClient(client);
        assertThat(this.dog.getClient(), is(client));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.dog.toString(), is("dog 'Rex'"));
    }
}
