package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private Node<T> header;
    private int size = 0;

    LinkedList(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    private LinkedList() {
        this.header = new Node<>(null);
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
        return (indexOf(o) != -1);
    }


    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        if (size == 0) {
            return objects;
        } else {
            Node<T> startNode = header.getNextNode();
            for (int i = 0; i > size - 1; i++) {
                objects[i] = startNode.getValue();
                startNode = startNode.getNextNode();
            }
        }
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) new Object[size];
        }
        Node<T> startNode = header.getNextNode();
        for (int i = 0; i < size; i++) {
            a[i] = (T1) startNode.getValue();
            startNode = startNode.getNextNode();
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        if (size == 0) {
            addFirstNode(t);
        } else {
            Node<T> node = header.getNextNode();
            for (int i = 0; i < size - 1; i++) {
                node = node.getNextNode();
            }
            Node<T> newNode = new Node<>(t);
            node.setNextNode(newNode);
        }
        size++;
        return true;
    }

    private void addFirstNode(T t) {
        Node<T> firstNode = new Node<>(t);
        header.setNextNode(firstNode);
    }

    @Override
    public int indexOf(Object o) {
        Node<T> node = header.getNextNode();
        if (o == null) {
            for (int i = 0; i < size - 1; i++) {
                node = node.getNextNode();
                Object object = node.getValue();
                if (object == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size - 1; i++) {
                Object object = node.getValue();
                if (o.equals(object)) {
                    return i;
                }
                node = node.getNextNode();
            }
        }
        return -1;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }


    private Node<T> getLastNode() {
        if (size == 0) {
            return new Node<>(null);
        }
        Node<T> node = header.getNextNode();
        for (int i = 0; i < size - 1; i++) {
            node = node.getNextNode();
        }
        return node;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) { // TODO : if null or empty
        if (c == null) {
            return false;
        }
        if (c.isEmpty()) {
            return false;
        }
        Node<T> node = header.getNextNode();

        if (size != 0) {
            node = getLastNode();
        }
        for (T objectCollection : c) {
            if (size == 0) {
                Node<T> firstNode = new Node<>(objectCollection);
                header.setNextNode(firstNode);
                node = header.getNextNode();
            } else {
                Node<T> newNode = new Node<>(objectCollection);
                node.setNextNode(newNode);
                node = node.getNextNode();
            }
            size++;
        }
        node.setNextNode(null);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> firstNode = header.getNextNode();
        Object object = firstNode.getValue();
        if (object.equals(o)) {
            Node<T> nextNode = firstNode.getNextNode();
            header.setNextNode(nextNode);
            size--;
            return true;
        }
        Node<T> prevNode = firstNode;
        Node<T> currNode = firstNode.getNextNode();
        for (int i = 1; i < size; i++) {
            Object object1 = currNode.getValue();
            if (object1.equals(o)) {
                Node<T> nextNode = currNode.getNextNode();
                prevNode.setNextNode(nextNode);
                size--;
                return true;
            }
            prevNode = currNode;
            currNode = currNode.getNextNode();
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty()) {
            return false;
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("index number more then size of LinkedList");
        }
        Node<T> node1 = header.getNextNode();


        if (index > 0 && index != size - 1) {
            for (int listIndex = 0; listIndex < index - 1; listIndex++) {
                node1 = node1.getNextNode();
            }
            Node<T> node2 = node1.getNextNode();

            node1 = setNextNewNodesFromCollection(c, node1);
            node1.setNextNode(node2);
        }

        if (index == 0) {
            LinkedList<T> tempList = new LinkedList();
            tempList.addAll(c);
            Node<T> lastNode = tempList.getLastNode();
            lastNode.setNextNode(node1);
            header.setNextNode(tempList.header.getNextNode());
        }

        if (index == (size - 1)) {
            Node<T> lastNode = getLastNode();
            lastNode = setNextNewNodesFromCollection(c, lastNode);
        }


        size += c.size();
        return true;
    }

    private Node<T> setNextNewNodesFromCollection(Collection<? extends T> c, Node<T> node1) {
        for (T objFromC : c) {
            node1.setNextNode(new Node<>(objFromC));
            node1 = node1.getNextNode();
        }
        return node1;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemove = false;
        for (Object objectCollection : c) {
            if (contains(objectCollection)) {
                remove(objectCollection);
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int indexSize = size;
        if (c == null) {
            throw new NullPointerException("Collection<?> c == null");
        }
        Node<T> node = header.getNextNode();

        boolean isChangeList = false;
        boolean isPresentInCollection = false;
        for (int i = 0; i < indexSize; i++) {
            Object objectFromList = node.getValue();

            for (Object objectFromCollection : c) {
                if (objectFromList.equals(objectFromCollection)) {
                    isPresentInCollection = true;
                    break;
                } else {
                    isPresentInCollection = false;
                }
            }
            if (!isPresentInCollection) {
                remove(objectFromList);
                isChangeList = true;
            }
            node = node.getNextNode();
        }
        return isChangeList;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("the index is out of range");
        }
        int i = 0;
        for (Node<T> node = header.getNextNode(); node != null; node = node.getNextNode(), i++) {
            if (i == index) {
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("the index is out of range");
        }
        T object = get(index);
        return (remove(object)) ? object : null;
    }


    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        int count = 0;
        Iterator<T> iteratorList = iterator();
        for (; iteratorList.hasNext(); ){
            Object object = iteratorList.next();
            if(object.equals(o)){
                lastIndex = count;
            }
            count++;
        }
        return lastIndex;
    }


    private class Node<E> {
        private E value;
        private Node<E> nextNode;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIteratorImpl<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl<>();
    }

    private class IteratorImpl<A> implements Iterator<A> {
        private Node current = header;

        @Override
        public boolean hasNext() {
            return current.getNextNode() != null;
        }

        @Override
        public A next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.getNextNode();
            A value = (A) current.getValue();
            return value;
        }
    }

    private class ListIteratorImpl<E> extends IteratorImpl<E> implements ListIterator<E> {
        public ListIteratorImpl() {
        }


        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}
