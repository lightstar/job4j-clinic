package ru.lightstar.clinic.ui.action.drug;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.DrugService;
import ru.lightstar.clinic.drug.Drug;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.pet.Pet;

/**
 * Action to give drug to some client's pet.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class GiveDrugToClientPet extends DrugAction {

    /**
     * Constructs <code>GiveDrugToClientPet</code> object.
     *
     * @param clinicService clinic service operated by action.
     * @param drugService   drug's service operated by action.
     */
    public GiveDrugToClientPet(final ClinicService clinicService, final DrugService drugService) {
        super("give", "Give drug to client's pet", clinicService, drugService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String clientName = this.ask("Client's name:");
        final String drugName = this.askDrugName();

        try {
            final Pet pet = this.getClinicService().getClientPet(clientName);
            final Drug drug = this.getDrugService().takeDrug(drugName);
            this.getOutput().println(String.format("Gave %s to %s.", drug, pet));
        } catch (ServiceException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }
    }
}
