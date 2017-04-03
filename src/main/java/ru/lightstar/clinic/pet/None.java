package ru.lightstar.clinic.pet;

/**
 * 'None' pet used instead of null object. It indicates that there is really no pet at all.
 *
 * @author LightStar
 * @since 0.0.1
 */
class None implements Pet {

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
