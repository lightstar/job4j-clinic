package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.IteratorInput;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>ClinicRunner</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ClinicRunnerTest {

    /**
     * Test correctness of <code>run</code> method.
     */
    @Test
    public void whenRunAddClientThenResult() {
        final IteratorInput input = new IteratorInput();
        final ByteArrayOutput output = new ByteArrayOutput();
        final ClinicRunner clinicRunner = new ClinicRunner(input, output);
        final IoTestHelper helper = new IoTestHelper();

        input.setIterator(Arrays.asList(new String[]{
                "add",
                "Vova",
                "3",
                "showAll",
                "exit"
        }).iterator());
        clinicRunner.run();

        assertThat(output.toString(), is(helper.joinLines(new String[]{
                "Menu:",
                "-----",
                "Show all clients (showAll).",
                "Find clients by pet's name (findByPet).",
                "Find client by name (find).",
                "Add new client (add).",
                "Set client's pet (setPet).",
                "Update client's name (updateName).",
                "Update client pet's name (updatePetName).",
                "Delete client (delete).",
                "Delete client's pet (deletePet).",
                "Exit from program (exit).",
                "",
                "Choose action:",

                "Client's name:",
                "Client's position:",
                "Client added.",
                "",

                "Menu:",
                "-----",
                "Show all clients (showAll).",
                "Find clients by pet's name (findByPet).",
                "Find client by name (find).",
                "Add new client (add).",
                "Set client's pet (setPet).",
                "Update client's name (updateName).",
                "Update client pet's name (updatePetName).",
                "Delete client (delete).",
                "Delete client's pet (deletePet).",
                "Exit from program (exit).",
                "",
                "Choose action:",

                "Clinic size: 10.",
                "1. VACANT.",
                "2. VACANT.",
                "3. Vova with no pet.",
                "4. VACANT.",
                "5. VACANT.",
                "6. VACANT.",
                "7. VACANT.",
                "8. VACANT.",
                "9. VACANT.",
                "10. VACANT.",
                "",

                "Menu:",
                "-----",
                "Show all clients (showAll).",
                "Find clients by pet's name (findByPet).",
                "Find client by name (find).",
                "Add new client (add).",
                "Set client's pet (setPet).",
                "Update client's name (updateName).",
                "Update client pet's name (updatePetName).",
                "Delete client (delete).",
                "Delete client's pet (deletePet).",
                "Exit from program (exit).",
                "",
                "Choose action:",

                "Bye, bye!"
        })));
    }
}
