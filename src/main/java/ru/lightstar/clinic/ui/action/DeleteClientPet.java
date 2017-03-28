package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to delete client's pet.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DeleteClientPet extends ClinicAction {

    /**
     * Constructs <code>DeleteClientPet</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public DeleteClientPet(final ClinicService clinicService) {
        super("deletePet", "Delete client's pet", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");

        try {
            this.getClinicService().deleteClientPet(name);
        } catch (ServiceException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Client's pet deleted.");
    }
}
