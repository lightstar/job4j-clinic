package ru.lightstar.clinic.pet;

/**
 * Dog.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Dog extends Animal {;

    /**
     * Constructs <code>Dog</code> object.
     *
     * @param name dog's name.
     */
    public Dog(final String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        System.out.println("Gav, gav!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction() {
    }
}
