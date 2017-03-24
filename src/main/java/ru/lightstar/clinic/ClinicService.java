package ru.lightstar.clinic;

import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.pet.PetFactory;
import ru.lightstar.clinic.pet.Pet;

import java.util.Arrays;

/**
 * Clinic service object used to perform various operations on it.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicService {

    /**
     * Clinic manipulated by this service.
     */
    private final Clinic clinic;

    /**
     * Factory used by this service to create new pets.
     */
    private final PetFactory petFactory;

    /**
     * Constructs <code>ClinicService</code> object
     *
     * @param clinic clinic object.
     * @param output output object used for pet creation.
     */
    public ClinicService(final Clinic clinic, final Output output) {
        this.clinic = clinic;
        this.petFactory = new PetFactory(output);
    }

    /**
     * Count clients by pet's name
     *
     * @param name pet's name.
     * @return count of found clients.
     */
    public int countClientsByPetName(final String name) {
        int count = 0;

        for (final Client client : this.clinic.getClients()) {
            if (client.getPet().getName().equals(name)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Search clients by pet's name.
     *
     * @param name pet's name.
     * @return array of found clients.
     */
    public Client[] findClientsByPetName(final String name) {
        final Client[] resultClients = new Client[this.countClientsByPetName(name)];
        int resultIndex = 0;

        for (final Client client : this.clinic.getClients()) {
            if (client.getPet().getName().equals(name)) {
                resultClients[resultIndex++] = client;
            }
        }

        return resultClients;
    }

    /**
     * Search client by name and return his position.
     *
     * @param name client's name.
     * @return found client's position.
     * @throws ServiceException thrown if client can't be found.
     */
    public int findClientPositionByName(final String name) throws ServiceException {
        final Client[] allClients = this.clinic.getClients();
        int resultPosition = -1;

        for (int position = 0; position < allClients.length; position++) {
            if (allClients[position].getName().equals(name)) {
                resultPosition = position;
                break;
            }
        }

        if (resultPosition == -1) {
            throw new ServiceException("Client not found");
        }

        return resultPosition;
    }

    /**
     * Search client by position.
     *
     * @param position client's position.
     * @return found client.
     * @throws ServiceException thrown if client can't be found.
     */
    public Client getClientByPosition(final int position) throws ServiceException {
        this.checkPositionBounds(position);

        final Client[] allClients = this.clinic.getClients();

        if (allClients[position] == null) {
            throw new ServiceException("Client not found");
        }

        return allClients[position];
    }

    /**
     * Search client by name.
     *
     * @param name client's name.
     * @return found client.
     * @throws ServiceException thrown if client can't be found.
     */
    public Client findClientByName(final String name) throws ServiceException {
        int position = this.findClientPositionByName(name);
        return this.getClientByPosition(position);
    }

    /**
     * Add new client.
     *
     * @param position position for client.
     * @param name client's name.
     * @return added client.
     * @throws ServiceException thrown if position is busy.
     * @throws NameException thrown if client's name is incorrect.
     */
    public Client addClient(final int position, final String name)
            throws ServiceException, NameException {

        this.checkPositionBounds(position);
        this.checkClientName(name);

        final Client[] allClients = this.clinic.getClients();

        if (allClients[position] != null) {
            throw new ServiceException("Position is busy");
        }

        Client client = new Client(name, Pet.NONE);
        this.clinic.addClient(position, client);

        return client;
    }

    /**
     * Set client's pet.
     *
     * @param position client's position.
     * @param petType pet's type.
     * @param petName pet's name.
     * @throws ServiceException thrown if client can't be found or pet's type is wrong.
     * @throws NameException thrown if pet's name is wrong.
     */
    public void setClientPet(final int position, final String petType, final String petName)
            throws ServiceException, NameException {

        final Client[] allClients = this.clinic.getClients();

        this.checkPositionBounds(position);

        if (allClients[position] == null) {
            throw new ServiceException("No client in position");
        }

        this.checkPetType(petType);
        this.checkPetName(petName);

        final Pet pet = this.petFactory.create(petType, petName);
        allClients[position].setPet(pet);
    }

    /**
     * Update client's name.
     *
     * @param oldName client's old name.
     * @param newName new name.
     * @throws ServiceException thrown if client can't be found.
     * @throws NameException thrown if new name is wrong.
     */
    public void updateClientName(final String oldName, final String newName) throws ServiceException, NameException {
        if (oldName.equals(newName)) {
            throw new NameException("New name is the same as previous");
        }

        this.checkClientName(newName);

        final Client client = this.findClientByName(oldName);
        client.setName(newName);
    }

    /**
     * Update client pet's name.
     *
     * @param name client's name.
     * @param petName pet's name.
     * @throws ServiceException thrown if client can't be found or he doesn't has pet.
     * @throws NameException thrown if new name is wrong.
     */
    public void updateClientPetName(final String name, final String petName) throws ServiceException, NameException {
        final Client client = this.findClientByName(name);

        if (client.getPet() == Pet.NONE) {
            throw new ServiceException("Client doesn't have pet");
        }

        if (client.getPet().getName().equals(petName)) {
            throw new NameException("New name is the same as previous");
        }

        this.checkPetName(petName);

        client.getPet().setName(petName);
    }

    /**
     * Delete client.
     *
     * @param name client's name.
     * @throws ServiceException thrown if client can't be found.
     */
    public void deleteClient(final String name) throws ServiceException {
        final int position = this.findClientPositionByName(name);
        this.clinic.deleteClient(position);
    }

    /**
     * Delete client's pet.
     *
     * @param name client's name.
     * @throws ServiceException thrown if client can't be found or he doesn't has pet.
     */
    public void deletePet(final String name) throws ServiceException {
        final int position = this.findClientPositionByName(name);
        final Client client = this.getClientByPosition(position);

        if (client.getPet() == Pet.NONE) {
            throw new ServiceException("Client doesn't have pet");
        }

        client.setPet(Pet.NONE);
    }

    /**
     * Checks if provided position is in allowed bounds. If not - throws an exception.
     *
     * @param position client's position.
     * @throws ServiceException thrown if position is out of bounds.
     */
    private void checkPositionBounds(final int position) throws ServiceException {
        if (position < 0 || position > this.clinic.getSize()) {
            throw new ServiceException("Position is out of bounds");
        }
    }

    /**
     * Checks if provided client name is unique and non-empty.
     *
     * @param name client's name.
     * @throws NameException thrown if name is wrong.
     */
    private void checkClientName(final String name) throws NameException {
        if (name.isEmpty()) {
            throw new NameException("Client's name is empty");
        }

        for (final Client client : this.clinic.getClients()) {
            if (client.getName().equals(name)) {
                throw new NameException("Client's name is not unique");
            }
        }
    }

    /**
     * Checks if provided pet's type is correct.
     *
     * @param petType pet's type.
     * @throws ServiceException thrown if type is unknown.
     */
    private void checkPetType(String petType) throws ServiceException {
        if (!Arrays.asList(PetFactory.TYPES).contains(petType)) {
            throw new ServiceException("Unknown pet type");
        }
    }

    /**
     * Checks if provided pet's name is non-empty.
     *
     * @param name pet's name.
     * @throws NameException thrown if pet's name is empty.
     */
    private void checkPetName(final String name) throws NameException {
        if (name.isEmpty()) {
            throw new NameException("Pet's name is empty");
        }
    }
}
