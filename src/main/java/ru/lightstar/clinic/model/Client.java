package ru.lightstar.clinic.model;

import ru.lightstar.clinic.pet.Pet;

import java.util.HashSet;
import java.util.Set;

/**
 * Clinic's client.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Client extends Base {

    /**
     * 'None' client used instead of null object. It indicates that there is really no client at all.
     */
    public static final Client NONE = new None();

    /**
     * Client's name.
     */
    private String name;

    /**
     * Client's pet.
     */
    private Pet pet;

    /**
     * Client's position.
     */
    private int position;

    /**
     * Client's email.
     */
    private String email;

    /**
     * Client's phone.
     */
    private String phone;

    /**
     * Client's role.
     */
    private Role role;

    /**
     * Client's messages.
     */
    private Set<Message> messages;

    /**
     * Client's hashed password.
     */
    private String password;

    /**
     * Constructs <code>Client</code> object.
     */
    public Client() {
        this("", Pet.NONE, 0);
    }

    /**
     * Constructs <code>Client</code> object.
     * @param name client's name.
     * @param pet client's pet.
     * @param position client's position.
     */
    public Client(final String name, final Pet pet, final int position) {
        super();
        this.name = name;
        this.pet = pet;
        this.position = position;
        this.email = "";
        this.phone = "";
        this.role = new Role();
        this.messages = new HashSet<>();
        this.password = "";
    }

    /**
     * Get client's name
     *
     * @return client's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set client's name.
     *
     * @param name new client's name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get client's pet.
     *
     * @return client's pet.
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Set client's pet.
     *
     * @param pet new client's pet.
     */
    public void setPet(final Pet pet) {
        this.pet = pet;
    }

    /**
     * Get client's position.
     *
     * @return client's position.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Set client's position.
     *
     * @param position client's position.
     */
    public void setPosition(final int position) {
        this.position = position;
    }

    /**
     * Get client's email.
     *
     * @return client's email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set client's email.
     *
     * @param email client's email.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get client's phone.
     *
     * @return client's phone.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Set client's phone.
     *
     * @param phone client's phone.
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Get client's role.
     *
     * @return client's role.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Set client's role.
     *
     * @param role client's role.
     */
    public void setRole(final Role role) {
        this.role = role;
    }

    /**
     * Get client's messages.
     *
     * @return client's messages.
     */
    public Set<Message> getMessages() {
        return this.messages;
    }

    /**
     * Set client's messages.
     *
     * @param messages client's messages.
     */
    public void setMessages(final Set<Message> messages) {
        this.messages = messages;
    }

    /**
     * Get client's hashed password.
     *
     * @return client's hashed password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set client's hashed password.
     *
     * @param password client's hashed password.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (this.getPet() == Pet.NONE) {
            return String.format("%d. %s with no pet", this.getPosition() + 1, this.getName());
        } else {
            return String.format("%d. %s with %s", this.getPosition() + 1, this.getName(), this.getPet().toString());
        }
    }

    /**
     * Not real client, just place holder for some future client.
     */
    public static final class PlaceHolder extends Client {

        /**
         * Constructs <code>Holder</code> object.
         */
        public PlaceHolder(final int position) {
            super("", Pet.NONE, position);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setId(final int id) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setName(final String name) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPosition(final int id) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPet(final Pet pet) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setEmail(final String email) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPhone(final String phone) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setRole(final Role role) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setMessages(final Set<Message> messages) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPassword(final String password) {
        }
    }

    /**
     * 'None' client used instead of null object. It indicates that there is really no client at all.
     * Overridden methods make this object immutable.
     */
    private static final class None extends Client {

        /**
         * Constructs <code>None</code> object.
         */
        public None() {
            super();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setId(final int id) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setName(final String name) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPosition(final int id) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPet(final Pet pet) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setEmail(final String email) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPhone(final String phone) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setRole(final Role role) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setMessages(final Set<Message> messages) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPassword(final String password) {
        }
    }
}
