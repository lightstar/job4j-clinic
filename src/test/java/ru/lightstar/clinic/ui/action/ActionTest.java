package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.Clinic;
import ru.lightstar.clinic.ClinicService;
import ru.lightstar.clinic.IoTestHelper;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.IteratorInput;

/**
 * Base class for all actions tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public abstract class ActionTest {

    /**
     * Size of clinic used in action tests.
     */
    protected final static int CLINIC_SIZE = 3;

    /**
     * <code>ByteArrayOutput</code> object used in action tests.
     */
    protected final ByteArrayOutput output;

    /**
     * <code>IteratorInput</code> object used in action tests.
     */
    protected final IteratorInput input;

    /**
     * <code>Clinic</code> object used in action tests.
     */
    protected final Clinic clinic;

    /**
     * <code>ClinicService</code> object used in action tests.
     */
    protected final ClinicService clinicService;

    /**
     * IO helper object used in action tests.
     */
    protected final IoTestHelper helper;

    /**
     * Tested action object created in concrete test classes.
     */
    protected final Action action;

    /**
     * Constructs <code>ActionTest</code> object.
     */
    public ActionTest() throws NameException, ServiceException {
        super();
        this.output = new ByteArrayOutput();
        this.input = new IteratorInput();
        this.clinic = new Clinic(CLINIC_SIZE);
        this.clinicService = new ClinicService(this.input, this.output, this.clinic);
        this.helper = new IoTestHelper();
        this.action = this.createAction();

        this.clinicService.addClient(0, "Vasya");
        this.clinicService.addClient(1, "Masha");
        this.clinicService.setClientPet("Masha", "cat", "Murka");
    }

    /**
     * Constructs <code>Action</code> object.
     *
     * @return specific <code>Action</code> object.
     */
    protected abstract Action createAction();
}
