package ru.lightstar.clinic.drug;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>Glucose</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class GlucoseTest {

    /**
     * <code>Glucose</code> object used in tests.
     */
    private final Glucose glucose;

    /**
     * Constructs <code>GlucoseTest</code> object.
     */
    public GlucoseTest() {
        super();
        this.glucose = new Glucose();
    }

    /**
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.glucose.setId(1);
        assertThat(this.glucose.getId(), is(1));
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.glucose.getName(), is("glucose"));
    }

    /**
     * Test correctness of <code>getDangerLevel</code> method.
     */
    @Test
    public void getDangerLevel() {
        assertThat(this.glucose.getDangerLevel(), is(1));
    }
}
