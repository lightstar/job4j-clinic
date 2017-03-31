package ru.lightstar.clinic.drug;

/**
 * Drug used to heal pets.
 *
 * @author LightStar
 * @since 0.0.1
 */
public interface Drug {

    /**
     * Drug object used instead of null. Indicates that there is really no drug at all.
     */
    Drug NONE = new None();

    /**
     * Get drug's name.
     *
     * @return drug's name.
     */
    String getName();

    /**
     * Get drug's danger level.
     *
     * @return drug's danger level.
     */
    int getDangerLevel();
}
