package ua.com.alevel;

import java.util.*;

public class MatList<E extends Number> implements List<E> {

    private Number[] array;
    private int defaultSize = 10;
    private int elementQty;

    /**----------------------------------------------------
     * constructors
     */
    public MatList() {
        array = new Number[defaultSize];
    }

    public MatList(E[] ... numbers) {
        defaultSize = 0;
        for (E[] currentArray : numbers) {
            defaultSize += currentArray.length;
        }
        this.array = new Number[defaultSize];
        for (E[] currentArray : numbers) {
            addAll(Arrays.asList(currentArray));
        }
    }

    public MatList(MatList ... numbers) {
        defaultSize = 0;
        for (MatList matlist : numbers) {
            Number[] currentArray = matlist.toArray();
            defaultSize += currentArray.length;
        }
        this.array = new Number[defaultSize];
        for (MatList matlist : numbers) {
            addAll(matlist);
        }
    }

    /**----------------------------------------------------
     * methods for homework
     */
    @Override
    public boolean add(E n) {
        checkArrayCapacityBeforeAddingElement();
        this.array[elementQty] = n;
        elementQty++;
        return true;
    }

    public void add(E ... n) {
        for (E currentElem : n) {
            add(currentElem);
        }
    }

    /**----------------------------------------------------
     * methods from interface List that has to be @Override
     */
    @Override
    public void add(int i, E e) {
        if (i >= 0 && i < this.array.length) {
            checkArrayCapacityBeforeAddingElement();
            Number[] arrayAfterIndex = Arrays.copyOfRange(this.array, i, this.array.length);
            this.array[i] = e;
            this.elementQty++;
            System.arraycopy(arrayAfterIndex, 0, this.array, i+1, this.elementQty);
        } else {
            System.out.println("Error! Please enter correct index!");
        }
    }

    public void join(MatList ... ml) {
        for (MatList currentMatList : ml) {
            Number[] currentArray = currentMatList.toArray();
            for (int i = 0; i < currentArray.length; i++) {
                add((E) currentArray[i]);
            }
        }
}

    public void intersection (MatList ... ml) {
        Number[] commonArray = new Number[0];
        MatList commonMatList = new MatList(commonArray);
        for (MatList currentMatList : ml) {
            Number[] currentArray = currentMatList.toArray();
            for (int i = 0; i < currentArray.length; i++) {
                Number inputNum = currentArray[i];
                for (int j = 0; j < this.elementQty; j++) {
                    Number arrayNum = this.array[j];
                    if (arrayNum.equals(inputNum)) {
                        commonMatList.add(arrayNum);
                    }
                }
            }
        }
        this.array = commonMatList.array;
        this.elementQty = commonMatList.elementQty;
    }

    public void sortDesc() {
        Arrays.sort(this.array, 0, this.size(), Collections.reverseOrder());
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        Arrays.sort(this.array, firstIndex, lastIndex, Collections.reverseOrder());
    }

    public void sortDesc(E value) {
        int firstIndexOfValue = indexOf(value);
        Arrays.sort(this.array, firstIndexOfValue, this.elementQty, Collections.reverseOrder());
    }

    public void sortAsc() {
        Arrays.sort(this.array, 0, this.elementQty);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        Arrays.sort(this.array, firstIndex, lastIndex);
    }

    public void sortAsc(E value) {
        Number[] arrayCopy = Arrays.copyOf(this.array, this.size());
        Arrays.sort(arrayCopy, 0, arrayCopy.length);
        int firstIndexOfValue = Arrays.binarySearch(arrayCopy, value);
        Arrays.sort(this.array, firstIndexOfValue, this.size());
}

    public E get(int index) {
        if (index > 0 && index < this.array.length) {
            return (E) this.array[index];
        } else {
            System.out.println("Error! Please check index!");
            return (E) this.array[0];
        }
    }

    public Number getMax() {
        this.sortDesc();
        return this.toArray()[0];
    }

    public Number getMin() {
        this.sortAsc();
        return this.toArray()[0];
    }

    public Number getAverage() {
        double sum = 0.0;
        for (Number currentNum : this.array) {
            if (currentNum != null) {
                sum += currentNum.doubleValue();
            }
        }
        Number average = sum / this.elementQty;
        return average;
    }

    public Number getMedian() {
        if (this.size() % 2 == 0) {
            Number medianForOddSize = (this.array[this.size() / 2 - 1].doubleValue() + this.array[this.size() / 2].doubleValue()) / 2;
            return medianForOddSize;
        } else {
            Number medianForEvenSize = this.array[this.size() / 2];
            return medianForEvenSize;
        }
    }

    public Number[] toArray() {
        return Arrays.copyOf(this.array, this.elementQty);
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        return Arrays.copyOfRange(this.array, firstIndex, lastIndex);
    }

    public MatList cut(int firstIndex, int lastIndex) {
        Number[] array = Arrays.copyOfRange(this.array, firstIndex, lastIndex + 1);
        this.array = array;
        this.elementQty = (lastIndex - firstIndex) + 1;
        return this;
    }

    public void clear() {
        this.array = new Number[defaultSize];
        this.elementQty = 0;
    }

