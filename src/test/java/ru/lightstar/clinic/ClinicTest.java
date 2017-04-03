package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.io.DummyOutput;
import ru.lightstar.clinic.list.DrugList;
import ru.lightstar.clinic.list.PetList;
import ru.lightstar.clinic.pet.Cat;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * <code>Clinic</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicTest {

    /**
     * Clinic size for tests.
     */
    private final static int CLINIC_SIZE = 10;

    /**
     * <code>Clinic</code> object used in all tests.
     */
    private final Clinic clinic;

    /**
     * Constructs <code>Clinic</code> object.
     */
    public ClinicTest() {
        this.clinic = new Clinic(CLINIC_SIZE);
    }

    /**
     * Test correctness of <code>addClient</code> and <code>getClients</code> methods.
     */
    @Test
    public void whenAddClientThatHeAdds() {
        final Client client = new Client("Vasya", new Cat("Murka", new DummyOutput()), 0);
        clinic.addClient(1, client);
        assertThat(clinic.getClients()[1], is(client));
    }

    /**
     * Test correctness of <code>deleteClient</code> method.
     */
    @Test
    public void whenDeleteClientThatHeDeletes() {
        final Client client = new Client("Vasya", new Cat("Murka", new DummyOutput()), 0);
        this.clinic.addClient(1, client);
        this.clinic.deleteClient(1);
        assertThat(this.clinic.getClients()[1], is(nullValue()));
    }

    /**
     * Test correctness of <code>getSize</code> method.
     */
    @Test
    public void whenGetSizeThenResult() {
        assertThat(this.clinic.getSize(), is(CLINIC_SIZE));
    }

    /**
     * Test correctness of <code>getDrugList</code> method.
     */
    @Test
    public void whenGetDrugListThenResult() {
        assertThat(this.clinic.getDrugList(), instanceOf(DrugList.class));
        assertThat(this.clinic.getDrugList().isEmpty(), is(true));
    }

    /**
     * Test correctness of <code>getPetList</code> method.
     */
    @Test
    public void whenGetPetListThenResult() {
        assertThat(this.clinic.getPetList(), instanceOf(PetList.class));
        assertThat(this.clinic.getPetList().isEmpty(), is(true));
    }
}
