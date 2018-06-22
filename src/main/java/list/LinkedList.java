package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T> implements List<T> {
    private Node<T> header;
    private int size = 0;


    public LinkedList() {
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
    public Iterator<T> iterator() {
        return null;
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
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size == 0) {
            Node<T> firstNode = new Node<>(t);
            header.setNextNode(firstNode);
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
    public boolean remove(Object o) {
        Node<T> firstNode = header.getNextNode();
        Object object = firstNode.getValue();
        if (object.equals(o)) {
            Node<T> nextNode = firstNode.getNextNode();
            header.setNextNode(nextNode);
            size--;
            return true;
        }
        Node<T> prevNode = null;
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
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
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
        return null;
    }


    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
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
}
