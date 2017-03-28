package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to set client's pet.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class SetClientPet extends ClinicAction {

    /**
     * Constructs <code>SetClientPet</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public SetClientPet(final ClinicService clinicService) {
        super("setPet", "Set client's pet", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");
        final String petType = this.askPetType();
        final String petName = this.ask("Pet's name:");

        try {
            this.getClinicService().setClientPet(name, petType, petName);
        } catch (ServiceException | NameException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Pet was set.");
    }
}
