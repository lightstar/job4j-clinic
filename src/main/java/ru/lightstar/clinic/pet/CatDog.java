package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.exception.NameException;

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
     * Get inner <code>Cat</code> object.
     *
     * @return inner cat.
     */
    public Cat getCat() {
        return cat;
    }

    /**
     * Get inner <code>Dog</code> object.
     *
     * @return inner dog.
     */
    public Dog getDog() {
        return dog;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) throws NameException {
        String[] nameArray = name.split("-");
        if (nameArray.length != 2) {
            throw new NameException(String.format("Wrong cat-dog's name: %s", name));
        }
        this.cat.setName(nameArray[0]);
        this.dog.setName(nameArray[1]);
    }

}
