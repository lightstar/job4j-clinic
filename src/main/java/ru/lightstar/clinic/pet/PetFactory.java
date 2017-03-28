package ru.lightstar.clinic.pet;

import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.Output;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public final static String[] TYPES = new String[]{Bird.TYPE, Cat.TYPE, Dog.TYPE, Fish.TYPE, CatDog.TYPE};

    /**
     * Output used when creating pets.
     */
    private final Output output;

    private final Map<String, Class<? extends Pet>> knownPets;

    /**
     * Constructs <code>PetFactory</code> object.
     *
     * @param output output used when creating pets.
     */
    public PetFactory(final Output output) {
        super();
        this.output = output;
        this.knownPets = new LinkedHashMap<>();
    }

    public void addPet(Class<? extends Pet> petClass) {
        try {
            String type = (String) petClass.getField("TYPE").get(null);
            this.knownPets.put(type, petClass);
        } catch(Exception e) {
            throw new IllegalArgumentException("Wrong class. It must contain 'public static String TYPE' field.");
        }
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
        if (!this.knownPets.containsKey(type)) {
            throw new IllegalArgumentException("Unknown pet's type");
        }

        try {
            return this.knownPets.get(type).getConstructor(String.class, Output.class).newInstance(name, this.output);
        } catch(InvocationTargetException e) {
            if (e.getTargetException().getClass() == NameException.class) {
                throw (NameException) e.getTargetException();
            } else {
                throw new IllegalArgumentException("Wrong pet class. Its constructor must throw just NameException.");
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("Wrong pet class. Its constructor must take String and Output args.");
        }
    }
}
