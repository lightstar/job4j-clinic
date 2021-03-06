package ru.lightstar.clinic.model;

import org.junit.Test;
import ru.lightstar.clinic.io.DummyOutput;
import ru.lightstar.clinic.pet.Cat;
import ru.lightstar.clinic.pet.Dog;
import ru.lightstar.clinic.pet.Pet;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        super();
        this.client = new Client("Vova", new Dog("Bobik", new DummyOutput()), 0);
    }

    /**
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.client.setId(1);
        assertThat(this.client.getId(), is(1));
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
     * Test correctness of <code>setPosition</code> and <code>getPosition</code> method.
     */
    @Test
    public void whenSetPositionThenItChanges() {
        this.client.setPosition(2);
        assertThat(this.client.getPosition(), is(2));
    }

    /**
     * Test correctness of <code>setEmail</code> and <code>getEmail</code> method.
     */
    @Test
    public void whenSetEmailThenItChanges() {
        this.client.setEmail("test@test.ru");
        assertThat(this.client.getEmail(), is("test@test.ru"));
    }

    /**
     * Test correctness of <code>setPhone</code> and <code>getPhone</code> method.
     */
    @Test
    public void whenSetPhoneThenItChanges() {
        this.client.setPhone("123456");
        assertThat(this.client.getPhone(), is("123456"));
    }

    /**
     * Test correctness of <code>setRole</code> and <code>getRole</code> methods.
     */
    @Test
    public void whenSetRoleThenItChanges() {
        this.client.setRole(new Role("admin"));
        assertThat(this.client.getRole().getName(), is("admin"));
    }

    /**
     * Test correctness of <code>setMessages</code> and <code>getMessages</code> methods.
     */
    @Test
    public void whenSetMessagesThenItChanges() {
        this.client.setMessages(Collections.singleton(new Message(this.client, "Test message")));
        assertThat(this.client.getMessages().size(), is(1));
        assertThat(this.client.getMessages().iterator().next().getText(), is("Test message"));
        assertThat(this.client.getMessages().iterator().next().getClient(), is(this.client));
    }

    /**
     * Test correctness of <code>setPassword</code> and <code>getPassword</code> method.
     */
    @Test
    public void whenSetPasswordThenItChanges() {
        this.client.setPassword("123456");
        assertThat(this.client.getPassword(), is("123456"));
    }

    /**
     * Test correctness of <code>toString</code> method.
     */
    @Test
    public void whenToStringThenResult() {
        assertThat(this.client.toString(), is("1. Vova with dog 'Bobik'"));
    }

    /**
     * Test correctness of <code>Client.PlaceHolder</code> object.
     */
    @Test
    public void whenClientPlaceHolderThenItIsImmutableAndHasEmptyFields() {
        Client clientPlaceHolder = new Client.PlaceHolder(1);
        clientPlaceHolder.setId(10);
        clientPlaceHolder.setPosition(10);
        clientPlaceHolder.setName("Test");
        clientPlaceHolder.setEmail("Test");
        clientPlaceHolder.setPhone("Test");
        clientPlaceHolder.setPet(new Cat());
        clientPlaceHolder.setRole(new Role("admin"));
        clientPlaceHolder.setMessages(Collections.singleton(new Message(Client.NONE, "Test message")));
        clientPlaceHolder.setPassword("Test");

        assertThat(clientPlaceHolder.getId(), is(-1));
        assertThat(clientPlaceHolder.getName(), is(""));
        assertThat(clientPlaceHolder.getPosition(), is(1));
        assertThat(clientPlaceHolder.getPet(), is(Pet.NONE));
        assertThat(clientPlaceHolder.getEmail(), is(""));
        assertThat(clientPlaceHolder.getPhone(), is(""));
        assertThat(clientPlaceHolder.getRole().getName(), is(""));
        assertThat(clientPlaceHolder.getMessages().size(), is(0));
        assertThat(clientPlaceHolder.getPassword(), is(""));
    }

    /**
     * Test correctness of <code>Client.NONE</code> object.
     */
    @Test
    public void whenClientNoneThenItIsImmutableAndHasEmptyFields() {
        Client.NONE.setId(10);
        Client.NONE.setPosition(10);
        Client.NONE.setName("Test");
        Client.NONE.setEmail("Test");
        Client.NONE.setPhone("Test");
        Client.NONE.setPet(new Cat());
        Client.NONE.setRole(new Role("admin"));
        Client.NONE.setMessages(Collections.singleton(new Message(Client.NONE, "Test message")));
        Client.NONE.setPassword("Test");

        assertThat(Client.NONE.getId(), is(-1));
        assertThat(Client.NONE.getName(), is(""));
        assertThat(Client.NONE.getPosition(), is(0));
        assertThat(Client.NONE.getPet(), is(Pet.NONE));
        assertThat(Client.NONE.getEmail(), is(""));
        assertThat(Client.NONE.getPhone(), is(""));
        assertThat(Client.NONE.getRole().getName(), is(""));
        assertThat(Client.NONE.getMessages().size(), is(0));
        assertThat(Client.NONE.getPassword(), is(""));
    }
}
