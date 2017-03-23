package ru.lightstar.clinic;

/**
 * Pet's clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Clinic {

    /**
     * Client's array.
     */
    private final Client[] clients;

    /**
     * Constructs clinic with specified size.
     * @param size clinic's size.
     */
    public Clinic(final int size) {
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
     * Search clients by pet's name.
     *
     * @param name pet's name.
     * @return array of found clients.
     */
    public Client[] findClientsByPetName(final String name) {
        return new Client[]{};
    }
}
