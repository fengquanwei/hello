package com.fengquanwei.hello.algorithm.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 链表实现
 *
 * @author fengquanwei
 * @create 2018/2/10 15:50
 **/
public class LinkedList<E> implements Iterable<E> {
    /**
     * 节点
     */
    private static class Node<E> {
        public E element;
        public Node<E> previousNode;
        public Node<E> nextNode;

        public Node(E element, Node<E> previousNode, Node<E> nextNode) {
            this.element = element;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }
    }

    private int size;
    private int modification = 0;
    private Node<E> beginNode;
    private Node<E> endNode;

    public LinkedList() {
        clear();
    }

    public void clear() {
        beginNode = new Node<E>(null, null, null);
        endNode = new Node<E>(null, beginNode, null);
        beginNode.nextNode = endNode;

        size = 0;
        modification++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(E element) {
        add(size, element);
        return true;
    }

    public void add(int index, E element) {
        addBefore(getNode(index, 0, size), element);
    }

    // 在节点前添加元素
    private void addBefore(Node<E> node, E element) {
        Node<E> newNode = new Node<>(element, node.previousNode, node);
        newNode.previousNode.nextNode = newNode;
        node.previousNode = newNode;
        size++;
        modification++;
    }

    public E remove(int index) {
        return remove(getNode(index));
    }

    private E remove(Node<E> node) {
        node.nextNode.previousNode = node.previousNode;
        node.previousNode.nextNode = node.nextNode;
        size--;
        modification++;

        return node.element;
    }

    public E get(int index) {
        return getNode(index).element;
    }

    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E data = node.element;
        node.element = element;
        return data;
    }

    private Node<E> getNode(int index) {
        return getNode(index, 0, size - 1);
    }

    private Node<E> getNode(int index, int lowerIndex, int upperIndex) {
        if (index < lowerIndex || index > upperIndex) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node;

        if (index < size / 2) {
            node = beginNode.nextNode;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
        } else {
            node = endNode;
            for (int i = size; i > index; i--) {
                node = node.previousNode;
            }
        }

        return node;
    }

    /**
     * 迭代器
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> currentNode = beginNode.nextNode;
        private int expectedModification = modification;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return currentNode != endNode;
        }

        @Override
        public E next() {
            if (modification != expectedModification) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E e = currentNode.element;
            currentNode = currentNode.nextNode;
            okToRemove = true;
            return e;
        }

        @Override
        public void remove() {
            if (modification != expectedModification) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            LinkedList.this.remove(currentNode.previousNode);
            expectedModification++;
            okToRemove = false;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        linkedList.add(2, 99);
        linkedList.remove(1);

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
