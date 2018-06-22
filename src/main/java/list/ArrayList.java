package list;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//test git
public class ArrayList<T> implements List<T> {

    private static final int START_CAPACITY = 8;

    private int capacity;
    private int size = 0;
    private Object[] elements;

    public ArrayList() {
        this.capacity = START_CAPACITY;
        this.elements = new Object[START_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : elements) {
            if (o == null && element == null) {
                return true;
            }
            if (o == null || element == null) {
                return false;
            }
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl<>();
    }


    @Override
    public boolean add(T t) {
        if (size < capacity) {
            elements[size++] = t;
        } else {
            Object[] newElements = new Object[capacity * 2];
            System.arraycopy(elements, 0, newElements, 0, size++);
            this.elements = newElements;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index number more then size of ArrayList");
        }
        return (T) elements[index];
    }

    @Override
    public boolean remove(Object obj) { //TODO: if null!!!??????????-> this.elements= newArray;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (((T) elements[i]).equals(obj)) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }

        if (index == size - 1) {
            elements[index] = null;
            size--;
            return true;
        }
        Object[] newArray = new Object[capacity];
        if (index == 0) {
            System.arraycopy(elements, 1, newArray, 0, --size);
        } else {
            System.arraycopy(elements, 0, newArray, 0, index);
            System.arraycopy(elements, index + 1, newArray, index, size - 1 - index);
            size--;
        }
        this.elements = newArray;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collObj) {
        int sizeOfCollection = collObj.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            Object objectOfElem = elements[i];
            for (Object objectOfColl : collObj) {
                if (objectOfElem == null & objectOfColl == null) {
                    count++;
                } else if (elements[i].equals(objectOfColl)) {
                    count++;
                }
            }
        }
        return sizeOfCollection == count;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int sizeCollection = c.size();
        if (sizeCollection <= 0) {
            return false;
        }
        Object[] newArray = new Object[size + sizeCollection + 1];
        System.arraycopy(elements, 0, newArray, 0, size);
        Object[] arrayOfCollection = c.toArray();
        System.arraycopy(arrayOfCollection, 0, newArray, size + 1, sizeCollection);
        this.elements = newArray;
        size = newArray.length;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size) {
            throw new IndexOutOfBoundsException("index number more then size of ArrayList");
        }
        Object[] arrayFromCollection = c.toArray();
        int sizeCollection = arrayFromCollection.length;
        Object[] newArray = new Object[capacity + sizeCollection];

        if (index == size - 1) {
            System.arraycopy(elements, 0, newArray, 0, size);
            System.arraycopy(arrayFromCollection, 0, newArray, size, sizeCollection);

        } else if (index == 0) {
            System.arraycopy(arrayFromCollection, 0, newArray, 0, sizeCollection);
            System.arraycopy(elements, 0, newArray, sizeCollection, size);

        } else {
            System.arraycopy(elements, 0, newArray, 0, index);// 1 5 6 2 3
            System.arraycopy(arrayFromCollection, 0, newArray, index, sizeCollection);
            System.arraycopy(elements, index, newArray, index + sizeCollection, size - index);
        }
        this.elements = newArray;
        this.size = size + sizeCollection;

        return true;
    }


    @Override
    public void clear() {
        this.elements = new Object[START_CAPACITY];
        this.size = 0;
    }


    @Override
    public void add(int index, T object) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index number more then size of ArrayList");
        }

        Object[] newArray = new Object[capacity + 1];
        if (index == size - 1) {
            add(object);
        } else if (index == 0) {
            newArray[0] = object;
            System.arraycopy(elements, 0, newArray, 1, ++size);
            this.elements = newArray;

        } else {
            System.arraycopy(elements, 0, newArray, 0, index);
            newArray[index] = object;
            System.arraycopy(elements, index, newArray, index + 2, size - index);
            size++;
            this.elements = newArray;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index number more then size of ArrayList");
        }
        Object[] newArray = new Object[size - 1];
        Object elementToDelete = elements[index];
        int newIndex = 0;
        for (int i = 0; i < size; i++) {
            if (i != index) {
                newArray[newIndex] = elements[i];
                newIndex++;
            }
        }
        this.elements = newArray;
        this.size = newArray.length;
        return (T) elementToDelete;
    }

    @Override
    public int indexOf(Object object) {
        int index = -1;
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = -1;
        if (object == null) {
            for (int i = size - 1; i > 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }
        for (int i = size - 1; i > 0; i--) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIteratorImpl<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIteratorImpl<>(index);
    }


    private class IteratorImpl<E> implements Iterator<E> {
        protected int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public E next() {
            if (counter == size) {
                throw new NoSuchElementException();
            }
            return (E) elements[counter++];
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(elements, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException();
        }
        if (a.length < size) {
            T1[] a2 = (T1[]) Array.newInstance(a.getClass(), size); //T1[] a3 = (T1[]) new Object[size];
            System.arraycopy(elements, 0, a2, 0, size);
            return a2;
        }
        System.arraycopy(elements, 0, a, 0, size);
        return a;
    }


    @Override
    public T set(int index, T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index > size");
        }
        T oldElement = (T) elements[index];
        elements[index] = element;
        return oldElement;
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex < 0");
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex > size");
        }
        if (toIndex - fromIndex <= 0) {
            throw new IndexOutOfBoundsException("toIndex-fromIndex <= 0");
        }
        List<T> list = new ArrayList<>();
        System.arraycopy(elements, fromIndex, list, 0, toIndex);
        return list;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection<?> c == null");
        }
        boolean isRemove = false;
        for (Iterator it = c.iterator(); it.hasNext(); ) {
            Object object = it.next();
            if (object == null) {
                throw new NullPointerException("element Collection<?> c == null");
            }
            if (contains(object)) {
                remove(object);
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection<?> c == null");
        }
        boolean isRetain = false;
        for (Iterator it = c.iterator(); it.hasNext(); ) {
            Object object = it.next();
            if (object == null) {
                throw new NullPointerException("element Collection<?> c == null");
            }
            if (!contains(object)) {
                remove(object);
                isRetain = true;
            }
        }
        return isRetain;
    }

    private class ListIteratorImpl<E> extends IteratorImpl<E> implements ListIterator<E> {

        public ListIteratorImpl() {
        }

        public ListIteratorImpl(int counter) {
            this.counter = counter;
        }

        @Override
        public boolean hasPrevious() {
            return counter > 0;
        }

        @Override
        public E previous() {
            if (counter == 0) {
                throw new NoSuchElementException();
            }
            return (E) elements[counter--];
        }

        @Override
        public int nextIndex() {
            return counter == size - 1 ? size : counter + 1;
        }

        @Override
        public int previousIndex() {
            return counter == 0 ? -1 : counter - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

}
