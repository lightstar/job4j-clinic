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
     * Animal's name.
     */
    private String name;

    /**
     * Output used for sounds.
     */
    protected final Output output;

    /**
     * Constructs <code>Animal</code> object.
     *
     * @param name animal's name.
     * @param output output used for sounds.
     */
    public Animal(final String name, final Output output) {
        this.name = name;
        this.output = output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
}
