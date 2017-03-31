package ru.lightstar.clinic.list;

import com.sun.istack.internal.NotNull;
import ru.lightstar.clinic.drug.Drug;

import java.util.*;

/**
 * List of all drugs in clinic.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DrugList implements List<Drug> {

    /**
     * Default initial size of inner array.
     */
    private final static int DEFAULT_INITIAL_SIZE = 10;

    /**
     * Inner array of this list.
     */
    private Drug[] drugArray;

    /**
     * Current size of list list.
     */
    private int size;

    /**
     * Constructs <code>DrugList</code> object.
     */
    public DrugList() {
        this(DEFAULT_INITIAL_SIZE);
    }

    /**
     * Constructs <code>DrugList</code> object
     *
     * @param initialSize inner array's initial size.
     */
    public DrugList(final int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Initial size must be greater than zero");
        }
        this.drugArray = new Drug[initialSize];
        this.size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(@NotNull final Object obj) {
        int index = -1;

        for (int i = 0; i < this.size; i++) {
            if (obj.equals(this.drugArray[i])) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(@NotNull final Object obj) {
        int index = -1;

        for (int i = this.size - 1; i >= 0; i--) {
            if (obj.equals(this.drugArray[i])) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(@NotNull final Object obj) {
        return this.indexOf(obj) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.drugArray, this.size, Object[].class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked","SuspiciousSystemArraycopy"})
    public <T> T[] toArray(@NotNull final T[] dst) {
        if (dst.length < this.size || !(dst instanceof Drug[])) {
            return (T[])Arrays.copyOf(this.drugArray, this.size, dst.getClass());
        }

        System.arraycopy(this.drugArray, 0, dst, 0, this.size);

        return dst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Drug get(final int index) {
        this.checkIndexOutOfBounds(index);
        return this.drugArray[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Drug set(final int index, @NotNull final Drug drug) {
        this.checkIndexOutOfBounds(index);
        this.drugArray[index] = drug;
        return drug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(@NotNull final Drug drug) {
        this.add(this.size, drug);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final int index, @NotNull final Drug drug) {
        this.checkIndexOutOfBoundsForInsert(index);
        this.enlargeArrayIfNeeded();
        System.arraycopy(this.drugArray, index, this.drugArray, index + 1, this.size - index);
        this.drugArray[index] = drug;
        this.size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(@NotNull final Object obj) {
        int index = this.indexOf(obj);

        if (index == -1) {
            return false;
        }

        this.remove(index);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Drug remove(final int index) {
        this.checkIndexOutOfBounds(index);
        final Drug removedDrug = this.drugArray[index];

        System.arraycopy(this.drugArray, index + 1, this.drugArray, index, this.size - index - 1);
        this.size--;
        this.drugArray[this.size] = null;

        return removedDrug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.drugArray[i] = null;
        }
        this.size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(@NotNull final Collection<?> col) {
        boolean isContainsAll = true;

        for (final Object obj : col) {
            if (!this.contains(obj)) {
                isContainsAll = false;
                break;
            }
        }

        return isContainsAll;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(@NotNull final Collection<? extends Drug> col) {
        for (final Drug drug : col) {
            this.add(drug);
        }
        return col.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, @NotNull final Collection<? extends Drug> col) {
        for (final Drug drug : col) {
            this.add(index++, drug);
        }
        return col.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(@NotNull final Collection<?> col) {
        return this.removeAll(col, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(@NotNull final Collection<?> col) {
        return this.removeAll(col, true);
    }

    /**
     * This list's operation is unsupported.
     */
    @Override
    public List<Drug> subList(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException("Sublist is not supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Drug> iterator() {
        return new DrugIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<Drug> listIterator() {
        return new DrugIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<Drug> listIterator(final int index) {
        return new DrugIterator(index);
    }

    /**
     * Checks if given index is in list's bounds for get/set/remove operations.
     * If not - throws <code>IndexOutOfBoundsException</code>.
     *
     * @param index checked index.
     */
    private void checkIndexOutOfBounds(final int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(String.format("Index '%d' is out of bounds [0;%d)", index, this.size));
        }
    }

    /**
     * Checks if given index is list's bounds for insert operation.
     * If not - throws <code>IndexOutOfBoundsException</code>.
     *
     * @param index checked index.
     */
    private void checkIndexOutOfBoundsForInsert(final int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("Index '%d' is out of bounds [0;%d]", index, this.size));
        }
    }

    /**
     * Enlarge size of inner array twice if list can't grow anymore with current array.
     */
    private void enlargeArrayIfNeeded() {
        if (this.size == this.drugArray.length) {
            final Drug[] newDrugArray = new Drug[this.drugArray.length << 1];
            System.arraycopy(this.drugArray, 0, newDrugArray, 0, this.size);
            this.drugArray = newDrugArray;
        }
    }

    /**
     * Remove all elements, that are in or are not in given collection.
     *
     * @param col given collection.
     * @param inverse if <code>true</code> - remove all elements that are not in given collection, otherwise -
     *                that are in given collection.
     * @return <code>true</code> if list was changed and <code>false</code> otherwise.
     */
    private boolean removeAll(@NotNull final Collection<?> col, final boolean inverse) {
        int newSize = this.size;
        boolean isAnyRemoved = false;

        for (int i = 0; i < newSize; i++) {
            if (col.contains(this.drugArray[i]) == !inverse) {
                System.arraycopy(this.drugArray, i + 1, this.drugArray, i, newSize - i - 1);
                i--;
                newSize--;
                isAnyRemoved = true;
            }
        }

        for (int i = newSize; i < this.size; i++) {
            this.drugArray[i] = null;
        }

        this.size = newSize;
        return isAnyRemoved;
    }

    /**
     * Iterator for <code>DrugList</code> list.
     */
    private class DrugIterator implements ListIterator<Drug> {

        /**
         * Current index of iterator.
         */
        private int index;

        /**
         * Last returned index of iterator.
         */
        private int lastIndex;

        /**
         * Constructs <code>DrugIterator</code> object with zero initial index.
         */
        private DrugIterator() {
            this(0);
        }

        /**
         * Constructs <code>DrugIterator</code> object.
         *
         * @param index initial index.
         */
        private DrugIterator(final int index) {
            this.index = index;
            this.lastIndex = -1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return this.index < DrugList.this.size;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Drug next() {
            if (this.index == DrugList.this.size) {
                throw new IllegalStateException("End of list reached");
            }
            this.lastIndex = this.index++;
            return DrugList.this.drugArray[this.lastIndex];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasPrevious() {
            return this.index > 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Drug previous() {
            if (this.index == 0) {
                throw new IllegalStateException("Start of list reached");
            }
            this.lastIndex = --this.index;
            return DrugList.this.drugArray[this.lastIndex];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int nextIndex() {
            return this.index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int previousIndex() {
            return this.index - 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (this.lastIndex == -1) {
                throw new IllegalStateException("Nothing to remove");
            }
            DrugList.this.remove(this.lastIndex);
            this.index = this.lastIndex;
            this.lastIndex = -1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(@NotNull final Drug drug) {
            if (this.lastIndex == -1) {
                throw new IllegalStateException("Nowhere to set");
            }
            DrugList.this.set(this.lastIndex, drug);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(@NotNull final Drug drug) {
            DrugList.this.add(this.index, drug);
            this.index++;
            this.lastIndex = -1;
        }
    }
}
