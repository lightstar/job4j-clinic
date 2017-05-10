package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.model.Role;

/**
 * Action to update client's name.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class UpdateClientName extends ClinicAction {

    /**
     * Constructs <code>UpdateClientName</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public UpdateClientName(final ClinicService clinicService) {
        super("updateName", "Update client's name", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");
        final String newName = this.ask("Client's new name:");

        try {
            this.getClinicService().updateClient(name, newName, "", "", new Role(), "");
        } catch (ServiceException | NameException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Client's name updated.");
    }
}
