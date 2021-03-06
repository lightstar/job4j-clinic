package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.model.Client;

/**
 * 'None' pet used instead of null object. It indicates that there is really no pet at all.
 *
 * @author LightStar
 * @since 0.0.1
 */
final class None implements Pet {

    /**
     * Constructs <code>None</code> object.
     */
    None() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return -1;
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
    public Client getClient() {
        return Client.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClient(final Client client) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "";
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
    public int getAge() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(final int age) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sex getSex() {
        return Sex.M;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSex(final Sex sex) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getNextPet() {
        return Pet.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getPrevPet() {
        return Pet.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextPet(final Pet nextPet) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrevPet(final Pet prevPet) {
    }
}
