package ru.lightstar.clinic.model;

/**
 * Client's role.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Role extends Base {

    /**
     * Role's name.
     */
    private String name;

    /**
     * Constructs <code>Role</code> object.
     */
    public Role() {
        super();
        this.name = "";
    }

    /**
     * Get role's name.
     *
     * @return role's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set role's name.
     *
     * @param name role's name.
     */
    public void setName(final String name) {
        this.name = name;
    }
}
