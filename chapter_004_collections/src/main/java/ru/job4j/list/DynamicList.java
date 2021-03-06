package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class DynamicList<E> implements Iterable<E> {
    @GuardedBy("this.container")
    private int size;
    private Object[] container;
    private int index = 0;
    private int modCount = 0;

    public DynamicList(int size) {
        this.size = size;
        this.container = new Object[size];
    }

    /**
     * Method increases array size if necessary,
     * and adds given value to the array.
     * @param value
     */
    public void add(E value) {
        synchronized (this.container) {
            if (this.size <= this.index + 1) {
                int target = this.size;
                this.size *= 2;
                Object[] temp = new Object[this.size];
                for (int i = 0; i < target; i++) {
                    temp[i] = this.container[i];
                }
                this.container = temp;
            }
            this.container[this.index++] = value;
        }
    }

    public E get(int index) {
        synchronized (this.container) {
            return (E) this.container[index];
        }
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return (Iterator<E>) Arrays.asList(this.container).iterator();
    }

    public int getSize() {
        synchronized (this.container) {
            return this.size;
        }
    }
}
