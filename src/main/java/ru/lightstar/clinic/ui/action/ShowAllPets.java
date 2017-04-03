package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.pet.Pet;

/**
 * Action to show all pets in clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ShowAllPets extends ClinicAction {

    /**
     * Constructs <code>ShowAllPets</code> object.
     *
     * @param clinicService clinic service operated by action.
     */
    public ShowAllPets(final ClinicService clinicService) {
        super("showPets", "Show all pets", clinicService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws ActionException {
        final Pet[] allPets = this.getClinicService().getAllPets();

        if (allPets.length == 0) {
            this.getOutput().println("No pets found.");
            return;
        }

        final StringBuilder resultBuilder = new StringBuilder("Pets: ");

        boolean isFirst = true;
        for (final Pet pet : allPets) {
            isFirst = this.addCommaIfNeeded(resultBuilder, isFirst);
            resultBuilder.append(pet);
        }

        resultBuilder.append(".");
        this.getOutput().println(resultBuilder.toString());
    }
}
