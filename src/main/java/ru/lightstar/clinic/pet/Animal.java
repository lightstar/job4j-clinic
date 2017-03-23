package ru.lightstar.clinic.pet;

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
    private final String name;

    /**
     * Constructs <code>Animal</code> object.
     *
     * @param name animal's name.
     */
    public Animal(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
}
