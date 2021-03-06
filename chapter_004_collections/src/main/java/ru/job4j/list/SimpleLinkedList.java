package ru.job4j.list;

import java.util.HashSet;
import java.util.Iterator;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SimpleLinkedList<E> implements Iterable {

    @GuardedBy("this")
    private int size = 0;
    private Node<E> first;

    /**
     * Method checks whether the list has a loop
     * using Floyd algorithm.
     */
    public boolean hasLoop() {
        boolean result = false;
        Node<E> turtouse = this.first;
        Node<E> hare = this.first;

        while (hare != null && hare.next != null) {
            turtouse = turtouse.next;
            hare = hare.next.next;
            if (turtouse == hare) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Method adds an entry to the beginning of list.
     *
     * @param value
     */
    public void add(E value) {
        synchronized (this) {
            Node<E> newLink = new Node<>(value);
            newLink.next = this.first;
            this.first = newLink;
            this.size++;

        }
    }

    /**
     * Method deletes entry in the list by index.
     *
     * @param index
     */
    public void deleteByIndex(int index) {
        synchronized (this) {
            if (index == 0) {
                Node<E> temp = this.first;
                this.first = temp.next;
            } else {
                Node<E> temp = this.getNode(index).next;
                this.getNode(index - 1).next = temp;
            }
            this.size--;
        }
    }

    /**
     * Method deletes entry in the list by value.
     *
     * @param e
     */
    public void deleteByValue(E e) {
        synchronized (this) {
            for (int i = 0; i < size; i++) {
                if (e.equals(getNode(i).getValue())) {
                    deleteByIndex(i);
                    break;
                }
            }
        }
    }

    /**
     * Method returns value of an element from list by index.
     *
     * @param index
     */
    public E get(int index) {
        synchronized (this) {
            Node<E> result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.value;
        }
    }

    /**
     * Method returns element from list by index.
     *
     * @param index
     */
    public Node<E> getNode(int index) {
        synchronized (this) {
            Node<E> result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result;
        }
    }

    /**
     * Method checks whether the list contains a given element.
     *
     * @param e
     */
    public boolean contains(E e) {
        synchronized (this) {
            boolean result = false;
            for (int i = 0; i < size; i++) {
                if (e.equals(getNode(i).getValue())) {
                    result = true;
                    break;
                }
            }
            return result;
        }
    }

    /**
     * Method checks whether the list contains a given element.
     *
     * @param index
     */
    public boolean hasNext(int index) {
        return this.getNode(index).next != null;
    }

    /**
     * Method returns size of list.
     */
    public int getSize() {
        synchronized (this) {
            return this.size;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator<E>() {

            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E value = (E) current.value;
                    current = current.next;
                    return value;
                }
                return null;
            }
        };
    }

    /**
     * Class for storing data.
     */
    public static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }

        E getValue() {
            return this.value;
        }
    }
}
