package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to ask pet to make sound.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class PetMakeSound extends ClinicAction {

    /**
     * Constructs <code>PetMakeSound</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public PetMakeSound(final ClinicService clinicService) {
        super("makeSound", "Ask pet to make sound", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.ask("Client's name:");

        try {
            this.getClinicService().askPetMakeSound(name);
        } catch (ServiceException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }
    }
}
