package ru.lightstar.clinic.drug;

/**
 * Abstract drug.
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class AbstractDrug implements Drug {

    /**
     * Drug's database id.
     */
    private int id;

    /**
     * Drug's name.
     */
    private final String name;

    /**
     * Drug's danger level.
     */
    private int dangerLevel;

    /**
     * Constructs <code>AbstractDrug</code> object.
     *
     * @param name drug's name.
     * @param dangerLevel drug's danger level.
     */
    public AbstractDrug(final String name, final int dangerLevel) {
        super();
        this.id = -1;
        this.name = name;
        this.dangerLevel = dangerLevel;
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
    public void setDangerLevel(final int dangerLevel) {
        this.dangerLevel = dangerLevel;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s (%d)", this.name, this.dangerLevel);
    }
}
