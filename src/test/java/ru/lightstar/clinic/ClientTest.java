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
        this.client = new Client("Vova", new Dog("Bobik", new DummyOutput()));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.client.setName("Vanya");
        assertThat(client.getName(), is("Vanya"));
    }

    /**
     * Test correctness of <code>setPet</code> and <code>getPet</code> methods.
     */
    @Test
    public void whenSetPetThenItChanges() {
        this.client.setPet(new Cat("Murka", new DummyOutput()));
        assertThat(client.getPet(), instanceOf(Cat.class));
        assertThat(client.getPet().getName(), is("Murka"));
    }
}
