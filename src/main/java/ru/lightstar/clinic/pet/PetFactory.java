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
     * Output used when creating pets.
     */
    private final Output output;

    /**
     * Map of all known pet classes with type as a key.
     */
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

    /**
     * Add new pet class.
     *
     * @param petClass pet class.
     */
    public void addPet(Class<? extends Pet> petClass) {
        try {
            String type = (String) petClass.getField("TYPE").get(null);
            this.knownPets.put(type, petClass);
        } catch(Exception e) {
            throw new IllegalArgumentException("Wrong class. It must contain 'public static String TYPE' field.");
        }
    }

    /**
     * Check if given pet type is known to this factory.
     *
     * @param type pet type.
     * @return true if given type is known and false otherwise.
     */
    public boolean isKnownType(String type) {
        return this.knownPets.containsKey(type);
    }

    /**
     * Get array of all known pet types.
     *
     * @return array of all known pet types.
     */
    public String[] getKnownTypes() {
        final String[] knownTypes = new String[this.knownPets.size()];
        return this.knownPets.keySet().toArray(knownTypes);
    }

    /**
     * Create <code>Pet</code> object using provided type and name.
     *
     * @param type pet's type.
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
            if (e.getTargetException() instanceof NameException) {
                throw (NameException) e.getTargetException();
            } else {
                throw new IllegalStateException("Wrong pet class. Its constructor must throw just NameException.");
            }
        } catch(Exception e) {
            throw new IllegalStateException("Wrong pet class. Its constructor must take String and Output args.");
        }
    }
}
