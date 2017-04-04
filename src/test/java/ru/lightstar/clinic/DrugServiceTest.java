package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.drug.Aspirin;
import ru.lightstar.clinic.drug.Drug;
import ru.lightstar.clinic.drug.Glucose;
import ru.lightstar.clinic.exception.ServiceException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>DrugService</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DrugServiceTest {

    /**
     * Clinic size for tests.
     */
    private final static int CLINIC_SIZE = 10;

    /**
     * <code>Clinic</code> object used in all tests.
     */
    private final Clinic clinic;

    /**
     * <code>DrugService</code> object used in all tests.
     */
    private final DrugService drugService;

    /**
     * Constructs <code>DrugServiceTest</code> object.
     */
    public DrugServiceTest() {
        super();
        this.clinic = new Clinic(CLINIC_SIZE);
        this.drugService = new DrugService(this.clinic);
    }

    /**
     * Test correctness of <code>addDrug</code> method.
     */
    @Test
    public void whenAddDrugThenItAdds() throws ServiceException {
        final Drug drug = this.drugService.addDrug("aspirin");
        assertThat(drug, instanceOf(Aspirin.class));
        assertThat(this.clinic.getDrugList().toArray(), is(new Object[]{new Aspirin()}));
    }

    /**
     * Test exception in <code>addDrug</code> thrown on incorrect input.
     */
    @Test(expected = ServiceException.class)
    public void whenAddUnknownDrugThenException() throws ServiceException {
        this.drugService.addDrug("unknown");
    }

    /**
     * Test correctness of <code>getAllDrugs</code> method.
     */
    @Test
    public void whenGetAllDrugsThenResult() throws ServiceException {
        this.drugService.addDrug("aspirin");
        this.drugService.addDrug("aspirin");
        this.drugService.addDrug("glucose");

        assertThat(this.drugService.getAllDrugs().get(new Aspirin()), is(2));
        assertThat(this.drugService.getAllDrugs().get(new Glucose()), is(1));
    }

    /**
     * Test correctness of <code>getKnownDrugNames</code> method.
     */
    @Test
    public void whenGetKnownDrugNamesThenResult() {
        assertThat(this.drugService.getKnownDrugNames(), is(new String[]{"aspirin", "glucose"}));
    }

    /**
     * Test correctness of <code>takeDrug</code> method.
     */
    @Test
    public void whenTakeDrugThenItTakesOut() throws ServiceException {
        this.drugService.addDrug("aspirin");
        this.drugService.addDrug("aspirin");
        this.drugService.addDrug("glucose");
        final Drug drug = this.drugService.takeDrug("aspirin");

        assertThat(drug, instanceOf(Aspirin.class));
        assertThat(this.clinic.getDrugList().toArray(), is(new Object[]{new Aspirin(), new Glucose()}));
    }

    /**
     * Test exception in <code>takeDrug</code> thrown if drug not exists in clinic.
     */
    @Test(expected = ServiceException.class)
    public void whenTakeAbsentDrugThenException() throws ServiceException {
        this.drugService.takeDrug("aspirin");
    }
}
