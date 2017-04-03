package ru.lightstar.clinic.list;

import ru.lightstar.clinic.pet.Pet;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * List of all pets in clinic.
 * It is assumed that there is only one such list in program and pet <code>setNextPet</code>/<code>setPrevPet</code>
 * methods are called only from within that list, otherwise things might get broken.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class PetList implements List<Pet> {

    /**
     * First pet in linked list.
     */
    private Pet firstPet;

    /**
     * Last pet in linked list.
     */
    private Pet lastPet;

    /**
     * List's size.
     */
    private int size;

    /**
     * Construct <code>PetList</code> object.
     */
    public PetList() {
        this.firstPet = Pet.NONE;
        this.lastPet = Pet.NONE;
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
    public int indexOf(final Object obj) {
        return this.find(obj).getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(final Object obj) {
        return this.findLast(obj).getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final Object obj) {
        return this.indexOf(obj) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        final Object[] dst = new Object[this.size];
        Pet pet = this.firstPet;

        for (int i = 0; i < this.size; i++) {
            dst[i] = pet;
            pet = pet.getNextPet();
        }

        return dst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] dst) {
        if (!(dst instanceof Pet[])) {
            throw new IllegalArgumentException("Argument is not array of pets");
        }

        if (dst.length < this.size) {
            dst = (T[]) Array.newInstance(dst.getClass().getComponentType(), this.size);
        }

        Pet pet = this.firstPet;
        for (int i = 0; i < this.size; i++) {
            dst[i] = (T) pet;
            pet = pet.getNextPet();
        }

        if (dst.length > this.size) {
            dst[this.size] = null;
        }

        return dst;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet get(final int index) {
        this.checkIndexOutOfBounds(index);

        Pet pet = this.firstPet;
        for (int i = 0; i < index; i++) {
            pet = pet.getNextPet();
        }

        return pet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet set(final int index, final Pet newPet) {
        this.checkIndexOutOfBounds(index);
        return this.set(this.get(index), newPet);
    }

    /**
     * Set new pet instead of old pet. Old pet is removed from list.
     * If old pet is <code>Pet.NONE</code> then new pet is added to the end of list.
     *
     * @param pet old pet.
     * @param newPet new pet.
     * @return old pet.
     */
    public Pet set(final Pet pet, final Pet newPet) {
        if (pet == Pet.NONE) {
            this.add(newPet);
            return Pet.NONE;
        }

        this.checkPetNotInList(pet);
        this.checkPetNone(newPet);
        this.checkPetInList(newPet);

        this.add(pet.getPrevPet(), pet.getNextPet(), newPet);

        pet.setNextPet(Pet.NONE);
        pet.setPrevPet(Pet.NONE);

        return pet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final Pet newPet) {
        this.add(Pet.NONE, newPet);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final int index, final Pet newPet) {
        if (index == this.size) {
            this.add(Pet.NONE, newPet);
        } else {
            this.add(this.get(index), newPet);
        }
    }

    /**
     * Add new pet before given pet.
     * If given pet is <code>Pet.NONE</code>, then add new pet to end of list.
     *
     * @param petBefore new pet is added before this pet.
     * @param pet new pet.
     */
    private void add(final Pet petBefore, final Pet pet) {
        this.checkPetNone(pet);
        this.checkPetInList(pet);

        if (petBefore == Pet.NONE) {
            this.add(this.lastPet, Pet.NONE, pet);
        } else {
            this.checkPetNotInList(petBefore);
            this.add(petBefore.getPrevPet(), petBefore, pet);
        }

        this.size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final Object obj) {
        final Pet pet = this.find(obj).getPet();
        if (pet == Pet.NONE) {
            return false;
        }

        this.remove(pet);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet remove(final int index) {
        return this.remove(this.get(index));
    }

    /**
     * Remove given pet object from this list.
     *
     * @param pet removed pet.
     */
    public Pet remove(final Pet pet) {
        this.checkPetNone(pet);
        this.checkPetNotInList(pet);

        if (pet.getNextPet() != Pet.NONE) {
            pet.getNextPet().setPrevPet(pet.getPrevPet());
        } else {
            this.lastPet = pet.getPrevPet();
        }

        if (pet.getPrevPet() != Pet.NONE) {
            pet.getPrevPet().setNextPet(pet.getNextPet());
        } else {
            this.firstPet = pet.getNextPet();
        }

        pet.setNextPet(Pet.NONE);
        pet.setPrevPet(Pet.NONE);

        this.size--;

        return pet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        Pet pet = this.firstPet;

        while (pet != Pet.NONE) {
            Pet nextPet = pet.getNextPet();
            pet.setNextPet(Pet.NONE);
            pet.setPrevPet(Pet.NONE);
            pet = nextPet;
        }

        this.size = 0;
        this.firstPet = Pet.NONE;
        this.lastPet = Pet.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(final Collection<?> col) {
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
    public boolean addAll(final Collection<? extends Pet> col) {
        for (final Pet newPet : col) {
            this.add(newPet);
        }
        return col.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(final int index, final Collection<? extends Pet> col) {
        if (index == this.size) {
            return this.addAll(col);
        }

        final Pet pet = this.get(index);
        for (final Pet newPet : col) {
            this.add(pet, newPet);
        }

        return col.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(final Collection<?> col) {
        return this.removeAll(col, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(final Collection<?> col) {
        return this.removeAll(col, true);
    }

    /**
     * This list's operation is unsupported.
     */
    @Override
    public List<Pet> subList(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException("Sublist is not supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Pet> iterator() {
        return new PetIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<Pet> listIterator() {
        return new PetIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<Pet> listIterator(final int index) {
        this.checkIndexOutOfBoundsForInsert(index);
        return new PetIterator(index);
    }

    /**
     * Checks if given index is in list's bounds for get/set/add/remove operations.
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
     * Checks if given pet is special 'NONE' value.
     *
     * @param pet checked pet.
     */
    private void checkPetNone(final Pet pet) {
        if (pet == Pet.NONE) {
            throw new IllegalArgumentException("Pet is special 'NONE' value");
        }
    }

    /**
     * Checks if given pet is already in list.
     *
     * @param pet checked pet.
     */
    private void checkPetInList(final Pet pet) {
        if (pet == this.firstPet || pet == this.lastPet ||
                pet.getNextPet() != Pet.NONE || pet.getPrevPet() != Pet.NONE) {
            throw new IllegalArgumentException("Pet is already in list");
        }
    }

    /**
     * Checks if given pet is not in in list.
     *
     * @param pet checked pet.
     */
    private void checkPetNotInList(final Pet pet) {
        if (pet != this.firstPet && pet != this.lastPet &&
                pet.getNextPet() == Pet.NONE && pet.getPrevPet() == Pet.NONE) {
            throw new IllegalArgumentException("Pet is not in list");
        }
    }

    /**
     * Find first pet in list equals to given object.
     *
     * @param obj given object.
     * @return found pet object.
     */
    private FoundPet find(final Object obj) {
        Pet pet = this.firstPet;
        int index = 0;

        while (pet != Pet.NONE && !pet.equals(obj)) {
            pet = pet.getNextPet();
            index++;
        }

        if (pet == Pet.NONE) {
            index = -1;
        }

        return new FoundPet(pet, index);
    }

    /**
     * Find last pet in list equals to given object.
     *
     * @param obj given object.
     * @return found pet object.
     */
    private FoundPet findLast(final Object obj) {
        Pet pet = this.lastPet;
        int index = this.size - 1;

        while (pet != Pet.NONE && !pet.equals(obj)) {
            pet = pet.getPrevPet();
            index--;
        }

        if (pet == Pet.NONE) {
            index = -1;
        }

        return new FoundPet(pet, index);
    }

    /**
     * Add new pet between given two pets in list.
     * Size is not increased in this method.
     *
     * @param petAfter new pet is added after this pet.
     * @param petBefore new pet is added before this pet.
     * @param pet new pet.
     */
    private void add(final Pet petAfter, final Pet petBefore, final Pet pet) {
        if (petAfter != Pet.NONE) {
            petAfter.setNextPet(pet);
        } else {
            this.firstPet = pet;
        }

        if (petBefore != Pet.NONE) {
            petBefore.setPrevPet(pet);
        } else {
            this.lastPet = pet;
        }

        pet.setPrevPet(petAfter);
        pet.setNextPet(petBefore);
    }

    /**
     * Remove all elements, that are in or are not in given collection.
     *
     * @param col given collection.
     * @param inverse if <code>true</code> - remove all elements that are not in given collection, otherwise -
     *                that are in given collection.
     * @return <code>true</code> if list was changed and <code>false</code> otherwise.
     */
    public boolean removeAll(final Collection<?> col, final boolean inverse) {
        Pet pet = this.firstPet;
        boolean isAnyRemoved = false;

        while (pet != Pet.NONE) {
            Pet nextPet = pet.getNextPet();
            if (col.contains(pet) == !inverse) {
                this.remove(pet);
                isAnyRemoved = true;
            }
            pet = nextPet;
        }

        return isAnyRemoved;
    }

    /**
     * Iterator for <code>PetList</code> list.
     */
    private class PetIterator implements ListIterator<Pet> {

        /**
         * Current index of iterator
         */
        private int index;

        /**
         * Current pet of iterator
         */
        private Pet pet;

        /**
         * Last returned pet from iterator.
         */
        private Pet lastPet;

        /**
         * Index of last returned pet from iterator.
         */
        private int lastIndex;

        /**
         * Constructs <code>PetIterator</code> object with zero initial index.
         */
        private PetIterator() {
            this(0);
        }

        /**
         * Constructs <code>PetIterator</code> object.
         *
         * @param index initial index.
         */
        private PetIterator(final int index) {
            this.index = index;
            this.pet = index != PetList.this.size ? PetList.this.get(index) : Pet.NONE;
            this.lastPet = Pet.NONE;
            this.lastIndex = -1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return this.pet != Pet.NONE;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Pet next() {
            if (this.pet == Pet.NONE) {
                throw new IllegalStateException("End of list reached");
            }
            this.lastPet = this.pet;
            this.lastIndex = this.index;
            this.index++;
            this.pet = this.pet.getNextPet();
            return this.lastPet;
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
        public Pet previous() {
            if (this.index == 0) {
                throw new IllegalStateException("End of list reached");
            }
            this.lastPet = this.pet != Pet.NONE ? this.pet.getPrevPet() : PetList.this.lastPet;
            this.pet = this.lastPet;
            this.index--;
            this.lastIndex = this.index;
            return this.lastPet;
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
            if (this.lastPet == Pet.NONE) {
                throw new IllegalStateException("Nothing to remove");
            }

            this.index = this.lastIndex;
            this.pet = this.lastPet.getNextPet();

            PetList.this.remove(this.lastPet);
            this.lastPet = Pet.NONE;
            this.lastIndex = -1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(final Pet pet) {
            if (this.lastPet == Pet.NONE) {
                throw new IllegalStateException("Nowhere to set");
            }

            PetList.this.set(this.lastPet, pet);

            this.lastPet = pet;
            this.pet = this.index == this.lastIndex ? pet : pet.getNextPet();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(final Pet pet) {
            PetList.this.add(this.pet, pet);

            this.index++;
            this.lastPet = Pet.NONE;
            this.lastIndex = -1;
        }
    }

    /**
     * Information about found pet in list.
     */
    private static class FoundPet {

        /**
         * Found pet object.
         */
        private final Pet pet;

        /**
         * Index of found pet in list.
         */
        private final int index;

        /**
         * Constructs <code>FoundPet</code> object.
         *
         * @param pet found pet.
         * @param index found pet's index in list.
         */
        public FoundPet(final Pet pet, final int index) {
            this.pet = pet;
            this.index = index;
        }

        /**
         * Get found pet.
         *
         * @return found pet.
         */
        public Pet getPet() {
            return pet;
        }

        /**
         * Get found pet's index in list.
         *
         * @return found pet's index.
         */
        public int getIndex() {
            return index;
        }
    }
}
