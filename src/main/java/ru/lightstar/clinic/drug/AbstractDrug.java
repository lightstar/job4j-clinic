package ru.lightstar.clinic.drug;

/**
 * Abstract drug.
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class AbstractDrug implements Drug {

    /**
     * Drug's name.
     */
    private final String name;

    /**
     * Drug's danger level.
     */
    private final int dangerLevel;

    /**
     * Constructs <code>AbstractDrug</code> object.
     *
     * @param name drug's name.
     * @param dangerLevel drug's danger level.
     */
    public AbstractDrug(final String name, final int dangerLevel) {
        this.name = name;
        this.dangerLevel = dangerLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDangerLevel() {
        return this.dangerLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        final AbstractDrug drug = (AbstractDrug) obj;

        return this.dangerLevel == drug.dangerLevel && this.name.equals(drug.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.dangerLevel;
        return result;
    }
}
