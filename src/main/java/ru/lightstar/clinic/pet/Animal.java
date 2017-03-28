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
    protected final Output output;

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
    public String toString() {
        return String.format("%s '%s'", this.getType(), this.getName());
    }
}
