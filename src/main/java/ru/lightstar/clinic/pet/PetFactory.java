package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.Console;
import ru.lightstar.clinic.io.Output;

/**
 * Pet factory.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class PetFactory {

    /**
     * All pet types known by this factory.
     */
    public final static String[] TYPES = new String[]{"bird", "cat", "dog", "fish", "cat-dog"};

    /**
     * Output used when creating pets.
     */
    private final Output output;

    /**
     * Constructs <code>PetFactory</code> object.
     *
     * @param output output used when creating pets.
     */
    public PetFactory(final Output output) {
        super();
        this.output = output;
    }

    /**
     * Constructs <code>PetFactory</code> object using {@link ru.lightstar.clinic.io.Console} output.
     */
    public PetFactory() {
        this(new Console());
    }

    /**
     * Create <code>Pet</code> object using provided type and name.
     *
     * @param type pet's type. Must be one of {@link #TYPES}
     * @param name pet's name.
     * @return created pet.
     * @throws NameException thrown when pet's name is wrong.
     */
    public Pet create(final String type, final String name) throws NameException {
        final Pet pet;

        switch(type) {
            case "bird":
                pet = new Bird(name, this.output);
                break;
            case "cat":
                pet = new Cat(name, this.output);
                break;
            case "dog":
                pet = new Dog(name, this.output);
                break;
            case "fish":
                pet = new Fish(name, this.output);
                break;
            case "cat-dog":
                pet = new CatDog(name, this.output);
                break;
            default:
                throw new IllegalArgumentException("Unknown pet's type");
        }

        return pet;
    }
}
