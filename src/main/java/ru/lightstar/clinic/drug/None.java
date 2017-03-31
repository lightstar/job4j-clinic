package ru.lightstar.clinic.drug;

/**
 * 'None' drug used instead of null object. It indicates that there is really no drug at all.
 *
 * @author LightStar
 * @since 0.0.1
 */
class None implements Drug {

    /**
     * Constructs <code>None</code> object.
     */
    None() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDangerLevel() {
        return 0;
    }
}
