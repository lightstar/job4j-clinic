package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.Client;
import ru.lightstar.clinic.io.ByteArrayOutput;

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
     * <code>ByteArrayOutput</code> used for test of fish's output.
     */
    final ByteArrayOutput byteArrayOutput;

    /**
     * <code>Fish</code> object used in all tests.
     */
    final Fish fish;

    /**
     * Constructs <code>CatTest</code> object.
     */
    public FishTest() {
        super();
        this.byteArrayOutput = new ByteArrayOutput();
        this.fish = new Fish("Beauty", this.byteArrayOutput);
    }

    /**
     * Test correctness of <code>makeSound</code> method.
     */
    @Test
    public void whenMakeSoundThenSilence() {
        this.fish.makeSound();
        assertThat(this.byteArrayOutput.toString(), is(String.format("<silence>%n")));
    }

    /**
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.fish.setId(1);
        assertThat(this.fish.getId(), is(1));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.fish.setName("VeryBeauty");
        assertThat(this.fish.getName(), is("VeryBeauty"));
    }

    /**
     * Test correctness of <code>getType</code> method.
     */
    @Test
    public void whenGetTypeThenFish() {
        assertThat(this.fish.getType(), is("fish"));
    }

    /**
     * Test correctness of <code>setAge</code> and <code>getAge</code> methods.
     */
    @Test
    public void whenSetAgeThenItChanges() {
        this.fish.setAge(10);
        assertThat(this.fish.getAge(), is(10));
    }

    /**
     * Test correctness of <code>setSex</code> and <code>getSex</code> methods.
     */
    @Test
    public void whenSetSexThenItChanges() {
        this.fish.setSex(Sex.F);
        assertThat(this.fish.getSex(), is(Sex.F));
    }

    /**
     * Test correctness of <code>setClient</code> and <code>getClient</code> methods.
     */
    @Test
    public void whenSetClientThenItChanges() {
        final Client client = new Client();
        this.fish.setClient(client);
        assertThat(this.fish.getClient(), is(client));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.fish.toString(), is("fish 'Beauty'"));
    }
}
