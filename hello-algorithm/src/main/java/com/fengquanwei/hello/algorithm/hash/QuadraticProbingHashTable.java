package com.fengquanwei.hello.algorithm.hash;

/**
 * 平方探测哈希表
 *
 * @author fengquanwei
 * @create 2018/2/25 15:02
 **/
public class QuadraticProbingHashTable<AnyType> {
    private static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType element) {
            this(element, true);
        }

        public HashEntry(AnyType element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }

    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<AnyType>[] array;
    private int currentSize;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myHash(x);

        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }

        return currentPos;
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private int myHash(AnyType e) {
        int hashVal = e.hashCode();

        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
        }

        return hashVal;
    }

    public void insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            return;
        }

        array[currentPos] = new HashEntry<AnyType>(x);
        if (++currentSize > array.length / 2) {
            rehash();
        }
    }

    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
            currentSize--;
        }
    }

    private void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null && oldArray[i].isActive) {
                insert(oldArray[i].element);
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
        QuadraticProbingHashTable<Employee> hashTable = new QuadraticProbingHashTable<>(7);

        for (int i = 0; i < 10; i++) {
            hashTable.insert(new Employee("Name-" + i, i, i));
        }

        System.out.println(hashTable.contains(new Employee("Name-" + 7, 7, 7)));
    }
}
