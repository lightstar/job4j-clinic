package ru.lightstar.clinic.ui.action.drug;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.DrugService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ServiceException;

/**
 * Action to add new drug.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class AddDrug extends DrugAction {

    /**
     * Constructs <code>AddDrug</code> object.
     *
     * @param clinicService clinic service operated by action.
     * @param drugService   drug's service operated by action.
     */
    public AddDrug(final ClinicService clinicService, final DrugService drugService) {
        super("add", "Add new drug", clinicService, drugService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final String name = this.askDrugName();

        try {
            this.getDrugService().addDrug(name);
        } catch (ServiceException e) {
            throw new ActionException(String.format("%s.", e.getMessage()));
        }

        this.getOutput().println("Drug added.");
    }
}
