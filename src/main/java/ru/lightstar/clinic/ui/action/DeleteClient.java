package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to delete client from clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DeleteClient extends ClinicAction {

    /**
     * Constructs <code>DeleteClient</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public DeleteClient(final ClinicService clinicService) {
        super("delete", "Delete client", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");

        try {
            this.getClinicService().deleteClient(name);
        } catch (ServiceException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Client deleted.");
    }
}
