package ru.lightstar.clinic;

import ru.lightstar.clinic.io.Input;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.ui.Menu;
import ru.lightstar.clinic.ui.action.*;
import ru.lightstar.clinic.ui.action.drug.*;

/**
 * Console runner for pet's clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicRunner {

    /**
     * Clinic size.
     */
    public final static int CLINIC_SIZE = 10;

    /**
     * User's main menu.
     */
    private final Menu mainMenu;

    /**
     * Constructs <code>ClinicRunner</code> object.
     *
     * @param input <code>Input</code> object used for user input.
     * @param output <code>Output</code> object used for output.
     */
    public ClinicRunner(final Input input, final Output output) {
        final Clinic clinic = new Clinic(CLINIC_SIZE);
        final ClinicService clinicService = new ClinicService(input, output, clinic);
        final DrugService drugService = new DrugService(clinic);

        final Menu drugMenu = new Menu("drug","Drug operations", input, output);
        drugMenu.addAction(new ShowAllDrugs(clinicService, drugService));
        drugMenu.addAction(new AddDrug(clinicService, drugService));
        drugMenu.addAction(new GiveDrugToClientPet(clinicService, drugService));
        drugMenu.addAction(new Return(output));

        this.mainMenu = new Menu("main", "Main menu", input, output);
        this.mainMenu.addAction(new ShowAllClients(clinicService));
        this.mainMenu.addAction(new FindClientsByPetName(clinicService));
        this.mainMenu.addAction(new FindClientByName(clinicService));
        this.mainMenu.addAction(new AddClient(clinicService));
        this.mainMenu.addAction(new SetClientPet(clinicService));
        this.mainMenu.addAction(new UpdateClientName(clinicService));
        this.mainMenu.addAction(new UpdateClientPetName(clinicService));
        this.mainMenu.addAction(new DeleteClient(clinicService));
        this.mainMenu.addAction(new DeleteClientPet(clinicService));
        this.mainMenu.addAction(new PetMakeSound(clinicService));
        this.mainMenu.addAction(drugMenu);
        this.mainMenu.addAction(new Exit(output));
    }

    /**
     * Run this runner.
     */
    public void run() {
        this.mainMenu.run();
    }
}
