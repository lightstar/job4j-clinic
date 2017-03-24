package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.io.Output;

/**
 * Fish.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Fish extends Animal {

    /**
     * Constructs <code>Fish</code> object.
     *
     * @param name fish's name.
     */
    public Fish(final String name, final Output output) {
        super(name, output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        this.output.println("<silence>");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction() {

    }
}