    public void clear(Number[] numbers) {
        Number[] clearArray = this.array;
        for (int i = 0; i < numbers.length; i++) {
            Number inputNum = numbers[i];
            for (int j = 0; j < this.elementQty; j++) {
                Number currentElem = clearArray[j];
                if (inputNum.equals(currentElem)) {
                    remove(j);
                    j--;
                }
            }
        }
    }

    @Override
    public E set(int index, E e) {
        Number oldValue = this.array[index];
        this.array[index] = e;
        return (E) oldValue;
    }

    @Override
    public E remove(int index) {
        Number oldValue = this.array[index];
        int newSize = this.elementQty - 1;
        if (newSize > index) {
            System.arraycopy(this.array, index + 1, this.array, index, newSize - index);
        }
        this.array[newSize] = null;
        this.elementQty--;
        return (E) oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for(int i = 0; i < this.array.length; i++) {
                if (this.array[i] == null) {
                    return i;
                }
            }
        } else {
            for(int i = 0; i < this.array.length; i++) {
                if (o.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for(int i = this.array.length - 1; i >= 0; i--) {
                if (this.array[i] == null) {
                    return i;
                }
            }
        } else {
            for(int i = this.array.length - 1; i >= 0; i--) {
                if (o.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MatList.ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MatList.ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Number[] array = Arrays.copyOfRange(this.array, fromIndex, toIndex);
        return new MatList(array);
    }

    @Override
    public boolean isEmpty() {
        return this.elementQty == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MatList.Itr();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        if (ts.length < defaultSize) {
            return (T[]) Arrays.copyOf(array, array.length, ts.getClass());
        } else {
            return ts;
        }

    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Object[] tempArray = collection.toArray();
            for (Object currentElem : tempArray) {
               this.add((E) currentElem);
            }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        Object[] tempArray = collection.toArray();
        if (i >= 0 && i < this.array.length) {
            checkArrayCapacityBeforeAddingElement();
            Number[] arrayTillIndex = Arrays.copyOfRange(this.array, 0, i+1);
            System.arraycopy(tempArray, 0, arrayTillIndex, i, arrayTillIndex.length + tempArray.length);
            this.array = arrayTillIndex;
            return true;
        } else {
            System.out.println("Error! Please enter correct index!");
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] tempArray = collection.toArray();
        for (int i = 0; i < tempArray.length; i++) {
            for (Number currentElem : this.array) {
                if (tempArray[i].equals(currentElem)) {
                    remove(currentElem);
                }
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Object[] tempArray = collection.toArray();
        for (int i = 0; i < tempArray.length; i++) {
            for (Number currentElem : this.array) {
                if (!tempArray[i].equals(currentElem)) {
                    remove(currentElem);
                }
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.elementQty;
    }

    /**----------------------------------------------------
     * supporting methods
     */
    public void checkArrayCapacityBeforeAddingElement() {
        if (this.elementQty == this.array.length) {
            int newLength;
            if (this.array.length == 0) {
                newLength = 1;
            } else {
                newLength = this.array.length  * 2;
            }
            this.array = Arrays.copyOf(this.array, newLength);
        }
    }
    @Override
    public String toString() {
        String value = "";
        int count = 0;
        for (int i = 0; i < this.elementQty; i++) {
            if (this.array[i] != null) {
                if (value.equals("")) {
                    value = value + "[" + this.array[i];
                } else {
                    value = value + ", " + this.array[i];
                }
                count++;
            }
        }
        if (count == 0) {
            value = "[]";
        } else {
            value = value + "]";
        }
        return value;
    }

    /**----------------------------------------------------
     * private classes for iterator methods from interface List,
     * based on methods from Arraylist class
     */

    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = MatList.this.elementQty;

        Itr() {
        }

        public boolean hasNext() {
            return this.cursor != MatList.this.elementQty;
        }

        public E next() {
            this.checkForComodification();
            int i = this.cursor;
            if (i >= MatList.this.elementQty) {
                throw new NoSuchElementException();
            } else {
                Object[] elementData = MatList.this.array;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i + 1;
                    return (E) array[this.lastRet = i];
                }
            }
        }

        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {
                this.checkForComodification();
                try {
                    MatList.this.remove(this.lastRet);
                    this.cursor = this.lastRet;
                    this.lastRet = -1;
                    this.expectedModCount = MatList.this.elementQty;
                } catch (IndexOutOfBoundsException var2) {
                    throw new ConcurrentModificationException();
                }
            }
        }


        final void checkForComodification() {
            if (MatList.this.elementQty != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends MatList<E>.Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            this.cursor = index;
        }

        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        public int nextIndex() {
            return this.cursor;
        }

        public int previousIndex() {
            return this.cursor - 1;
        }

        public E previous() {
            this.checkForComodification();
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                Object[] elementData = MatList.this.array;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i;
                    return (E) elementData[this.lastRet = i];
                }
            }
        }

        public void set(E e) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {
                this.checkForComodification();
                try {
                    MatList.this.set(this.lastRet, e);
                } catch (IndexOutOfBoundsException var3) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        public void add(E e) {
            this.checkForComodification();
            try {
                int i = this.cursor;
                MatList.this.add(i, e);
                this.cursor = i + 1;
                this.lastRet = -1;
                this.expectedModCount = MatList.this.elementQty;
            } catch (IndexOutOfBoundsException var3) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
