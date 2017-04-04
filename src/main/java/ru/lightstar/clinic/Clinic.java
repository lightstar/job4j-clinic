package ru.lightstar.clinic;

import ru.lightstar.clinic.list.DrugList;
import ru.lightstar.clinic.list.PetList;

import java.util.Arrays;

/**
 * Pet's clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Clinic {

    /**
     * Array of clients.
     */
    private final Client[] clients;

    /**
     * Clinic's drug list.
     */
    private final DrugList drugList;

    /**
     * Clinic's pet list.
     */
    private final PetList petList;

    /**
     * Constructs clinic with specified size.
     * @param size clinic's size.
     */
    public Clinic(final int size) {
        super();
        this.clients = new Client[size];
        this.drugList = new DrugList();
        this.petList = new PetList();
    }

    /**
     * Add client.
     *
     * @param position client's position.
     * @param client client.
     */
    public void addClient(final int position, final Client client) {
        this.clients[position] = client;
    }

    /**
     * Delete client at given position.
     *
     * @param position client's position.
     */
    public void deleteClient(final int position) {
        this.clients[position] = null;
    }

    /**
     * Get size of clinic (number of positions).
     *
     * @return clinic's size.
     */
    public int getSize() {
        return this.clients.length;
    }

    /**
     * Get copy of client's array.
     * Not optimal for performance, but there is no other way to protect inner array from modifications.
     *
     * @return client's array.
     */
    public Client[] getClients() {
        return Arrays.copyOf(this.clients, this.clients.length);
    }

    /**
     * Get client by his position.
     *
     * @param position client's position.
     * @return client or <code>null</code> if there is no client at given position.
     * @throws IndexOutOfBoundsException thrown if given position is out of inner array's bounds.
     */
    public Client getClientByPosition(final int position) {
        return this.clients[position];
    }

    /**
     * Get clinic's drug list.
     *
     * @return drug list.
     */
    public DrugList getDrugList() {
        return this.drugList;
    }

    /**
     * Get clinic's pet list.
     *
     * @return pet list.
     */
    public PetList getPetList() {
        return this.petList;
    }
}
