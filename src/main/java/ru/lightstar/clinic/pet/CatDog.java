package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.model.Client;
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
     * Cat-dog's type string.
     */
    public final static String TYPE = "cat-dog";

    /**
     * Pet's database id.
     */
    private int id;

    /**
     * Pet's host.
     */
    private Client client;

    /**
     * Inner <code>Cat</code> object.
     */
    private final Cat cat;

    /**
     * Inner <code>Dog</code> object.
     */
    private final Dog dog;

    /**
     * Next pet in chain.
     */
    private Pet nextPet;

    /**
     * Previous pet in chain.
     */
    private Pet prevPet;

    /**
     * Constructs <code>CatDog</code> object.
     */
    public CatDog() {
        this(new Cat(), new Dog());
    }

    /**
     * Constructs <code>CatDog</code> object from given cat and dog objects.
     *
     * @param cat inner cat.
     * @param dog inner dog.
     */
    public CatDog(final Cat cat, final Dog dog) {
        super();
        this.id = -1;
        this.client = Client.NONE;
        this.cat = cat;
        this.dog = dog;
        this.init();
    }

    /**
     * Constructs <code>CatDog</code> object.
     * @param name cat-dog's name. Must be in format '&lt;cat_name&gt;_&lt;dog_name&gt;'.
     * @param output output used for sounds.
     * @throws NameException thrown when given name doesn't fit correct format.
     */
    public CatDog(final String name, final Output output) throws NameException {
        super();
        this.id = -1;
        this.client = Client.NONE;
        String[] nameArray = this.splitName(name);
        this.cat = new Cat(nameArray[0], output);
        this.dog = new Dog(nameArray[1], output);
        this.init();
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
    public int getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client getClient() {
        return this.client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClient(final Client client) {
        this.client = client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return TYPE;
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
     * {@inheritDoc}
     */
    @Override
    public int getAge() {
        return this.cat.getAge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(final int age) {
        this.cat.setAge(age);
        this.dog.setAge(age);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sex getSex() {
        return this.cat.getSex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSex(final Sex sex) {
        this.cat.setSex(sex);
        this.dog.setSex(sex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getNextPet() {
        return this.nextPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextPet(final Pet nextPet) {
        this.nextPet = nextPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrevPet(final Pet prevPet) {
        this.prevPet = prevPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getPrevPet() {
        return this.prevPet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s '%s'", this.getType(), this.getName());
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

    /**
     * Initialize operations common for all constructors.
     */
    private void init() {
        this.nextPet = Pet.NONE;
        this.prevPet = Pet.NONE;
    }
}
