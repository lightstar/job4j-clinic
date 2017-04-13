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
     * Client's database id.
     */
    private int id;

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
    private final int position;

    /**
     * Constructs <code>Client</code> object.
     *  @param name client's name.
     * @param pet client's pet.
     * @param position client's position.
     */
    public Client(final String name, final Pet pet, final int position) {
        super();
        this.name = name;
        this.pet = pet;
        this.position = position;
    }

    /**
     * Get client's database id.
     *
     * @return database id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set client's database id.
     *
     * @param id database id.
     */
    public void setId(final int id) {
        this.id = id;
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
}
