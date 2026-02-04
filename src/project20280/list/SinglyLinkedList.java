package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E get(int position) {
        assert(position >= 0 && position <= size());
        if (position == size) {
            return null;
        }
        Node<E> curr = head;
        while (position > 0) {
            curr = curr.getNext();
            position--;
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        assert(position >= 0 && position <= size());
        if (head == null) {
            head = new Node<E>(e, null);
            size++;
            return;
        }
        Node<E> prev = head;
        Node<E> curr = head;
        while (position > 0) {
            prev = curr;
            curr = curr.getNext();
            position--;
        }
        Node<E> newNode = new Node<E>(e, curr);
        prev.setNext(newNode);
        size++;
    }


    @Override
    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        if (head == null) {
            head = new Node<E>(e, null);
            size++;
            return;
        }
        Node<E> curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        Node<E> newNode = new Node<E>(e, null);
        curr.setNext(newNode);
        size++;
    }

    @Override
    public E remove(int position) {
        if (head == null) {
            return null;
        }
        assert(position >= 0 && position < size());
        Node<E> prev = head;
        Node<E> curr = head;
        while (position > 0) {
            prev = curr;
            curr = curr.getNext();
            position--;
        }
        Node<E> next = curr.getNext();
        prev.setNext(next);
        size--;
        if (size == 0) {
            head = null;
        }
        return curr.getElement();
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            head = null;
        }
        return element;
    }

    @Override
    public E removeLast() {
        if (head == null) {
            return null;
        }
        Node<E> prev = head;
        Node<E> curr = head;
        while (curr.getNext() != null) {
            prev = curr;
            curr = curr.getNext();
        }
        prev.setNext(null);
        size--;
        if (size == 0) {
            head = null;
        }
        return curr.getElement();
    }

    public SinglyLinkedList<Integer> sortedMerge(SinglyLinkedList<Integer> other) {
        int thisIndex = 0;
        int otherIndex = 0;

        SinglyLinkedList<Integer> newList = new SinglyLinkedList<Integer>();
        while (get(thisIndex) != null || other.get(otherIndex) != null) {
            Integer thisData = (Integer) get(thisIndex);
            Integer otherData = (Integer) other.get(otherIndex);
            if (thisData == null) {
                newList.addLast(otherData);
                otherIndex++;
            } else if (otherData == null) {
                newList.addLast(thisData);
                thisIndex++;
            } else if (thisData.equals(otherData)) {
                newList.addLast(thisData);
                thisIndex++;
            } else if (thisData.compareTo(otherData) < 0) {
                newList.addLast(thisData);
                thisIndex++;
            } else {
                newList.addLast(otherData);
                otherIndex++;
            }
        }
        return newList;
    }

    public void reverse() {
        if (size < 1) {
            return;
        }
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next = head.next;
        while (next != null) {
            curr.setNext(prev);
            prev = curr;
            curr = next;
            next = next.next;
            if (next == null) {
                curr.setNext(prev);
                head = curr;
            }
        }
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);

        SinglyLinkedList<Integer> ll2 = new SinglyLinkedList<Integer>();
        ll2.addFirst(3);
        ll2.addLast(7);
        ll2.addLast(10);
        System.out.println(ll2);

        System.out.println("Merged: " + ll.sortedMerge(ll2));
        ll.reverse();
        System.out.println("Reversed: " + ll);
    }
}
