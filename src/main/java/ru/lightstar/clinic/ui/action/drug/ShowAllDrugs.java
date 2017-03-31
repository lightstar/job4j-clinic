package ru.lightstar.clinic.ui.action.drug;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.DrugService;
import ru.lightstar.clinic.drug.Drug;
import ru.lightstar.clinic.exception.ActionException;

import java.util.Map;

/**
 * Action to show all drugs in clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ShowAllDrugs extends DrugAction {

    /**
     * Constructs <code>ShowAllDrugs</code> object.
     *
     * @param clinicService clinic service operated by action.
     * @param drugService   drug's service operated by action.
     */
    public ShowAllDrugs(final ClinicService clinicService, final DrugService drugService) {
        super("show", "Show all drugs", clinicService, drugService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final Map<Drug, Integer> drugMap = this.getDrugService().getAllDrugs();

        if (drugMap.size() == 0) {
            this.getOutput().println("No drugs found.");
            return;
        }

        final StringBuilder resultBuilder = new StringBuilder("Drugs: ");

        boolean isFirst = true;
        for (Map.Entry<Drug, Integer> drugEntry : drugMap.entrySet()) {
            isFirst = this.addCommaIfNeeded(resultBuilder, isFirst);
            this.appendDrugEntry(drugEntry, resultBuilder);
        }

        resultBuilder.append(".");
        this.getOutput().println(resultBuilder.toString());
    }

    /**
     * Append text presentation of drug entry in map to <code>StringBuilder</code>
     *
     * @param drugEntry drug entry.
     * @param resultBuilder <code>StringBuilder</code> object to where text presentation is appended.
     */
    private void appendDrugEntry(final Map.Entry<Drug, Integer> drugEntry, final StringBuilder resultBuilder) {
        if (drugEntry.getValue() > 1) {
            resultBuilder.append(String.format("%s - %s", drugEntry.getKey(), drugEntry.getValue()));
        } else {
            resultBuilder.append(drugEntry.getKey());
        }
    }
}
