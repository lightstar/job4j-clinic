package ru.lightstar.clinic.drug;

/**
 * Glucose.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Glucose extends AbstractDrug {

    /**
     * Glucose's name string.
     */
    public final static String NAME = "glucose";

    /**
     * Constructs <code>Glucose</code> object.
     */
    public Glucose() {
        super(NAME, 1);
    }
}
