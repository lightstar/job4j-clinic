package ru.lightstar.clinic;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.exception.ServiceException;
import ru.lightstar.clinic.io.ByteArrayOutput;
import ru.lightstar.clinic.io.Input;
import ru.lightstar.clinic.io.IteratorInput;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.pet.Pet;
import ru.lightstar.clinic.pet.Sex;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Test of concurrent work.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class ConcurrencyTest {

    /**
     * Size of clinic for tests.
     */
    private static final int CLINIC_SIZE = 10;

    /**
     * Concurrent work of admin and clients.
     */
    @Test
    public void whenConcurrentWorkThenAllIsOk() {
        final Clinic clinic = new Clinic(CLINIC_SIZE);
        final Output output = new ByteArrayOutput();
        final Input input = new IteratorInput();
        final ClinicService clinicService = new ClinicService(input, output, clinic);
        final DrugService drugService = new DrugService(clinic);

        final StringBuffer adminLog = new StringBuffer();
        final Thread adminThread = new Thread(() -> {
            try {
                clinicService.addClient(0, "Vasya", "", "");
                clinicService.addClient(1, "Vova", "", "");
                clinicService.addClient(2, "Masha", "", "");

                drugService.addDrug("aspirin");
                drugService.addDrug("glucose");
                drugService.addDrug("aspirin");

                adminLog.append(String.format("Admin finishes work%n"));
            } catch (ServiceException | NameException e) {
                adminLog.append(String.format("Admin receives error: %s%n", e.getMessage()));
            }
        });

        final StringBuffer vasyaLog = new StringBuffer();
        final Thread vasyaThread = new Thread(() -> {
            int tryCount = 0;
            while (tryCount++ < 3) {
                try {
                    final Client vasya = clinicService.findClientByName("Vasya");
                    vasyaLog.append(String.format("In the beginning Vasya sees: %s%n", vasya));

                    if (vasya.getPet() == Pet.NONE) {
                        clinicService.setClientPet("Vasya", "cat", "Murka", 0, Sex.M);
                        vasyaLog.append(String.format("After setting pet Vasya sees: %s%n",
                                clinicService.findClientByName("Vasya")));
                    }

                    drugService.takeDrug("aspirin");
                    vasyaLog.append(String.format("Vasya takes aspirin for his cat%n"));
                    break;
                } catch (ServiceException | NameException e) {
                    vasyaLog.append(String.format("Vasya receives error: %s%n", e.getMessage()));
                }
            }
        });

        final StringBuffer vovaLog = new StringBuffer();
        final Thread vovaThread = new Thread(() -> {
            int tryCount = 0;
            while (tryCount++ < 3) {
                try {
                    final Client vova = clinicService.findClientByName("Vova");
                    vovaLog.append(String.format("In the beginning Vova sees: %s%n", vova));

                    if (vova.getPet() == Pet.NONE) {
                        clinicService.setClientPet("Vova", "dog", "Bobik", 0, Sex.M);
                        vovaLog.append(String.format("After setting pet Vova sees: %s%n",
                                clinicService.findClientByName("Vova")));
                    }

                    drugService.takeDrug("aspirin");
                    vovaLog.append(String.format("Vova takes aspirin for his cat%n"));
                    break;
                } catch (ServiceException | NameException e) {
                    vovaLog.append(String.format("Vova receives error: %s%n", e.getMessage()));
                }
            }
        });

        final StringBuffer mashaLog = new StringBuffer();
        final Thread mashaThread = new Thread(() -> {
            int tryCount = 0;
            while (tryCount++ < 3) {
                try {
                    final Client masha = clinicService.findClientByName("Masha");
                    mashaLog.append(String.format("In the beginning Masha sees: %s%n", masha));

                    if (masha.getPet() == Pet.NONE) {
                        clinicService.setClientPet("Masha", "fish", "Summer", 0, Sex.M);
                        mashaLog.append(String.format("After setting her pet Masha sees: %s%n",
                                clinicService.findClientByName("Masha")));
                    }

                    drugService.takeDrug("glucose");
                    mashaLog.append(String.format("Masha takes glucose for her fish%n"));
                    break;
                } catch (ServiceException | NameException e) {
                    mashaLog.append(String.format("Masha receives error: %s%n", e.getMessage()));
                }
            }
        });

        adminThread.start();
        vasyaThread.start();
        vovaThread.start();
        mashaThread.start();

        try {
            adminThread.join();
            vasyaThread.join();
            vovaThread.join();
            mashaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Admin log");
        System.out.println("---------");
        System.out.println(adminLog);

        System.out.println();
        System.out.println("Vasya log");
        System.out.println("---------");
        System.out.println(vasyaLog);

        System.out.println();
        System.out.println("Vova log");
        System.out.println("--------");
        System.out.println(vovaLog);

        System.out.println();
        System.out.println("Masha log");
        System.out.println("---------");
        System.out.println(mashaLog);

        assertThat(clinic.getClientByPosition(0).getName(), is("Vasya"));
        assertThat(clinic.getClientByPosition(1).getName(), is("Vova"));
        assertThat(clinic.getClientByPosition(2).getName(), is("Masha"));
        assertThat(clinic.getClientByPosition(0).getPet().getName(), is("Murka"));
        assertThat(clinic.getClientByPosition(1).getPet().getName(), is("Bobik"));
        assertThat(clinic.getClientByPosition(2).getPet().getName(), is("Summer"));
        assertTrue(clinic.getDrugList().isEmpty());
    }
}
