package ru.lightstar.clinic.drug;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>DrugFactory</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DrugFactoryTest {

    /**
     * <code>DrugFactory</code> object used in all tests.
     */
    private final DrugFactory drugFactory;

    /**
     * Constructs <code>DrugFactoryTest</code> object.
     */
    public DrugFactoryTest() {
        super();
        this.drugFactory = new DrugFactory();
        this.drugFactory.addDrug(Aspirin.class);
        this.drugFactory.addDrug(Glucose.class);
    }

    /**
     * Test correctness of <code>create</code> method.
     */
    @Test
    public void whenCreateAspirinThenAspirinClass() {
        final Drug drug = this.drugFactory.create("aspirin");
        assertThat(drug, instanceOf(Aspirin.class));
    }

    /**
     * Test thrown exception on unknown drug name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenUnknownNameThenException() {
        this.drugFactory.create("unknown");
    }

    /**
     * Test correctness of <code>isKnownName</code> method.
     */
    @Test
    public void whenIsKnownNameThenResult() {
        assertThat(this.drugFactory.isKnownName("glucose"), is(true));
        assertThat(this.drugFactory.isKnownName("unknown"), is(false));
    }

    /**
     * Test correctness of <code>getKnownNames</code> method.
     */
    @Test
    public void whenGetKnownTypesThenResult() {
        assertThat(this.drugFactory.getKnownNames(), is(new String[]{"aspirin", "glucose"}));
    }
}
