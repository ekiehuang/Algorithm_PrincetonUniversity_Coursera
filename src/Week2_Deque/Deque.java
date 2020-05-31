/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first, last;

    private class Node {
        Item item;
        Node fore;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.fore = null;
        //oldFirst.item = first.item;
        //first.fore = null;
        first.next = oldFirst;
        if (last == null) {
            last = first;
        }
        else {
            oldFirst.fore = first;
        }

        //last = oldFirst;
        //first = last.fore;
        size++;
        //oldFirst.fore = first;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.fore = oldLast;
        last.next = null;
        if (first == null) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        //first = oldLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item item = first.item;
            first = first.next;
            size--;
            if (isEmpty()) last = null;
            return item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item item = last.item;
            if (size == 1) {
                last = null;
                first = null;
            }
            else {
                last = last.fore;
                last.next = null;
            }
            size--;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        /*StdOut.println("Please enter to add in the begining");
        while (!StdIn.isEmpty()) {
            deque.addFirst(StdIn.readString());
        }
        StdOut.println("Please enter to add in the end");
        while (!StdIn.isEmpty()) {
            deque.addLast(StdIn.readString());
        }*/
        deque.addLast("like");
        deque.addFirst("I");
        deque.addLast("apple");
        deque.addLast("and");
        deque.addFirst("banana");
        StdOut.println("The current size is " + deque.size());
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println("\nRemove: " + deque.removeLast());
        StdOut.println("Remove: " + deque.removeFirst());
        StdOut.println("Remove: " + deque.removeFirst());
        StdOut.println("The current size is " + deque.size());
        for (String s : deque) {
            StdOut.print(s + " ");
        }
    }

}



