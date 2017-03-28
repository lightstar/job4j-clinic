package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.io.Output;

/**
 * Cat.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Cat extends Animal {

    /**
     * Cat's type string.
     */
    public final static String TYPE = "cat";

    /**
     * Constructs <code>Cat</code> object.
     *
     * @param name cat's name.
     */
    public Cat(final String name, final Output output) {
        super(TYPE, name, output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        this.output.println("Mew, mew!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction() {
    }
}
