package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.model.Client;
import ru.lightstar.clinic.ClinicService;

/**
 * Action to find all clients by pet name.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class FindClientsByPetName extends ClinicAction {

    /**
     * Constructs <code>FindClientsByPet</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public FindClientsByPetName(final ClinicService clinicService) {
        super("findByPet", "Find clients by pet's name", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final String name = this.ask("Pet's name:");

        final Client[] foundClients = this.getClinicService().findClientsByPetName(name);

        if (foundClients.length == 0) {
            this.getOutput().println("No clients found.");
        } else {
            for (final Client client : foundClients) {
                this.getOutput().println(String.format("%s.", client.toString()));
            }
        }
    }
}
