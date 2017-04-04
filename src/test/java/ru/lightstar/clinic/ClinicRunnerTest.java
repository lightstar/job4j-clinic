package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.IteratorInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>ClinicRunner</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicRunnerTest {

    /**
     * System-dependent line separator.
     */
    private final static String LN = System.getProperty("line.separator");

    /**
     * <code>IteratorInput</code> object used in tests.
     */
    private final IteratorInput input;

    /**
     * <code>ByteArrayOutput</code> object used in tests.
     */
    private final ByteArrayOutput output;

    /**
     * <code>ClinicRunner</code> object used in tests.
     */
    private final ClinicRunner clinicRunner;

    /**
     * Constructs <code>ClinicRunnerTest</code> object.
     */
    public ClinicRunnerTest() {
        super();
        this.input = new IteratorInput();
        this.output = new ByteArrayOutput();
        this.clinicRunner = new ClinicRunner(this.input, this.output);
    }

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunThenResult() {
        this.input.setIterator(Arrays.asList(new String[]{
                "add",
                "Vova",
                "3",

                "add",
                "Vasya",
                "5",

                "add",
                "Masha",
                "10",

                "show",

                "setPet",
                "Masha",
                "cat",
                "Murka",

                "setPet",
                "Vova",
                "fish",
                "Murka",

                "setPet",
                "Vasya",
                "cat-dog",
                "Murka-Rex",

                "show",

                "exit"
        }).iterator());
        this.clinicRunner.run();

        final List<String> result = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            this.resultMenu(result);
            this.resultAddClient(result);
            this.resultWaitEnter(result);
        }

        this.resultMenu(result);
        result.addAll(Arrays.asList(
                "Clinic size: 10.",
                "1. VACANT.",
                "2. VACANT.",
                "3. Vova with no pet.",
                "4. VACANT.",
                "5. Vasya with no pet.",
                "6. VACANT.",
                "7. VACANT.",
                "8. VACANT.",
                "9. VACANT.",
                "10. Masha with no pet."
        ));
        this.resultWaitEnter(result);

        for (int i = 0; i < 3; i++) {
            this.resultMenu(result);
            this.resultSetClientPet(result);
            this.resultWaitEnter(result);
        }

        this.resultMenu(result);
        result.addAll(Arrays.asList(
                "Clinic size: 10.",
                "1. VACANT.",
                "2. VACANT.",
                "3. Vova with fish 'Murka'.",
                "4. VACANT.",
                "5. Vasya with cat-dog 'Murka-Rex'.",
                "6. VACANT.",
                "7. VACANT.",
                "8. VACANT.",
                "9. VACANT.",
                "10. Masha with cat 'Murka'."
        ));
        this.resultWaitEnter(result);

        this.resultMenu(result);
        this.resultByeBye(result);

        assertThat(this.output.toString(), is(String.join(LN, result)));
    }

    /**
     * Add menu lines to list with expected result.
     *
     * @param result list with lines of expected result.
     */
    private void resultMenu(final List<String> result) {
        result.addAll(Arrays.asList(
                "Main menu:",
                "----------",
                "Show all clients (show).",
                "Show all pets (showPets).",
                "Find clients by pet's name (findByPet).",
                "Find client by name (find).",
                "Add new client (add).",
                "Set client's pet (setPet).",
                "Update client's name (updateName).",
                "Update client pet's name (updatePetName).",
                "Delete client (delete).",
                "Delete client's pet (deletePet).",
                "Ask pet to make sound (makeSound).",
                "Drug operations (drug).",
                "Exit from program (exit).",
                "",
                "Choose action:"
        ));
    }

    /**
     * Add lines of expected output when processing <code>AddClient</code> action.
     *
     * @param result list with lines of expected result.
     */
    private void resultAddClient(final List<String> result) {
        result.addAll(Arrays.asList(
                "Client's name:",
                "Client's position:",
                "Client added."
        ));
    }

    /**
     * Add lines of expected output when processing <code>SetClientPet</code> action.
     *
     * @param result list with lines of expected result.
     */
    private void resultSetClientPet(final List<String> result) {
        result.addAll(Arrays.asList(
                "Client's name:",
                "Pet's type (bird, fish, cat, dog, cat-dog):",
                "Pet's name:",
                "Pet was set."
        ));
    }

    /**
     * Add line of waiting for 'enter' to expected result.
     *
     * @param result list with lines of expected result.
     */
    private void resultWaitEnter(final List<String> result) {
        result.add("Press 'Enter' to continue...");
    }

    /**
     * Add finishing lines to expected result.
     *
     * @param result list with lines of expected result.
     */
    private void resultByeBye(final List<String> result) {
        result.addAll(Arrays.asList("Bye, bye!", ""));
    }
}
