package ru.lightstar.clinic.list;

import org.junit.Test;
import ru.lightstar.clinic.io.DummyOutput;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.pet.*;

import java.util.Arrays;
import java.util.ListIterator;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <code>PetList</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class PetListTest {

    /**
     * <code>PetList</code> object used in tests.
     */
    private final PetList list;

    /**
     * <code>Output</code> object used to create pets.
     */
    private final Output output;

    /**
     * Constructs <code>PetListTest</code> object.
     */
    public PetListTest() {
        super();
        this.list = new PetList();
        this.output = new DummyOutput();
    }

    /**
     * Test correctness of <code>size</code> method when list is empty.
     */
    @Test
    public void whenListIsEmptyThenSizeIsZero() {
        assertThat(this.list.size(), is(0));
    }

    /**
     * Test correctness of <code>size</code> method when list is not empty.
     */
    @Test
    public void whenSizeThenResult() {
        this.list.add(new Dog("Bobik", this.output));
        assertThat(this.list.size(), is(1));
    }

    /**
     * Test correctness of <code>isEmpty</code> method when list is empty;
     */
    @Test
    public void whenListIsEmptyThenIsEmptyIsTrue() {
        assertThat(this.list.isEmpty(), is(true));
    }

    /**
     * Test correctness of <code>isEmpty</code> method when list is not empty.
     */
    @Test
    public void whenListIsNonEmptyThenIsEmptyIsFalse() {
        this.list.add(new Dog("Bobik", this.output));
        assertThat(this.list.isEmpty(), is(false));
    }

    /**
     * Test correctness of <code>indexOf</code> method.
     */
    @Test
    public void whenIndexOfThenResult() {
        final Cat cat = new Cat("Murka", this.output);
        this.list.add(new Dog("Bobik", this.output));
        this.list.add(cat);
        assertThat(this.list.indexOf(cat), is(1));
    }

    /**
     * Test correctness of <code>indexOf</code> method for absent pet.
     */
    @Test
    public void whenIndexOfAbsentThenMinusOne() {
        this.list.add(new Dog("Bobik", this.output));
        assertThat(this.list.indexOf(new Cat("Murka", this.output)), is(-1));
    }

    /**
     * Test correctness of <code>lastIndexOf</code> method.
     */
    @Test
    public void whenLastIndexOfThenResult() {
        final Cat cat = new Cat("Murka", this.output);
        this.list.add(new Dog("Bobik", this.output));
        this.list.add(cat);
        assertThat(this.list.lastIndexOf(cat), is(1));
    }

    /**
     * Test correctness of <code>lastIndexOf</code> method for absent pet.
     */
    @Test
    public void whenLastIndexOfAbsentThenMinusOne() {
        this.list.add(new Dog("Bobik", this.output));
        assertThat(this.list.lastIndexOf(new Cat("Murka", this.output)), is(-1));
    }

    /**
     * Test correctness of <code>contains</code> method.
     */
    @Test
    public void whenListContainsЗуеThenContainsIsTrue() {
        final Dog dog = new Dog("Bobik", this.output);
        this.list.add(dog);
        assertThat(this.list.contains(dog), is(true));
    }

    /**
     * Test correctness of <code>contains</code> method for absent pet.
     */
    @Test
    public void whenListNotContainsЗуеThenContainsIsFalse() {
        this.list.add(new Dog("Bobik", this.output));
        assertThat(this.list.contains(new Cat("Murka", this.output)), is(false));
    }

    /**
     * Test correctness of <code>toArray</code> method.
     */
    @Test
    public void whenToArrayThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        this.list.add(dog);
        this.list.add(cat);

        assertThat(this.list.toArray(), is(new Object[]{dog, cat}));
        assertThat(this.list.toArray(), instanceOf(Object[].class));
    }

    /**
     * Test correctness of <code>toArray</code> method converting to typed array.
     */
    @Test
    public void whenToTypedArrayThenResult() {
        final Pet[] array = new Pet[2];

        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        this.list.add(dog);
        this.list.add(cat);

        assertThat(this.list.toArray(array), is(new Pet[]{dog, cat}));
        assertThat(this.list.toArray(array), is(array));
        assertThat(this.list.toArray(array), instanceOf(Pet[].class));
    }

    /**
     * Test correctness of <code>get</code> method.
     */
    @Test
    public void whenGetThenResult() {
        final Dog dog = new Dog("Bobik", this.output);

        this.list.add(new Cat("Murka", this.output));
        this.list.add(dog);
        this.list.add(new Fish("Beauty", this.output));

        assertThat(this.list.get(1), is(dog));
    }

    /**
     * Test thrown exception in <code>get</code> method when index is out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundsThenException() {
        this.list.add(new Cat("Murka", this.output));
        this.list.add(new Fish("Beauty", this.output));
        this.list.add(new Dog("Bobik", this.output));
        this.list.get(3);
    }

    /**
     * Test correctness of <code>set</code> method.
     */
    @Test
    public void whenSetThenResult() {
        final Dog dog = new Dog("Rex", this.output);

        this.list.add(new Cat("Murka", this.output));
        this.list.add(new Fish("Beauty", this.output));
        this.list.add(new Dog("Bobik", this.output));
        this.list.set(2, dog);

        assertThat(this.list.get(2), is(dog));
    }

    /**
     * Test thrown exception in <code>set</code> method when index is out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetToOutOfBoundsThenException() {
        this.list.add(new Cat("Murka", this.output));
        this.list.add(new Fish("Beauty", this.output));
        this.list.add(new Dog("Bobik", this.output));
        this.list.set(3, new Dog("Rex", this.output));
    }

    /**
     * Test correctness of <code>set</code> method with two <code>Pet</code> params.
     */
    @Test
    public void whenSetWithTwoPetParamsThenResult() {
        final Cat cat = new Cat("Murka", this.output);
        final Dog dog = new Dog("Bobik", this.output);

        this.list.add(new Fish("Beauty", this.output));
        this.list.add(cat);
        this.list.add(new Dog("Bobik", this.output));

        this.list.set(cat, dog);

        assertThat(this.list.get(1), is(dog));
    }

    /**
     * Test correctness of <code>add</code> method.
     */
    @Test
    public void whenAddThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(fish);

        assertThat(this.list.get(0), is(dog));
        assertThat(this.list.get(1), is(cat));
        assertThat(this.list.get(2), is(fish));
    }

    /**
     * Test correctness of <code>add</code> method (add at given index).
     */
    @Test
    public void whenAddToIndexThenResult() {
        final Dog dog = new Dog("Bobik", this.output);

        this.list.add(new Cat("Murka", this.output));
        this.list.add(new Fish("Beauty", this.output));
        this.list.add(1, dog);

        assertThat(this.list.get(1), is(dog));
    }

    /**
     * Test correctness of <code>add</code> method with two <code>Pet</code> params.
     */
    @Test
    public void whenAddWithTwoPetParamsThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(dog, fish);

        assertThat(this.list.get(0), is(fish));
        assertThat(this.list.get(1), is(dog));
        assertThat(this.list.get(2), is(cat));
    }

    /**
     * Test correctness of <code>remove</code> method.
     */
    @Test
    public void whenRemoveThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.remove(cat);

        assertThat(this.list.toArray(), is(new Object[]{dog}));
    }

    /**
     * Test correctness of <code>remove</code> method (remove at given index).
     */
    @Test
    public void whenRemoveIndexThenResult() {
        final Dog dog = new Dog("Bobik", this.output);

        this.list.add(new Cat("Murka", this.output));
        this.list.add(dog);
        this.list.remove(0);

        assertThat(this.list.toArray(), is(new Object[]{dog}));
    }

    /**
     * Test correctness of <code>clear</code> method.
     */
    @Test
    public void whenClearThenIsEmptyIsTrue() {
        this.list.add(new Cat("Murka", this.output));
        this.list.add(new Dog("Bobik", this.output));
        this.list.clear();

        assertThat(this.list.isEmpty(), is(true));
    }

    /**
     * Test correctness of <code>containsAll</code> method when it must be <code>true</code>.
     */
    @Test
    public void whenContainsAllThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);

        this.list.add(dog);
        this.list.add(cat);

        assertThat(this.list.containsAll(Arrays.asList(dog, cat)), is(true));
    }

    /**
     * Test correctness of <code>containsAll</code> method when it must be <code>false</code>.
     */
    @Test
    public void whenNotContainsAllThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);

        this.list.add(dog);

        assertThat(this.list.containsAll(Arrays.asList(dog, cat)), is(false));
    }

    /**
     * Test correctness of <code>addAll</code> method.
     */
    @Test
    public void whenAddAllThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);

        assertThat(this.list.addAll(Arrays.asList(dog, cat)), is(true));
        assertThat(this.list.containsAll(Arrays.asList(dog, cat)), is(true));
    }

    /**
     * Test correctness of <code>addAll</code> method (add at given index).
     */
    @Test
    public void whenAddAllToIndexThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);
        final Bird bird = new Bird("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);

        assertThat(this.list.addAll(1, Arrays.asList(fish, bird)), is(true));
        assertThat(this.list.containsAll(Arrays.asList(dog, fish, bird, cat)),
                is(true));
    }

    /**
     * Test correctness of <code>removeAll</code> method.
     */
    @Test
    public void whenRemoveAllThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(fish);
        this.list.removeAll(Arrays.asList(cat, fish));

        assertThat(this.list.toArray(), is(new Object[]{dog}));
    }

    /**
     * Test correctness of <code>retainAll</code> method.
     */
    @Test
    public void whenRetainAllThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(fish);
        this.list.retainAll(Arrays.asList(cat, fish));

        assertThat(this.list.toArray(), is(new Object[]{cat, fish}));
    }

    /**
     * Test iterator's <code>hasNext</code> method.
     */
    @Test
    public void whenIteratorHasNextThenResult() {
        this.list.add(new Dog("Bobik", this.output));
        this.list.add(new Cat("Murka", this.output));

        final ListIterator<Pet> iterator = this.list.listIterator();

        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Test iterator's <code>next</code> method.
     */
    @Test
    public void whenIteratorNextThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);

        this.list.add(dog);
        this.list.add(cat);
        final ListIterator<Pet> iterator = this.list.listIterator();

        assertThat(iterator.next(), is(dog));
        assertThat(iterator.next(), is(cat));
    }

    /**
     * Test iterator's <code>hasPrevious</code> method.
     */
    @Test
    public void whenIteratorHasPreviousThenResult() {
        this.list.add(new Dog("Bobik", this.output));
        this.list.add(new Cat("Murka", this.output));

        final ListIterator<Pet> iterator = this.list.listIterator(2);

        assertThat(iterator.hasPrevious(), is(true));
        iterator.previous();
        assertThat(iterator.hasPrevious(), is(true));
        iterator.previous();
        assertThat(iterator.hasPrevious(), is(false));
    }

    /**
     * Test iterator's <code>previous</code> method.
     */
    @Test
    public void whenIteratorPreviousThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);

        this.list.add(dog);
        this.list.add(cat);
        final ListIterator<Pet> iterator = this.list.listIterator(2);

        assertThat(iterator.previous(), is(cat));
        assertThat(iterator.previous(), is(dog));
    }

    /**
     * Test iterator's <code>nextIndex</code> method.
     */
    @Test
    public void whenIteratorNextIndexThenResult() {
        this.list.add(new Dog("Bobik", this.output));
        this.list.add(new Cat("Murka", this.output));

        final ListIterator<Pet> iterator = this.list.listIterator();
        iterator.next();

        assertThat(iterator.nextIndex(), is(1));
    }

    /**
     * Test iterator's <code>previousIndex</code> method.
     */
    @Test
    public void whenIteratorPreviousIndexThenResult() {
        this.list.add(new Dog("Bobik", this.output));
        this.list.add(new Cat("Murka", this.output));

        final ListIterator<Pet> iterator = this.list.listIterator();
        iterator.next();

        assertThat(iterator.previousIndex(), is(0));
    }

    /**
     * Test iterator's <code>remove</code> method.
     */
    @Test
    public void whenIteratorRemoveThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(fish);

        final ListIterator<Pet> iterator = this.list.listIterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertThat(this.list.toArray(), is(new Object[]{dog, fish}));
    }

    /**
     * Test iterator's <code>set</code> method.
     */
    @Test
    public void whenIteratorSetThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);
        final Fish bird = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(fish);

        final ListIterator<Pet> iterator = this.list.listIterator();
        iterator.next();
        iterator.set(bird);

        assertThat(this.list.toArray(), is(new Object[]{bird, cat, fish}));
    }

    /**
     * Test iterator's <code>add</code> method.
     */
    @Test
    public void whenIteratorAddThenResult() {
        final Dog dog = new Dog("Bobik", this.output);
        final Cat cat = new Cat("Murka", this.output);
        final Fish fish = new Fish("Beauty", this.output);
        final Fish bird = new Fish("Beauty", this.output);

        this.list.add(dog);
        this.list.add(cat);
        this.list.add(fish);

        final ListIterator<Pet> iterator = this.list.listIterator();
        iterator.next();
        iterator.add(bird);

        assertThat(this.list.toArray(), is(new Object[]{dog, bird, cat, fish}));
    }
}
