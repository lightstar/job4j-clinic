package ru.lightstar.clinic.model;

import org.junit.Test;
import ru.lightstar.clinic.pet.Pet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>Message</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class MessageTest {

    /**
     * <code>Message</code> object used in all tests.
     */
    private final Message message;

    /**
     * Constructs <code>MessageTest</code> object.
     */
    public MessageTest() {
        super();
        this.message = new Message();
    }

    /**
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.message.setId(1);
        assertThat(this.message.getId(), is(1));
    }

    /**
     * Test correctness of <code>setClient</code> and <code>getClient</code> methods.
     */
    @Test
    public void whenSetClientThenItChanges() {
        final Client client = new Client("Vasya", Pet.NONE, 0);
        this.message.setClient(client);
        assertThat(this.message.getClient(), is(client));
    }

    /**
     * Test correctness of <code>setText</code> and <code>getText</code> methods.
     */
    @Test
    public void whenSetTextThenItChanges() {
        this.message.setText("Test message");
        assertThat(this.message.getText(), is("Test message"));
    }
}