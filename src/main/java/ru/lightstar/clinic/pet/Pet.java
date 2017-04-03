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

    /**
     * Get next pet in chain.
     *
     * @return next pet.
     */
    Pet getNextPet();

    /**
     * Get previous pet in chain.
     *
     * @return previous pet.
     */
    Pet getPrevPet();

    /**
     * Set next pet in chain.
     *
     * @param nextPet next pet.
     */
    void setNextPet(Pet nextPet);

    /**
     * Set previous pet in chain.
     *
     * @param prevPet previous pet.
     */
    void setPrevPet(Pet prevPet);
}
