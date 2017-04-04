package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.io.Input;
import ru.lightstar.clinic.io.Output;

/**
 * Base class for all actions operating on clinic service.
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class ClinicAction implements Action {

    /**
     * Unique name used as user input to select this action.
     */
    private final String name;

    /**
     * Action description in menu.
     */
    private final String description;

    /**
     * Clinic service operated by this action.
     */
    private final ClinicService clinicService;

    /**
     * Constructs <code>Action</code> object.
     *
     * @param name action's name.
     * @param description action's description.
     * @param clinicService clinic service operated by action.
     */
    public ClinicAction(final String name, final String description, final ClinicService clinicService) {
        super();
        this.name = name;
        this.description = description;
        this.clinicService = clinicService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Get <code>Input</code> object used by clinic service operated by this action.
     *
     * @return <code>Input</code> object.
     */
    public Input getInput() {
        return this.clinicService.getInput();
    }

    /**
     * Get <code>Output</code> object used by clinic service operated by this action.
     *
     * @return <code>Output</code> object.
     */
    public Output getOutput() {
        return this.clinicService.getOutput();
    }

    /**
     * Get <code>ClinicService</code> object operated by this action.
     *
     * @return <code>ClinicService</code> object.
     */
    public ClinicService getClinicService() {
        return this.clinicService;
    }

    /**
     * Ask user a question using <code>Input</code> and <code>Output</code> objects used by inner clinic service.
     *
     * @param question question to ask.
     * @return user's answer.
     */
    protected String ask(final String question) {
        return this.getInput().ask(this.getOutput(), question);
    }

    /**
     * Ask user some client's position.
     *
     * @return client's position entered by user decreased by one (so it is index in clinic's client array).
     * @throws ActionException thrown when user's input is invalid.
     */
    protected int askPosition() throws ActionException {
        try {
            return Integer.valueOf(this.ask("Client's position:")) - 1;
        } catch (NumberFormatException e) {
            throw new ActionException("Client's position must be a number");
        }
    }

    /**
     * Ask user of pet type. Question will contain all available pet types.
     *
     * @return user's answer.
     */
    protected String askPetType() {
        final StringBuilder questionBuilder = new StringBuilder("Pet's type (");

        boolean isFirst = true;
        for (String petType : this.clinicService.getKnownPetTypes()) {
            isFirst = this.addCommaIfNeeded(questionBuilder, isFirst);
            questionBuilder.append(petType);
        }

        questionBuilder.append("):");

        return this.ask(questionBuilder.toString());
    }

    /**
     * Add comma to given <code>StringBuilder</code> if it is not first item in it.
     * Used to create comma-separated lists.
     *
     * @param builder operated <code>StringBuilder</code> object.
     * @param isFirst <code>true</code> if it is first invocation of this method, and <code>false</code> - otherwise.
     * @return always <code>false</code> to set outside <code>isFirst</code> variable to this value.
     */
    protected boolean addCommaIfNeeded(final StringBuilder builder, final boolean isFirst) {
        if (!isFirst) {
            builder.append(", ");
        }
        return false;
    }
}
