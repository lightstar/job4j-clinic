package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.Client;
import ru.lightstar.clinic.ClinicService;

/**
 * Action to show all clients in clinic with their pets.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ShowAllClients extends ClinicAction {

    /**
     * Constructs <code>ShowAllClients</code> object.
     *
     * @param clinicService clinic service operated by action
     */
    public ShowAllClients(final ClinicService clinicService) {
        super("showAll", "Show all clients",clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final Client[] allClients = this.getClinicService().getAllClients();

        this.getOutput().println(String.format("Clinic size: %d.", allClients.length));

        for (int i = 0; i < allClients.length; i++) {
            final Client client = allClients[i];
            if (client == null) {
                this.getOutput().println(String.format("%d. VACANT.", i + 1));
            } else {
                this.getOutput().println(String.format("%s.", client));
            }
        }
    }
}
