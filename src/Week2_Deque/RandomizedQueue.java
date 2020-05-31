/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQue;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randomQue = (Item[]) new Object[2];
        N = 0;
        //p = (int) (Math.random() * N);
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = randomQue[i];
        }
        randomQue = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == randomQue.length) resize(2 * randomQue.length);
        randomQue[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements to delete");
        }
        else {
            int p = StdRandom.uniform(N);
            Item item = randomQue[p];
            if (p < N - 1) {
                randomQue[p] = randomQue[N - 1];
                randomQue[N - 1] = null;
            }
            else {
                randomQue[p] = null;
            }
            N--;
            if (N > 0 && N == randomQue.length / 4) resize(randomQue.length / 2);
            return item;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int sam = StdRandom.uniform(N);
        return randomQue[sam];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i = 0;

        public RandomIterator() {
            for (int i = 0; i < N; i++) {
                int r = StdRandom.uniform(N);
                Item temp = randomQue[i];
                randomQue[i] = randomQue[r];
                randomQue[r] = temp;
            }
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (i >= N) throw new NoSuchElementException();
            return randomQue[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        StdOut.println("The current size is " + randomizedQueue.size());
        for (String s : randomizedQueue) {
            StdOut.println(s);
        }
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        StdOut.println("The current size is " + randomizedQueue.size());
        for (String s : randomizedQueue) {
            StdOut.println(s);
        }
    }

}