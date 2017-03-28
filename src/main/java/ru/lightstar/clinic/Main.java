package ru.lightstar.clinic;

import ru.lightstar.clinic.io.Console;

/**
 * Entry point for <code>ClinicRunner</code> runner.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Main {

    /**
     * Entry point.
     *
     * @param args not used.
     */
    public static void main(final String[] args) throws NoSuchFieldException, IllegalAccessException {
        final Console console = new Console();
        final ClinicRunner clinicRunner = new ClinicRunner(console, console);
        clinicRunner.run();
    }
}
