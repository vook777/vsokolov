package ru.job4j.tree;

import java.util.*;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E>, Iterable<Node<E>> {

    private Node<E> root;

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (!findBy(child).isPresent() && findBy(parent).isPresent()) {
            findBy(parent).get().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    public boolean isBinary() {
        boolean result = true;
        Iterator<Node<E>> itr = this.iterator();
        while (itr.hasNext()) {
            if (itr.next().leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return new Iterator<Node<E>>() {

            Queue<Node<E>> queue = new LinkedList<>();
            List<Node<E>> current = queue.poll().leaves();
            int index = 0;

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public Node<E> next() {
                if (index >= current.size() && hasNext()) {
                    index = 0;
                    current = queue.poll().leaves();
                }
             return current.get(index++);
            }
        };
    }
}
