package ru.lightstar.clinic.drug;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Factory of drugs.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DrugFactory {

    /**
     * Map of all known drug classes with type as a key.
     */
    private final Map<String, Class<? extends Drug>> knownDrugs;

    /**
     * Constructs <code>DrugFactory</code> object.
     */
    public DrugFactory() {
        super();
        this.knownDrugs = new LinkedHashMap<>();
    }

    /**
     * Add new drug class.
     *
     * @param drugClass drug class.
     */
    public void addDrug(Class<? extends Drug> drugClass) {
        try {
            String type = (String) drugClass.getField("NAME").get(null);
            this.knownDrugs.put(type, drugClass);
        } catch(Exception e) {
            throw new IllegalArgumentException("Wrong class. It must contain 'public static String NAME' field.");
        }
    }

    /**
     * Check if given drug's name is known to this factory.
     *
     * @param name drug's name.
     * @return true if given name is known and false otherwise.
     */
    public boolean isKnownName(final String name) {
        return this.knownDrugs.containsKey(name);
    }

    /**
     * Get array of all known drug names.
     *
     * @return array of all known drug names.
     */
    public String[] getKnownNames() {
        final String[] knownNames = new String[this.knownDrugs.size()];
        return this.knownDrugs.keySet().toArray(knownNames);
    }

    /**
     * Create <code>Drug</code> object using provided name.
     *
     * @param name drug's name.
     * @return created drug.
     */
    public Drug create(final String name) {
        if (!this.knownDrugs.containsKey(name)) {
            throw new IllegalArgumentException("Unknown drug's name");
        }

        try {
            return this.knownDrugs.get(name).newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Wrong drug class. Its must have default constructor.");
        }
    }
}
