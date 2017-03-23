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
    private final String name;

    /**
     * Client's pet.
     */
    private final Pet pet;

    /**
     * Constructs <code>Client</code> object.
     *
     * @param name client's name.
     * @param pet client's pet.
     */
    public Client(String name, Pet pet) {
        this.name = name;
        this.pet = pet;
    }
}
