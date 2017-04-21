package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.model.Client;
import ru.lightstar.clinic.exception.NameException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 'NONE' pet tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class NoneTest {

    /**
     * Test correctness of <code>Pet.NONE</code> object.
     */
    @Test
    public void whenPetNoneThenItIsImmutableAndHasEmptyFields() throws NameException {
        Pet.NONE.setId(10);
        Pet.NONE.setName("Test");
        Pet.NONE.setClient(new Client());
        Pet.NONE.setAge(10);
        Pet.NONE.setSex(Sex.F);

        assertThat(Pet.NONE.getId(), is(-1));
        assertThat(Pet.NONE.getName(), is(""));
        assertThat(Pet.NONE.getClient(), is(Client.NONE));
        assertThat(Pet.NONE.getAge(), is(0));
        assertThat(Pet.NONE.getSex(), is(Sex.M));
    }
}
