package ru.lightstar.clinic.model;

/**
 * Base class for models
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class Base {

    /**
     * Database id.
     */
    protected int id;

    /**
     * Constructs <code>Base</code> object.
     */
    public Base() {
        super();
        this.id = -1;
    }

    /**
     * Get database id.
     *
     * @return database id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set database id.
     *
     * @param id database id.
     */
    public void setId(final int id) {
        this.id = id;
    }
}
