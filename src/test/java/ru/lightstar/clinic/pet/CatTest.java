package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.Client;
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
        super();
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
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.cat.setId(1);
        assertThat(this.cat.getId(), is(1));
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
     * Test correctness of <code>setAge</code> and <code>getAge</code> methods.
     */
    @Test
    public void whenSetAgeThenItChanges() {
        this.cat.setAge(10);
        assertThat(this.cat.getAge(), is(10));
    }

    /**
     * Test correctness of <code>setSex</code> and <code>getSex</code> methods.
     */
    @Test
    public void whenSetSexThenItChanges() {
        this.cat.setSex(Sex.F);
        assertThat(this.cat.getSex(), is(Sex.F));
    }

    /**
     * Test correctness of <code>setClient</code> and <code>getClient</code> methods.
     */
    @Test
    public void whenSetClientThenItChanges() {
        final Client client = new Client();
        this.cat.setClient(client);
        assertThat(this.cat.getClient(), is(client));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.cat.toString(), is("cat 'Murka'"));
    }
}
