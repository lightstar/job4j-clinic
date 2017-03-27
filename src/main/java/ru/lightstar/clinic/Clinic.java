package ru.lightstar.clinic;

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
     * Constructs clinic with specified size.
     * @param size clinic's size.
     */
    public Clinic(final int size) {
        super();
        this.clients = new Client[size];
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
     *
     * @return client's array.
     */
    public Client[] getClients() {
        return Arrays.copyOf(this.clients, this.clients.length);
    }
}
