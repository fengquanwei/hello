package com.fengquanwei.hello.algorithm.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 数组实现
 * 未实现修改检测
 *
 * @author fengquanwei
 * @create 2018/2/10 15:03
 **/
public class ArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private E[] elements;

    public ArrayList() {
        clear();
    }

    public void clear() {
        this.size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void trimToSize() {
        ensureCapacity(size);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return elements[index];
    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= size) {
            return;
        }

        E[] oldElements = elements;
        elements = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            elements[i] = oldElements[i];
        }
    }

    public boolean add(E e) {
        add(size, e);
        return true;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (elements.length == size) {
            ensureCapacity(size * 2 + 1);
        }

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;

        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        E element = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;

        return element;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[current++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        arrayList.add(2, 99);
        arrayList.remove(4);

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
