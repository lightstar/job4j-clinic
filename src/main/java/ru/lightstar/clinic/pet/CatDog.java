package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.Output;

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
     * Constructs <code>CatDog</code> object from given cat and dog objects.
     *
     * @param cat inner cat.
     * @param dog inner dog.
     */
    public CatDog(final Cat cat, final Dog dog) {
        super();
        this.cat = cat;
        this.dog = dog;
    }

    /**
     * Constructs <code>CatDog</code> object.
     * @param name cat-dog's name. Must be in format '&lt;cat_name&gt;_&lt;dog_name&gt;'.
     * @param output output used for sounds.
     * @throws NameException thrown when given name doesn't fit correct format.
     */
    public CatDog(final String name, final Output output) throws NameException {
        String[] nameArray = this.splitName(name);
        this.cat = new Cat(nameArray[0], output);
        this.dog = new Dog(nameArray[1], output);
    }

    /**
     * Get inner <code>Cat</code> object.
     *
     * @return inner cat.
     */
    public Cat getCat() {
        return this.cat;
    }

    /**
     * Get inner <code>Dog</code> object.
     *
     * @return inner dog.
     */
    public Dog getDog() {
        return this.dog;
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
    public void setName(final String name) throws NameException {
        String[] nameArray = this.splitName(name);
        this.cat.setName(nameArray[0]);
        this.dog.setName(nameArray[1]);
    }

    /**
     * Split given name to corresponding cat and dog name.
     *
     * @param name cat-dog's name. Must be in format '&lt;cat_name&gt;_&lt;dog_name&gt;'.
     * @return string array with 2 elements: cat and dog names respectively.
     * @throws NameException thrown when given name can't be split.
     */
    private String[] splitName(final String name) throws NameException {
        String[] nameArray = name.split("-");
        if (nameArray.length != 2) {
            throw new NameException(String.format("Wrong cat-dog's name: %s", name));
        }
        return nameArray;
    }
}
