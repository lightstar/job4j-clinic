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
     * Pet's database id.
     */
    private int id;

    /**
     * Animal's type.
     */
    private final String type;

    /**
     * Animal's name.
     */
    private String name;

    /**
     * Animal's age.
     */
    private int age;

    /**
     * Animal's sex.
     */
    private Sex sex;

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
        this.age = 0;
        this.sex = Sex.M;
        this.output = output;
        this.nextPet = Pet.NONE;
        this.prevPet = Pet.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final int id) {
        this.id = id;
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
    public int getAge() {
        return this.age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sex getSex() {
        return this.sex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSex(final Sex sex) {
        this.sex = sex;
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
