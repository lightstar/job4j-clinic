package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.model.Role;

/**
 * Action to add new client.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class AddClient extends ClinicAction {

    /**
     * Constructs <code>AddClient</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public AddClient(final ClinicService clinicService) {
        super("add", "Add new client",clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");
        final int position = this.askPosition();

        try {
            this.getClinicService().addClient(position, name, "", "", new Role(), "");
        } catch (ServiceException | NameException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Client added.");
    }
}
