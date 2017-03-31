package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.io.*;
import ru.lightstar.clinic.pet.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * <code>ClinicService</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicServiceTest {

    /**
     * Clinic size for tests.
     */
    private final static int CLINIC_SIZE = 10;

    /**
     * <code>Input</code> object used in clinic service.
     */
    private final Input input;

    /**
     * <code>Output</code> object used in clinic service.
     */
    private final Output output;

    /**
     * <code>Clinic</code> object used in all tests.
     */
    private final Clinic clinic;

    /**
     * <code>ClinicService</code> object used in all tests.
     */
    private final ClinicService clinicService;

    /**
     * Constructs <code>ClinicServiceTest</code> object.
     */
    public ClinicServiceTest() {
        this.input = new IteratorInput();
        this.output = new ByteArrayOutput();
        this.clinic = new Clinic(CLINIC_SIZE);
        this.clinicService = new ClinicService(this.input, this.output, this.clinic);
    }

    /**
     * Test correctness of <code>getAllClients</code> method.
     */
    @Test
    public void whenGetAllClientsThenResult() throws NameException, ServiceException {
        final Client[] allClients = new Client[CLINIC_SIZE];
        allClients[0] = this.clinicService.addClient(0, "Vova");
        assertThat(this.clinicService.getAllClients(), is(allClients));
    }

    /**
     * Test correctness of <code>getInput</code> method.
     */
    @Test
    public void whenGetInputThenResult() {
        assertThat(this.clinicService.getInput(), is(this.input));
    }

    /**
     * Test correctness of <code>getOutput</code> method.
     */
    @Test
    public void whenGetOutputThenResult() {
        assertThat(this.clinicService.getOutput(), is(this.output));
    }

    /**
     * Test correctness of <code>addClient</code> method.
     */
    @Test
    public void whenAddClientThenItAddsWithNonePet() throws NameException, ServiceException {
        final Client client = this.clinicService.addClient(0, "Vova");
        final Client[] allClients = this.clinic.getClients();

        assertThat(allClients[0], instanceOf(Client.class));
        assertThat(allClients[0].getName(), is("Vova"));
        assertThat(allClients[0].getPet(), is(Pet.NONE));
        assertThat(allClients[0], is(client));
    }

    /**
     * Test that <code>addClient</code> method throws exception on attempt to add client to occupied position.
     */
    @Test(expected = ServiceException.class)
    public void whenAddClientToOccupiedPositionThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.addClient(0, "Vasya");
    }

    /**
     * Test that <code>addClient</code> method throws exception on attempt to add client with non-unique name.
     */
    @Test(expected = NameException.class)
    public void whenAddClientWithNonUniqueNameThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.addClient(1, "Vova");
    }

    /**
     * Test that <code>addClient</code> method throws exception on attempt to add client to out of bounds position.
     */
    @Test(expected = ServiceException.class)
    public void whenAddClientToWrongPositionThenException() throws NameException, ServiceException {
        this.clinicService.addClient(CLINIC_SIZE, "Vova");
    }

    /**
     * Test correctness of <code>setClientPet</code> method.
     */
    @Test
    public void whenSetClientPetThenItSets() throws NameException, ServiceException {
        final Client client = this.clinicService.addClient(0, "Vova");
        final Pet pet = this.clinicService.setClientPet("Vova", "cat", "Murka");

        assertThat(pet, instanceOf(Cat.class));
        assertThat(pet.getName(), is("Murka"));
        assertThat(client.getPet(), is(pet));
    }

    /**
     * Test that <code>setClientPet</code> method throws exception on attempt to set pet for non-existent client.
     */
    @Test(expected = ServiceException.class)
    public void whenSetClientPetToNonExistentClientThenException() throws NameException, ServiceException {
        this.clinicService.setClientPet("Vasya", "cat", "Murka");
    }

    /**
     * Test that <code>setClientPet</code> method throws exception on attempt to set pet with unknown type.
     */
    @Test(expected = ServiceException.class)
    public void whenSetClientPetWithWrongTypeThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.setClientPet("Vova", "unknown", "Unknown");
    }

    /**
     * Test that <code>setClientPet</code> method throws exception on attempt to set pet with incorrect name.
     */
    @Test(expected = NameException.class)
    public void whenSetClientPetWithWrongNameThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.setClientPet("Vova", "cat-dog", "Murka");
    }

    /**
     * Test correctness of <code>updateClientName</code> method.
     */
    @Test
    public void whenUpdateClientNameThenItChanges() throws NameException, ServiceException {
        final Client client = this.clinicService.addClient(0, "Vova");
        this.clinicService.updateClientName("Vova", "Vasya");

        assertThat(client.getName(), is("Vasya"));
    }

    /**
     * Test that <code>updateClientName</code> method throws exception on attempt to set the same name.
     */
    @Test(expected = NameException.class)
    public void whenUpdateClientNameToSameStringThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.updateClientName("Vova", "Vova");
    }

    /**
     * Test that <code>updateClientName</code> method throws exception on attempt to set the non-unique name.
     */
    @Test(expected = NameException.class)
    public void whenUpdateClientNameToNonUniqueThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.addClient(1, "Vasya");
        this.clinicService.updateClientName("Vova", "Vasya");
    }

    /**
     * Test correctness of <code>updateClientPetName</code> method.
     */
    @Test
    public void whenUpdatePetNameThenItChanges() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        final Pet pet = this.clinicService.setClientPet("Vova", "cat", "Murka");
        this.clinicService.updateClientPetName("Vova", "Bobik");

        assertThat(pet.getName(), is("Bobik"));
    }

    /**
     * Test that <code>updateClientPetName</code> method throws exception on attempt to set pet name for non-existent
     * client.
     */
    @Test(expected = ServiceException.class)
    public void whenUpdatePetNameForNonExistentClientThenException() throws NameException, ServiceException {
        this.clinicService.updateClientPetName("Vova", "Bobik");
    }

    /**
     * Test that <code>updatePetName</code> method throws exception on attempt to set the same pet name.
     */
    @Test(expected = NameException.class)
    public void whenUpdatePetNameToSameStringThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.setClientPet("Vova", "cat", "Murka");
        this.clinicService.updateClientPetName("Vova", "Murka");
    }

    /**
     * Test correctness of <code>deleteClient</code> method.
     */
    @Test
    public void whenDeleteClientThenItDeletes() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.deleteClient("Vova");

        assertThat(this.clinic.getClients()[0], is(nullValue()));
    }

    /**
     * Test that <code>deleteClient</code> method throws exception on attempt to delete non-existent client.
     */
    @Test(expected = ServiceException.class)
    public void whenDeleteNonExistentClientThenException() throws NameException, ServiceException {
        this.clinicService.deleteClient("Vova");
    }

    /**
     * Test correctness of <code>deleteClientPet</code> method.
     */
    @Test
    public void whenDeletePetThenItDeletes() throws NameException, ServiceException {
        final Client client = this.clinicService.addClient(0, "Vova");
        this.clinicService.setClientPet("Vova", "cat", "Murka");
        this.clinicService.deleteClientPet("Vova");

        assertThat(client.getPet(), is(Pet.NONE));
    }

    /**
     * Test that <code>deleteClientPet</code> method throws exception on attempt to delete pet from non-existent client.
     */
    @Test(expected = ServiceException.class)
    public void whenDeletePetFromNonExistentClientThenException() throws NameException, ServiceException {
        this.clinicService.deleteClientPet("Vova");
    }

    /**
     * Test that <code>deleteClientPet</code> method throws exception on attempt to delete non-existent pet.
     */
    @Test(expected = ServiceException.class)
    public void whenDeleteNonExistentPetThenException() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.deleteClientPet("Vova");
    }

    /**
     * Test correctness of <code>countClientsByPetName</code> method.
     */
    @Test
    public void whenCountClientsByPetNameThenResult() throws NameException, ServiceException {
        this.clinicService.addClient(0, "Vova");
        this.clinicService.addClient(1, "Vasya");
        this.clinicService.addClient(3, "Masha");
        this.clinicService.addClient(4, "Nastya");

        this.clinicService.setClientPet("Vova", "cat", "Murka");
        this.clinicService.setClientPet("Vasya", "fish", "Murka");
        this.clinicService.setClientPet("Masha", "dog", "Bobik");

        final int count = this.clinicService.countClientsByPetName("Murka");

        assertThat(count, is(2));
    }

    /**
     * Test correctness of <code>findClientsByPetName</code> method.
     */
    @Test
    public void whenFindClientsByPetNameThenResult() throws NameException, ServiceException {
        final Client client1 = this.clinicService.addClient(0, "Vova");
        final Client client2 = this.clinicService.addClient(1, "Vasya");
        this.clinicService.addClient(3, "Masha");
        this.clinicService.addClient(4, "Nastya");

        this.clinicService.setClientPet("Vova", "cat", "Murka");
        this.clinicService.setClientPet("Vasya", "fish", "Murka");
        this.clinicService.setClientPet("Masha", "dog", "Bobik");

        final Client[] foundClients = this.clinicService.findClientsByPetName("Murka");

        assertThat(foundClients, is(new Client[]{client1, client2}));
    }

    /**
     * Test correctness of <code>findClientPositionByName</code> method.
     */
    @Test
    public void whenFindClientPositionByNameThenResult() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        this.clinicService.addClient(5, "Vasya");

        final int position = clinicService.findClientPositionByName("Vasya");

        assertThat(position, is(5));
    }

    /**
     * Test that <code>findClientPositionByName</code> method throws exception for non-existent client's name.
     */
    @Test(expected = ServiceException.class)
    public void whenFindClientPositionByNonExistentNameThenException() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        this.clinicService.addClient(5, "Vasya");

        this.clinicService.findClientPositionByName("Masha");
    }

    /**
     * Test correctness of <code>getClientByPosition</code> method
     */
    @Test
    public void whenGetClientByPositionThenResult() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        final Client client = this.clinicService.addClient(5, "Vasya");

        assertThat(this.clinicService.getClientByPosition(5), is(client));
    }

    /**
     * Test that <code>getClientByPosition</code> method throws exception for empty position.
     */
    @Test(expected = ServiceException.class)
    public void whenGetClientByEmptyPositionThenException() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        this.clinicService.addClient(5, "Vasya");

        this.clinicService.getClientByPosition(6);
    }

    /**
     * Test that <code>getClientByPosition</code> method throws exception for out of bounds position.
     */
    @Test(expected = ServiceException.class)
    public void whenGetClientByWrongPositionThenException() throws NameException, ServiceException {
        this.clinicService.getClientByPosition(CLINIC_SIZE);
    }

    /**
     * Test correctness of <code>findClientByName</code> method.
     */
    @Test
    public void whenFindClientByNameThenResult() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        final Client client = this.clinicService.addClient(5, "Vasya");

        assertThat(this.clinicService.findClientByName("Vasya"), is(client));
    }

    /**
     * Test that <code>findClientByName</code> method throws exception for non-existent client's name.
     */
    @Test(expected = ServiceException.class)
    public void whenFindClientByNonExistentNameThenException() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        this.clinicService.addClient(5, "Vasya");
        this.clinicService.findClientByName("Masha");
    }

    /**
     * Test correctness of <code>getClientPet</code> method.
     */
    @Test
    public void whenGetClientPetThenResult() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        final Pet pet = this.clinicService.setClientPet("Vova", "cat", "Murka");

        assertThat(this.clinicService.getClientPet("Vova"), is(pet));
    }

    /**
     * Test that <code>getClientPet</code> method throws exception is client not exists.
     */
    @Test(expected = ServiceException.class)
    public void whenGetClientPetByNonExistentNameThenException() throws NameException, ServiceException {
        this.clinicService.getClientPet("Vova");
    }

    /**
     * Test that <code>getClientPet</code> throws exception if client doesn't have pet.
     */
    @Test(expected = ServiceException.class)
    public void whenGetClientPetAndClientHasNoPetThenException() throws NameException, ServiceException {
        this.clinicService.addClient(1, "Vova");
        this.clinicService.getClientPet("Vova");
    }

    /**
     * Test correctness of <code>askPetMakeSound</code> method.
     */
    @Test
    public void whenAskPetMakeSoundThenResult() throws NameException, ServiceException {
        this.clinicService.addClient(5, "Vasya");
        this.clinicService.setClientPet("Vasya", "cat", "Murka");

        this.clinicService.askPetMakeSound("Vasya");

        assertThat(this.output.toString(), is(String.format("Mew, mew!%n")));
    }

    /**
     * Test that <code>askPetMakeSound</code> throws exception for non-existent client's name.
     */
    @Test(expected = ServiceException.class)
    public void whenAskPetMakeSoundByNonExistentNameThenException() throws ServiceException {
        this.clinicService.askPetMakeSound("Vasya");
    }

    /**
     * Test that <code>askPetMakeSound</code> throws exception for non-existent client's pet.
     */
    @Test(expected = ServiceException.class)
    public void whenAskNonExistentPetMakeSoundThenException() throws NameException, ServiceException {
        this.clinicService.addClient(5, "Vasya");
        this.clinicService.askPetMakeSound("Vasya");
    }
}
