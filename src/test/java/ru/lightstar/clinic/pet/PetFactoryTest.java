package ru.lightstar.clinic.pet;

import org.junit.Test;
import ru.lightstar.clinic.exception.NameException;
import ru.lightstar.clinic.io.DummyOutput;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>PetFactory</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class PetFactoryTest {

    /**
     * <code>PetFactory</code> object used in all tests.
     */
    private final PetFactory petFactory;

    /**
     * Constructs <code>PetFactoryTest</code> object.
     */
    public PetFactoryTest() {
        super();
        this.petFactory = new PetFactory(new DummyOutput());
        this.petFactory.addPet(CatDog.class);
    }

    /**
     * Test correctness of cat-dog's creation.
     */
    @Test
    public void whenCreateCatDogThenCatDogClass() throws NameException {
        final Pet pet = this.petFactory.create("cat-dog", "Murka-Bobik");
        assertThat(pet, instanceOf(CatDog.class));
        assertThat(pet.getName(), is("Murka-Bobik"));
    }

    /**
     * Test exception on unknown pet type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenUnknownTypeThenException() throws NameException {
        this.petFactory.create("unknown", "Unknown");
    }

    /**
     * Test correctness of <code>isKnownType</code> method.
     */
    @Test
    public void whenIsKnownTypeThenResult() {
        assertThat(this.petFactory.isKnownType("cat-dog"), is(true));
        assertThat(this.petFactory.isKnownType("unknown"), is(false));
    }

    /**
     * Test correctness of <code>getKnownTypes</code> method.
     */
    @Test
    public void whenGetKnownTypesThenResult() {
        assertThat(this.petFactory.getKnownTypes(), is(new String[]{"cat-dog"}));
    }
}
