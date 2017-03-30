package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.exception.NameException;

/**
 * Describes pet behavior.
 *
 * @author LightStar
 * @since 0.0.1
 */
public interface Pet {

    /**
     * Pet object used instead of null. Indicates that there is really no pet at all.
     */
    Pet NONE = new None();

    /**
     * Make some sound.
     */
    void makeSound();

    /**
     * Get pet's type.
     *
     * @return pet's type.
     */
    String getType();

    /**
     * Get pet's name.
     *
     * @return pet's name.
     */
    String getName();

    /**
     * Set pet's name.
     *
     * @param name new pet's name.
     */
    void setName(String name) throws NameException;
}
