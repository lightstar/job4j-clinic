package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.Client;
import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to find client by his name.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class FindClientByName extends ClinicAction {

    /**
     * Constructs <code>FindClientByName</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public FindClientByName(final ClinicService clinicService) {
        super("find", "Find client by name", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");

        try {
            final Client client = this.getClinicService().findClientByName(name);
            this.getOutput().println(String.format("%s.", client.toString()));
        } catch (ServiceException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }
    }
}
