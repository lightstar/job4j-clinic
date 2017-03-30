package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.io.Output;

/**
 * Bird.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Bird extends Animal {

    /**
     * Bird's type string.
     */
    public final static String TYPE = "bird";

    /**
     * Constructs <code>Bird</code> object.
     *
     * @param name bird's name.
     */
    public Bird(final String name, final Output output) {
        super(TYPE, name, output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        this.output.println("Chirp, chirp!");
    }
}
