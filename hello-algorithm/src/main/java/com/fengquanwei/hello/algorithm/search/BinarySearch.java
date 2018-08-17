package com.fengquanwei.hello.algorithm.search;

/**
 * 二分查找
 *
 * @author fengquanwei
 * @create 2018/2/10 12:39
 **/
public class BinarySearch {
    /**
     * O(logN)
     */
    public static <T extends Comparable<? super T>> int binarySearch(T[] a, T t) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (a[mid].compareTo(t) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(t) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(binarySearch(a, 7));
    }
}
