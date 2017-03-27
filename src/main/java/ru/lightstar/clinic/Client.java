package ru.lightstar.clinic;

import ru.lightstar.clinic.pet.Pet;

/**
 * Clinic's client.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Client {

    /**
     * Client's name.
     */
    private String name;

    /**
     * Client's pet.
     */
    private Pet pet;

    /**
     * Constructs <code>Client</code> object.
     *
     * @param name client's name.
     * @param pet client's pet.
     */
    public Client(final String name, final Pet pet) {
        super();
        this.name = name;
        this.pet = pet;
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
}
