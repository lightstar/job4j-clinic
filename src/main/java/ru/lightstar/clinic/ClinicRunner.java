package ru.lightstar.clinic;

import ru.lightstar.clinic.io.Console;
import ru.lightstar.clinic.pet.Cat;
import ru.lightstar.clinic.pet.CatDog;
import ru.lightstar.clinic.pet.Dog;

/**
 * Console runner for pet's clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicRunner {

    /**
     * Run.
     */
    public void run() {
        final Clinic clinic = new Clinic(10);
        final Console console = new Console();

        clinic.addClient(0, new Client("Brown", new Cat("Diggy", console)));
        clinic.addClient(1, new Client("Nick", new Dog("Sparky", console)));

        clinic.addClient(
                2, new Client("Ann",
                    new CatDog(
                        new Cat("Tom", console), new Dog("Piccy", console)
                    )
        ));
    }
}
