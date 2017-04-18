package ru.lightstar.clinic.drug;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>AbstractDrug</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class AbstractDrugTest {

    /**
     * Name of test drug.
     */
    private final static String TEST_DRUG_NAME = "TestDrug";

    /**
     * Danger level of test drug.
     */
    private final static int TEST_DRUG_DANGER_LEVEL = 5;

    /**
     * <code>AbstractDrug</code> object used in tests.
     */
    private final AbstractDrug abstractDrug;

    /**
     * Constructs <code>AbstractDrugTest</code> object.
     */
    public AbstractDrugTest() {
        super();
        this.abstractDrug = new TestDrug();
    }

    /**
     * Test correctness of <code>setId</code> and <code>getId</code> methods.
     */
    @Test
    public void whenSetIdThenItChanges() {
        this.abstractDrug.setId(1);
        assertThat(this.abstractDrug.getId(), is(1));
    }

    /**
     * Test correctness of <code>getName</code> method.
     */
    @Test
    public void whenGetNameThenResult() {
        assertThat(this.abstractDrug.getName(), is(TEST_DRUG_NAME));
    }

    /**
     * Test correctness of <code>getDangerLevel</code> method.
     */
    @Test
    public void whenGetDangerLevelThenResult() {
        assertThat(this.abstractDrug.getDangerLevel(), is(TEST_DRUG_DANGER_LEVEL));
    }

    /**
     * Test correctness of <code>equals</code> method for drugs with same properties.
     */
    @Test
    public void whenDrugsHaveSamePropertiesThenEqualsIsTrue() {
        final AbstractDrug otherDrug = new TestDrug();
        assertThat(this.abstractDrug.equals(otherDrug), is(true));
    }

    /**
     * Test correctness of <code>equals</code> method for drugs with different properties.
     */
    @Test
    public void whenDrugsHaveDifferentPropertiesThenEqualsIsFalse() {
        final AbstractDrug otherDrug = new TestDrug("OtherDrug", 1);
        assertThat(this.abstractDrug.equals(otherDrug), is(false));
    }

    /**
     * Subclass of <code>AbstractDrug</code> used for tests.
     */
    private static class TestDrug extends AbstractDrug {

        /**
         * Constructs <code>TestDrug</code> object with default test properties.
         */
        public TestDrug() {
            this(TEST_DRUG_NAME, TEST_DRUG_DANGER_LEVEL);
        }

        /**
         * Constructs <code>TestDrug</code> object.
         *
         * @param name drug's name.
         * @param dangerLevel drug's danger level.
         */
        public TestDrug(final String name, final int dangerLevel) {
            super(name, dangerLevel);
        }
    }
}
