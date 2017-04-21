package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.model.Client;
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
     * Get pet's database id.
     *
     * @return pet's database id.
     */
    int getId();

    /**
     * Set pet's database id.
     */
    void setId(int id);

    /**
     * Get pet's host.
     *
     * @return pet's host.
     */
    Client getClient();

    /**
     * Set pet's host.
     *
     * @param client pet's host.
     */
    void setClient(Client client);

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
     * Get pet's age.
     *
     * @return pet's age.
     */
    int getAge();

    /**
     * Set pet's age.
     *
     * @param age new pet's age.
     */
    void setAge(int age);

    /**
     * Get pet's sex.
     *
     * @return pet's sex.
     */
    Sex getSex();

    /**
     * Set pet's sex.
     *
     * @param sex new pet's sex.
     */
    void setSex(Sex sex);

    /**
     * Get next pet in chain.<br>
     * <strong>Initial value of this property must be <code>Pet.NONE</code>.</strong><br>
     * <strong></b>Only for use by <code>PetList</code> object.</strong>
     *
     * @return next pet.
     */
    Pet getNextPet();

    /**
     * Get previous pet in chain.<br>
     * <strong>Initial value of this property must be <code>Pet.NONE</code>.</strong><br>
     * <strong></b>Only for use by <code>PetList</code> object.</strong>
     *
     * @return previous pet.
     */
    Pet getPrevPet();

    /**
     * Set next pet in chain.<br>
     * <strong></b>Only for use by <code>PetList</code> object.</strong>
     *
     * @param nextPet next pet.
     */
    void setNextPet(Pet nextPet);

    /**
     * Set previous pet in chain.<br>
     * <strong></b>Only for use by <code>PetList</code> object.</strong>
     *
     * @param prevPet previous pet.
     */
    void setPrevPet(Pet prevPet);
}
