package ru.lightstar.clinic;

import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.io.Input;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.pet.*;

/**
 * Clinic service object used to perform various operations on it.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicService {

    /**
     * <code>Input</code> object used for this service.
     */
    private final Input input;

    /**
     * <code>Output</code> object used for this service.
     */
    private final Output output;

    /**
     * Clinic manipulated by this service.
     */
    private final Clinic clinic;

    /**
     * Factory used by this service to create new pets.
     */
    private final PetFactory petFactory;

    /**
     * Constructs <code>ClinicService</code> object.
     *
     * @param input input object used for this service.
     * @param output output object used for this service.
     * @param clinic clinic object.
     */
    public ClinicService(final Input input, final Output output, final Clinic clinic) {
        super();
        this.input = input;
        this.output = output;
        this.clinic = clinic;

        this.petFactory = new PetFactory(output);
        this.petFactory.addPet(Bird.class);
        this.petFactory.addPet(Fish.class);
        this.petFactory.addPet(Cat.class);
        this.petFactory.addPet(Dog.class);
        this.petFactory.addPet(CatDog.class);
    }

    /**
     * Get array of all clients in clinic. Unoccupied positions will contain null.
     *
     * @return array of all clients in clinic.
     */
    public Client[] getAllClients() {
        return this.clinic.getClients();
    }

    /**
     * Get array of all pets in clinic.
     *
     * @return array of all pets in clinic.
     */
    public synchronized Pet[] getAllPets() {
        final Pet[] pets = new Pet[this.clinic.getPetList().size()];
        return this.clinic.getPetList().toArray(pets);
    }

    /**
     * Get all known pet types from pet factory.
     *
     * @return array of all known pet types.
     */
    public String[] getKnownPetTypes() {
        return this.petFactory.getKnownTypes();
    }

    /**
     * Get this service's input.
     *
     * @return input.
     */
    public Input getInput() {
        return this.input;
    }

    /**
     * Get this service's output.
     *
     * @return output.
     */
    public Output getOutput() {
        return this.output;
    }

    /**
     * Count clients by pet's name
     *
     * @param name pet's name.
     * @return count of found clients.
     */
    public synchronized int countClientsByPetName(final String name) {
        int count = 0;

        for (final Client client : this.clinic.getClients()) {
            if (client != null && client.getPet() != Pet.NONE && client.getPet().getName().equals(name)) {
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
    public synchronized Client[] findClientsByPetName(final String name) {
        final Client[] resultClients = new Client[this.countClientsByPetName(name)];
        int resultIndex = 0;

        for (final Client client : this.clinic.getClients()) {
            if (client != null && client.getPet() != Pet.NONE && client.getPet().getName().equals(name)) {
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
    public synchronized int findClientPositionByName(final String name) throws ServiceException {
        final Client[] allClients = this.clinic.getClients();
        int resultPosition = -1;

        for (int position = 0; position < allClients.length; position++) {
            if (allClients[position] != null && allClients[position].getName().equals(name)) {
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
    public synchronized Client getClientByPosition(final int position) throws ServiceException {
        this.checkPositionBounds(position);

        final Client client = this.clinic.getClientByPosition(position);

        if (client == null) {
            throw new ServiceException("Client not found");
        }

        return client;
    }

    /**
     * Search client by name.
     *
     * @param name client's name.
     * @return found client.
     * @throws ServiceException thrown if client can't be found.
     */
    public synchronized Client findClientByName(final String name) throws ServiceException {
        int position = this.findClientPositionByName(name);
        return this.getClientByPosition(position);
    }

    /**
     * Get client's pet.
     *
     * @param name client's name.
     * @throws ServiceException thrown if client can't be found or doesn't have pet.
     */
    public synchronized Pet getClientPet(final String name) throws ServiceException {
        final Client client = this.findClientByName(name);

        if (client.getPet() == Pet.NONE) {
            throw new ServiceException("Client doesn't have pet");
        }

        return client.getPet();
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
    public synchronized Client addClient(final int position, final String name)
            throws ServiceException, NameException {

        this.checkPositionBounds(position);
        this.checkClientName(name);

        final Client[] allClients = this.clinic.getClients();

        if (allClients[position] != null) {
            throw new ServiceException("Position is busy");
        }

        final Client client = new Client(name, Pet.NONE, position);
        this.clinic.addClient(position, client);

        return client;
    }

    /**
     * Set client's pet.
     *
     * @param name client's name.
     * @param petType pet's type.
     * @param petName pet's name.
     * @return created pet.
     * @throws ServiceException thrown if client can't be found or pet's type is wrong.
     * @throws NameException thrown if pet's name is wrong.
     */
    public synchronized Pet setClientPet(final String name, final String petType, final String petName)
            throws ServiceException, NameException {

        final Client client = this.findClientByName(name);

        this.checkPetType(petType);
        this.checkPetName(petName);

        final Pet pet = this.petFactory.create(petType, petName);

        this.clinic.getPetList().set(client.getPet(), pet);
        client.setPet(pet);

        return pet;
    }

    /**
     * Update client's name.
     *
     * @param name client's old name.
     * @param newName new name.
     * @throws ServiceException thrown if client can't be found.
     * @throws NameException thrown if new name is wrong.
     */
    public synchronized void updateClientName(final String name, final String newName) throws ServiceException, NameException {
        if (name.equals(newName)) {
            throw new NameException("New name is the same as previous");
        }

        this.checkClientName(newName);

        final Client client = this.findClientByName(name);
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
    public synchronized void updateClientPetName(final String name, final String petName) throws ServiceException, NameException {
        final Pet pet = this.getClientPet(name);

        if (pet.getName().equals(petName)) {
            throw new NameException("New name is the same as previous");
        }

        this.checkPetName(petName);

        pet.setName(petName);
    }

    /**
     * Delete client.
     *
     * @param name client's name.
     * @throws ServiceException thrown if client can't be found.
     */
    public synchronized void deleteClient(final String name) throws ServiceException {
        final int position = this.findClientPositionByName(name);
        final Client client = this.getClientByPosition(position);

        if (client.getPet() != Pet.NONE) {
            this.clinic.getPetList().remove(client.getPet());
        }

        this.clinic.deleteClient(position);
    }

    /**
     * Ask client's pet to make sound.
     *
     * @param name client's name.
     * @throws ServiceException thrown if client can't be found or doesn't have pet.
     */
    public synchronized void askPetMakeSound(final String name) throws ServiceException {
        final Pet pet = this.getClientPet(name);
        pet.makeSound();
    }

    /**
     * Delete client's pet.
     *
     * @param name client's name.
     * @throws ServiceException thrown if client can't be found or he doesn't has pet.
     */
    public synchronized void deleteClientPet(final String name) throws ServiceException {
        final Client client = this.findClientByName(name);

        if (client.getPet() == Pet.NONE) {
            throw new ServiceException("Client doesn't have pet");
        }

        this.clinic.getPetList().remove(client.getPet());
        client.setPet(Pet.NONE);
    }

    /**
     * Checks if provided position is in allowed bounds. If not - throws an exception.
     *
     * @param position client's position.
     * @throws ServiceException thrown if position is out of bounds.
     */
    private void checkPositionBounds(final int position) throws ServiceException {
        if (position < 0 || position >= this.clinic.getSize()) {
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
            if (client != null && client.getName().equals(name)) {
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
    private void checkPetType(final String petType) throws ServiceException {
        if (!this.petFactory.isKnownType(petType)) {
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
