package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.io.DummyOutput;
import ru.lightstar.clinic.pet.Cat;
import ru.lightstar.clinic.pet.Dog;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * <code>Client</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClientTest {

    /**
     * <code>Client</code> object used in all tests.
     */
    private final Client client;

    /**
     * Constructs <code>ClientTest</code> object.
     */
    public ClientTest() {
        this.client = new Client("Vova", new Dog("Bobik", new DummyOutput()), 0);
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.client.setName("Vanya");
        assertThat(this.client.getName(), is("Vanya"));
    }

    /**
     * Test correctness of <code>setPet</code> and <code>getPet</code> methods.
     */
    @Test
    public void whenSetPetThenItChanges() {
        this.client.setPet(new Cat("Murka", new DummyOutput()));
        assertThat(this.client.getPet(), instanceOf(Cat.class));
        assertThat(this.client.getPet().getName(), is("Murka"));
    }

    /**
     * Test correctness of <code>getPosition</code> method.
     */
    @Test
    public void whenGetPositionThenResult() {
        assertThat(this.client.getPosition(), is(0));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.client.toString(), is("1. Vova with dog 'Bobik'"));
    }
}