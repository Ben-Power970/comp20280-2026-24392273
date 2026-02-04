package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    private Node<E> getNode(int i) {
        if (size == 0) {
            return null;
        }
        if (i == -1) {
            return tail;
        }
        Node<E> head = tail.next;
        while (i > 0) {
            head = head.next;
            i--;
        }
        return head;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("Index must be in bounds");
        }
        Node<E> node = getNode(i);
        return node == null ? null : node.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("Index must be in bounds");
        }
        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node<E> oldNode = getNode(i);
            Node<E> oldNodePointer = getNode(i - 1);
            Node<E> newNode = new Node<E>(e, oldNode);
            oldNodePointer.next = newNode;
            size++;
        }
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("Index must be in bounds");
        }
        if (size == 0) {
            return null;
        }
        if (i == 0) {
            return removeFirst();
        } else if (i == size) {
            return removeLast();
        } else {
            Node<E> oldNode = getNode(i);
            Node<E> oldNodePointer = getNode(i - 1);
            Node<E> nextNode = oldNode.next;
            oldNodePointer.next = nextNode;
            size--;
            return oldNode.getData();
        }
    }

    public void rotate() {
        if (size == 0) {
            return;
        }
        tail = tail.next;
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            size--;
            tail = null;
            return null;
        }
        Node<E> first = tail.next;
        Node<E> second = first.next;
        tail.setNext(second);
        size--;
        return first.getData();
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            size--;
            tail = null;
            return null;
        }
        E data = tail.getData();
        Node<E> first = tail.next;
        Node<E> newLast = tail;
        do {
            newLast = newLast.next;
        } while (newLast.next != tail);
        newLast.setNext(first);
        tail = newLast;
        size--;
        return data;
    }

    @Override
    public void addFirst(E e) {
        if (tail == null) {
            tail = new Node<>(e, null);
            tail.next = tail;
            size++;
            return;
        }
        Node<E> newNode = new Node<E>(e, tail.getNext());
        tail.setNext(newNode);
        size++;
    }

    @Override
    public void addLast(E e) {
        if (tail == null) {
            tail = new Node<>(e, null);
            tail.next = tail;
            size++;
            return;
        }
        Node<E> newNode = new Node<E>(e, tail.getNext());
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
