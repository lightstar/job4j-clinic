package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to update client pet' name.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class UpdateClientPetName extends ClinicAction {

    /**
     * Constructs <code>UpdateClientPetName</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public UpdateClientPetName(final ClinicService clinicService) {
        super("updatePetName", "Update client pet's name", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");
        final String petName = this.ask("Client pet's new name:");

        try {
            this.getClinicService().updateClientPetName(name, petName);
        } catch (ServiceException | NameException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Client pet's name updated.");
    }
}
