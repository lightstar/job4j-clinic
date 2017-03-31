package ru.lightstar.clinic.drug;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>Aspirin</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class AspirinTest {

    /**
     * <code>Aspirin</code> object used in tests.
     */
    private final Aspirin aspirin;

    /**
     * Constructs <code>AspirinTest</code> object.
     */
    public AspirinTest() {
        this.aspirin = new Aspirin();
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.aspirin.getName(), is("Aspirin"));
    }

    /**
     * Test correctness of <code>getDungerLevel</code> method.
     */
    @Test
    public void getDangerLevel() {
        assertThat(this.aspirin.getDangerLevel(), is(2));
    }
}