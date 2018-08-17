package com.fengquanwei.hello.algorithm.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接哈希表
 *
 * @author fengquanwei
 * @create 2018/2/25 14:06
 **/
public class SeparateChainingHashTable<E> {
    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<E>[] elementListArray;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        elementListArray = new LinkedList[nextPrime(size)];
        for (int i = 0; i < elementListArray.length; i++) {
            elementListArray[i] = new LinkedList<>();
        }
    }

    public void makeEmpty() {
        for (int i = 0; i < elementListArray.length; i++) {
            elementListArray[i].clear();
        }
        currentSize = 0;
    }

    public boolean contains(E e) {
        List<E> whichList = elementListArray[myHash(e)];
        return whichList.contains(e);
    }

    public void insert(E e) {
        List<E> whichList = elementListArray[myHash(e)];

        if (!whichList.contains(e)) {
            whichList.add(e);

            if (++currentSize > elementListArray.length) {
                rehash();
            }
        }
    }

    public void remove(E e) {
        List<E> whichList = elementListArray[myHash(e)];
        if (whichList.contains(e)) {
            whichList.remove(e);
            currentSize--;
        }
    }

    private int myHash(E e) {
        int hashVal = e.hashCode();

        hashVal %= elementListArray.length;
        if (hashVal < 0) {
            hashVal += elementListArray.length;
        }

        return hashVal;
    }

    private void rehash() {
        List<E>[] oldLists = elementListArray;

        elementListArray = new List[nextPrime(2 * elementListArray.length)];
        for (int i = 0; i < elementListArray.length; i++) {
            elementListArray[i] = new LinkedList<>();
        }

        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (E item : oldLists[i]) {
                insert(item);
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        }

        if (num == 1 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int nextPrime(int num) {
        if (num == 0 || num == 1 || num == 2) {
            return 2;
        }

        if (num % 2 == 0) {
            num++;
        }

        while (!isPrime(num)) {
            num += 2;
        }

        return num;
    }

    public static void main(String[] args) {
        SeparateChainingHashTable<Employee> hashTable = new SeparateChainingHashTable<>(7);

        for (int i = 0; i < 10; i++) {
            hashTable.insert(new Employee("Name-" + i, i, i));
        }

        System.out.println(hashTable.contains(new Employee("Name-" + 7, 7, 7)));
    }
}
