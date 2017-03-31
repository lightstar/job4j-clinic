package ru.lightstar.clinic.ui.action.drug;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.DrugService;
import ru.lightstar.clinic.ui.action.ClinicAction;

/**
 * Base class for all actions operating on drug service.
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class DrugAction extends ClinicAction {

    /**
     * Drug service operated by this action.
     */
    private final DrugService drugService;

    /**
     * Constructs <code>DrugAction</code> object.
     *
     * @param name          action's name.
     * @param description   action's description.
     * @param clinicService clinic service operated by action.
     * @param drugService   drug's service operated by action.
     */
    public DrugAction(final String name, final String description, final ClinicService clinicService,
                      final DrugService drugService) {
        super(name, description, clinicService);
        this.drugService = drugService;
    }

    /**
     * Get <code>DrugService</code> object operated by this action.
     *
     * @return <code>DrugService</code> object.
     */
    public DrugService getDrugService() {
        return this.drugService;
    }

    /**
     * Ask user of drug name. Question will contain all available drug names.
     *
     * @return user's answer.
     */
    protected String askDrugName() {
        final StringBuilder questionBuilder = new StringBuilder("Drug's name (");

        boolean isFirst = true;
        for (String drugName : this.drugService.getKnownDrugNames()) {
            isFirst = this.addCommaIfNeeded(questionBuilder, isFirst);
            questionBuilder.append(drugName);
        }

        questionBuilder.append("):");
        return this.ask(questionBuilder.toString());
    }
}
