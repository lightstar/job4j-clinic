package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.io.Output;

/**
 * Abstract <code>Animal</code>.
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class Animal implements Pet {

    /**
     * Animal's type.
     */
    private final String type;

    /**
     * Animal's name.
     */
    private String name;

    /**
     * Output used for sounds.
     */
    private final Output output;

    /**
     * Next pet in chain.
     */
    private Pet nextPet;

    /**
     * Previous pet in chain.
     */
    private Pet prevPet;

    /**
     * Constructs <code>Animal</code> object.
     *
     * @param type animal's type.
     * @param name animal's name.
     * @param output output used for sounds.
     */
    public Animal(final String type, final String name, final Output output) {
        super();
        this.type = type;
        this.name = name;
        this.output = output;
        this.nextPet = Pet.NONE;
        this.prevPet = Pet.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getNextPet() {
        return this.nextPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextPet(final Pet nextPet) {
        this.nextPet = nextPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrevPet(final Pet prevPet) {
        this.prevPet = prevPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getPrevPet() {
        return this.prevPet;
    }

    /**
     * Get <code>Output</code> object used by animal.
     * @return <code>Output</code> object.
     */
    public Output getOutput() {
        return this.output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s '%s'", this.getType(), this.getName());
    }
}
