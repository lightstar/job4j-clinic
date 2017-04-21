package ru.lightstar.clinic.model;

/**
 * Client's message.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Message extends Base {

    /**
     * Message's author.
     */
    private Client client;

    /**
     * Message's text.
     */
    private String text;

    /**
     * Constructs <code>Message</code> object.
     */
    public Message() {
        super();
        this.client = Client.NONE;
        this.text = "";
    }

    /**
     * Get message's author.
     *
     * @return message's author.
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Set message's author.
     *
     * @param client message's author.
     */
    public void setClient(final Client client) {
        this.client = client;
    }

    /**
     * Get message's text.
     *
     * @return message's text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set message's text.
     *
     * @param text message's text.
     */
    public void setText(final String text) {
        this.text = text;
    }
}
