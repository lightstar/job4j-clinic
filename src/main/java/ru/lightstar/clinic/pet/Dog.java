package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.io.DummyOutput;
import ru.lightstar.clinic.io.Output;

/**
 * Dog.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Dog extends Animal {;

    /**
     * Dog's type string.
     */
    public final static String TYPE = "dog";

    /**
     * Constructs <code>Dog</code> object.
     */
    public Dog() {
        super(TYPE, "", new DummyOutput());
    }

    /**
     * Constructs <code>Dog</code> object.
     *
     * @param name dog's name.
     */
    public Dog(final String name, final Output output) {
        super(TYPE, name, output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        this.getOutput().println("Gav, gav!");
    }
}
