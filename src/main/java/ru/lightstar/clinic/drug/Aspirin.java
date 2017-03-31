package ru.lightstar.clinic.drug;

/**
 * Aspirin.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Aspirin extends AbstractDrug {

    /**
     * Aspirin's name string.
     */
    public final static String NAME = "aspirin";

    /**
     * Constructs <code>Aspirin</code> object.
     */
    public Aspirin() {
        super(NAME, 2);
    }
}
