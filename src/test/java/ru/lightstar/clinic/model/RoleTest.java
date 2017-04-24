package ru.lightstar.clinic.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>Role</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class RoleTest {

    /**
     * <code>Role</code> object used in all tests.
     */
    private final Role role;

    /**
     * Constructs <code>RoleTest</code> object.
     */
    public RoleTest() {
        super();
        this.role = new Role("admin");
    }

    /**
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.role.setId(1);
        assertThat(this.role.getId(), is(1));
    }

    /**
     * Test correctness of <code>setName</code> and <code>getName</code> methods.
     */
    @Test
    public void whenSetNameThenItChanges() {
        this.role.setName("client");
        assertThat(this.role.getName(), is("client"));
    }
}