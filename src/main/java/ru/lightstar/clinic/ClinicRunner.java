package ru.lightstar.clinic;

import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.ExitException;
import ru.lightstar.clinic.exception.MenuException;
import ru.lightstar.clinic.io.Input;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.pet.*;
import ru.lightstar.clinic.ui.Menu;
import ru.lightstar.clinic.ui.action.*;

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
     * Clinic service.
     */
    private final ClinicService clinicService;

    /**
     * User menu.
     */
    private final Menu menu;

    /**
     * Constructs <code>ClinicRunner</code> object.
     *
     * @param input <code>Input</code> object used for user input.
     * @param output <code>Output</code> object used for output.
     */
    public ClinicRunner(final Input input, final Output output) {
        this.clinicService = new ClinicService(input, output, new Clinic(CLINIC_SIZE));
        this.menu = new Menu(input, output);

        this.menu.addAction(new ShowAllClients(this.clinicService));
        this.menu.addAction(new FindClientsByPetName(this.clinicService));
        this.menu.addAction(new FindClientByName(this.clinicService));
        this.menu.addAction(new AddClient(this.clinicService));
        this.menu.addAction(new SetClientPet(this.clinicService));
        this.menu.addAction(new UpdateClientName(this.clinicService));
        this.menu.addAction(new UpdateClientPetName(this.clinicService));
        this.menu.addAction(new DeleteClient(this.clinicService));
        this.menu.addAction(new DeleteClientPet(this.clinicService));
        this.menu.addAction(new Exit(this.clinicService.getOutput()));
    }

    /**
     * Run this runner.
     */
    public void run() {
        while(true) {
            this.menu.show();

            try {
                this.menu.run();
            } catch(ExitException e) {
                break;
            } catch (MenuException | ActionException e) {
                this.getOutput().println(e.getMessage());
            }

            this.getOutput().println("");
        }
    }

    /**
     * Get output object.
     *
     * @return <code>Output</code> object.
     */
    private Output getOutput() {
        return this.clinicService.getOutput();
    }
}
