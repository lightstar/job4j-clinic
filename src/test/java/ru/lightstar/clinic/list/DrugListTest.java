package ru.lightstar.clinic.list;

import org.junit.Test;
import ru.lightstar.clinic.drug.Aspirin;
import ru.lightstar.clinic.drug.Drug;
import ru.lightstar.clinic.drug.Glucose;

import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * <code>DrugList</code> class tests.
 *
 * @author LightStar
 * @since 0.0.1
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class DrugListTest {

    /**
     * <code>DrugList</code> object used in tests.
     */
    private final DrugList list;

    /**
     * Constructs <code>DrugListTest</code> object.
     */
    public DrugListTest() {
        super();
        this.list = new DrugList();
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
        this.list.add(new Aspirin());
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
        this.list.add(new Aspirin());
        assertThat(this.list.isEmpty(), is(false));
    }

    /**
     * Test correctness of <code>indexOf</code> method.
     */
    @Test
    public void whenIndexOfThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Glucose());
        assertThat(this.list.indexOf(new Glucose()), is(1));
    }

    /**
     * Test correctness of <code>indexOf</code> method for absent drug.
     */
    @Test
    public void whenIndexOfAbsentThenMinusOne() {
        this.list.add(new Aspirin());
        assertThat(this.list.indexOf(new Glucose()), is(-1));
    }

    /**
     * Test correctness of <code>lastIndexOf</code> method.
     */
    @Test
    public void whenLastIndexOfThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Glucose());
        assertThat(this.list.lastIndexOf(new Glucose()), is(2));
    }

    /**
     * Test correctness of <code>lastIndexOf</code> method for absent drug.
     */
    @Test
    public void whenLastIndexOfAbsentThenMinusOne() {
        this.list.add(new Aspirin());
        assertThat(this.list.lastIndexOf(new Glucose()), is(-1));
    }

    /**
     * Test correctness of <code>contains</code> method.
     */
    @Test
    public void whenListContainsDrugThenContainsIsTrue() {
        this.list.add(new Aspirin());
        assertThat(this.list.contains(new Aspirin()), is(true));
    }

    /**
     * Test correctness of <code>contains</code> method for absent drug.
     */
    @Test
    public void whenListNotContainsDrugThenContainsIsFalse() {
        this.list.add(new Aspirin());
        assertThat(this.list.contains(new Glucose()), is(false));
    }

    /**
     * Test correctness of <code>toArray</code> method.
     */
    @Test
    public void whenToArrayThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        assertThat(this.list.toArray(), is(new Object[]{new Aspirin(), new Glucose()}));
        assertThat(this.list.toArray(), instanceOf(Object[].class));
    }

    /**
     * Test correctness of <code>toArray</code> method converting to typed array.
     */
    @Test
    public void whenToTypedArrayThenResult() {
        final Drug[] array = new Drug[2];

        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        assertThat(this.list.toArray(array), is(new Drug[]{new Aspirin(), new Glucose()}));
        assertThat(this.list.toArray(array), is(array));
        assertThat(this.list.toArray(array), instanceOf(Drug[].class));
    }

    /**
     * Test correctness of <code>get</code> method.
     */
    @Test
    public void whenGetThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        assertThat(this.list.get(1), is(new Glucose()));
    }

    /**
     * Test thrown exception in <code>get</code> method when index is out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundsThenException() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());
        this.list.get(3);
    }

    /**
     * Test correctness of <code>set</code> method.
     */
    @Test
    public void whenSetThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());
        this.list.set(2, new Glucose());

        assertThat(this.list.get(2), is(new Glucose()));
    }

    /**
     * Test thrown exception in <code>set</code> method when index is out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetToOutOfBoundsThenException() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());
        this.list.set(3, new Glucose());
    }

    /**
     * Test correctness of <code>add</code> method.
     */
    @Test
    public void whenAddThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        assertThat(this.list.get(0), is(new Aspirin()));
        assertThat(this.list.get(1), is(new Glucose()));
        assertThat(this.list.get(2), is(new Aspirin()));
    }

    /**
     * Test correctness of <code>add</code> method (add at given index).
     */
    @Test
    public void whenAddToIndexThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(1, new Aspirin());

        assertThat(this.list.get(1), is(new Aspirin()));
    }

    /**
     * Test correctness of <code>remove</code> method.
     */
    @Test
    public void whenRemoveThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.remove(new Glucose());

        assertThat(this.list.toArray(), is(new Object[]{new Aspirin()}));
    }

    /**
     * Test correctness of <code>remove</code> method (remove at given index).
     */
    @Test
    public void whenRemoveIndexThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.remove(0);

        assertThat(this.list.toArray(), is(new Object[]{new Glucose()}));
    }

    /**
     * Test correctness of <code>clear</code> method.
     */
    @Test
    public void whenClearThenIsEmptyIsTrue() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());
        this.list.clear();

        assertThat(this.list.isEmpty(), is(true));
    }

    /**
     * Test correctness of <code>containsAll</code> method when it must be <code>true</code>.
     */
    @Test
    public void whenContainsAllThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        assertThat(this.list.containsAll(Arrays.asList(new Aspirin(), new Glucose())), is(true));
    }

    /**
     * Test correctness of <code>containsAll</code> method when it must be <code>false</code>.
     */
    @Test
    public void whenNotContainsAllThenResult() {
        this.list.add(new Aspirin());

        assertThat(this.list.containsAll(Arrays.asList(new Aspirin(), new Glucose())), is(false));
    }

    /**
     * Test correctness of <code>addAll</code> method.
     */
    @Test
    public void whenAddAllThenResult() {
        assertThat(this.list.addAll(Arrays.asList(new Aspirin(), new Glucose())), is(true));
        assertThat(this.list.containsAll(Arrays.asList(new Aspirin(), new Glucose())), is(true));
    }

    /**
     * Test correctness of <code>addAll</code> method (add at given index).
     */
    @Test
    public void whenAddAllToIndexThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Aspirin());

        assertThat(this.list.addAll(1, Arrays.asList(new Glucose(), new Aspirin())), is(true));
        assertThat(this.list.containsAll(Arrays.asList(new Aspirin(), new Glucose(), new Aspirin(), new Aspirin())),
                is(true));
    }

    /**
     * Test correctness of <code>removeAll</code> method.
     */
    @Test
    public void whenRemoveAllThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        this.list.removeAll(Arrays.asList(new Glucose(), new Aspirin()));

        assertThat(this.list.toArray(), is(new Object[]{}));
    }

    /**
     * Test correctness of <code>retainAll</code> method.
     */
    @Test
    public void whenRetainAllThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        this.list.retainAll(Collections.singletonList(new Glucose()));

        assertThat(this.list.toArray(), is(new Object[]{new Glucose()}));
    }

    /**
     * Test iterator's <code>hasNext</code> method.
     */
    @Test
    public void whenIteratorHasNextThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        final ListIterator<Drug> iterator = this.list.listIterator();

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
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        final ListIterator<Drug> iterator = this.list.listIterator();

        assertThat(iterator.next(), is(new Aspirin()));
        assertThat(iterator.next(), is(new Glucose()));
    }

    /**
     * Test iterator's <code>hasPrevious</code> method.
     */
    @Test
    public void whenIteratorHasPreviousThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        final ListIterator<Drug> iterator = this.list.listIterator(2);

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
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        final ListIterator<Drug> iterator = this.list.listIterator(2);

        assertThat(iterator.previous(), is(new Glucose()));
        assertThat(iterator.previous(), is(new Aspirin()));
    }

    /**
     * Test iterator's <code>nextIndex</code> method.
     */
    @Test
    public void whenIteratorNextIndexThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        final ListIterator<Drug> iterator = this.list.listIterator();
        iterator.next();

        assertThat(iterator.nextIndex(), is(1));
    }

    /**
     * Test iterator's <code>previousIndex</code> method.
     */
    @Test
    public void whenIteratorPreviousIndexThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());

        final ListIterator<Drug> iterator = this.list.listIterator();
        iterator.next();

        assertThat(iterator.previousIndex(), is(0));
    }

    /**
     * Test iterator's <code>remove</code> method.
     */
    @Test
    public void whenIteratorRemoveThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        final ListIterator<Drug> iterator = this.list.listIterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertThat(this.list.toArray(), is(new Object[]{new Aspirin(), new Aspirin()}));
    }

    /**
     * Test iterator's <code>set</code> method.
     */
    @Test
    public void whenIteratorSetThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        final ListIterator<Drug> iterator = this.list.listIterator();
        iterator.next();
        iterator.set(new Glucose());

        assertThat(this.list.toArray(), is(new Object[]{new Glucose(), new Glucose(), new Aspirin()}));
    }

    /**
     * Test iterator's <code>add</code> method.
     */
    @Test
    public void whenIteratorAddThenResult() {
        this.list.add(new Aspirin());
        this.list.add(new Glucose());
        this.list.add(new Aspirin());

        final ListIterator<Drug> iterator = this.list.listIterator();
        iterator.next();
        iterator.add(new Glucose());

        assertThat(this.list.toArray(), is(new Object[]{new Aspirin(), new Glucose(), new Glucose(), new Aspirin()}));
    }
}
