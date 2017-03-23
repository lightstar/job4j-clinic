package ru.lightstar.clinic.pet;

/**
 * Cat.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Cat extends Animal {

    /**
     * Constructs <code>Cat</code> object.
     *
     * @param name cat's name.
     */
    public Cat(final String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        System.out.println("Mew, mew!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction() {
    }
}
