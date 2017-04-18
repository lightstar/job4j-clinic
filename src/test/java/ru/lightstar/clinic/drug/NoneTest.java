package ru.lightstar.clinic.drug;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 'NONE' drug tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class NoneTest {

    /**
     * Test correctness of <code>Drug.NONE</code> object.
     */
    @Test
    public void whenDrugNoneThenItIsImmutableAndHasEmptyFields() throws NameException {
        Drug.NONE.setId(10);

        assertThat(Drug.NONE.getId(), is(-1));
        assertThat(Drug.NONE.getName(), is(""));
        assertThat(Drug.NONE.getDangerLevel(), is(0));
    }
}
