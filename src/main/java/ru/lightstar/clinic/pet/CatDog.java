package ru.lightstar.clinic.pet;

/**
 * Cat-dog, mystical creature.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class CatDog implements Pet {

    /**
     * Inner <code>Cat</code> object.
     */
    private final Cat cat;

    /**
     * Inner <code>Dog</code> object.
     */
    private final Dog dog;

    /**
     * Constructs <code>CatDog</code> object.
     *
     * @param cat inner cat.
     * @param dog inner dog.
     */
    public CatDog(final Cat cat, final Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeSound() {
        this.cat.makeSound();
        this.dog.makeSound();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction() {
        this.cat.doAction();
        this.dog.doAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.format("%s-%s", this.cat.getName(), this.dog.getName());
    }
}
