package com.fengquanwei.hello.algorithm.tree;

import java.util.Comparator;
import java.util.Random;

/**
 * 二叉查找树
 * 左子树中所有节点的值小于根节点的值，右子树中所有节点的值大于根节点的值。
 *
 * @author fengquanwei
 * @create 2018/2/15 22:13
 **/
public class BinarySearchTree<E> {
    private BinaryTreeNode rootBinaryTreeNode;
    private Comparator<? super E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.rootBinaryTreeNode = null;
        this.comparator = comparator;
    }

    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        } else {
            return ((Comparable) element1).compareTo(element2);
        }
    }

    public void makeEmpty() {
        rootBinaryTreeNode = null;
    }

    public boolean isEmpty() {
        return rootBinaryTreeNode == null;
    }

    public boolean contains(E element) {
        return contains(element, rootBinaryTreeNode);
    }

    private boolean contains(E element, BinaryTreeNode<E> binaryTreeNode) {
        if (binaryTreeNode == null) {
            return false;
        }

        int compare = compare(element, binaryTreeNode.element);

        if (compare < 0) {
            return contains(element, binaryTreeNode.leftChild);
        } else if (compare > 0) {
            return contains(element, binaryTreeNode.rightChild);
        } else {
            return true;
        }
    }

    public E findMin() {
        BinaryTreeNode<E> minBinaryTreeNode = findMin(rootBinaryTreeNode);

        if (minBinaryTreeNode == null) {
            return null;
        } else {
            return minBinaryTreeNode.element;
        }
    }

    private BinaryTreeNode<E> findMin(BinaryTreeNode<E> binaryTreeNode) {
        if (binaryTreeNode != null) {
            while (binaryTreeNode.leftChild != null) {
                binaryTreeNode = binaryTreeNode.leftChild;
            }
        }

        return binaryTreeNode;
    }

    public E findMax() {
        BinaryTreeNode<E> maxBinaryTreeNode = findMax(rootBinaryTreeNode);

        if (maxBinaryTreeNode == null) {
            return null;
        } else {
            return maxBinaryTreeNode.element;
        }
    }

    private BinaryTreeNode<E> findMax(BinaryTreeNode<E> binaryTreeNode) {
        if (binaryTreeNode != null) {
            while (binaryTreeNode.rightChild != null) {
                binaryTreeNode = binaryTreeNode.rightChild;
            }
        }

        return binaryTreeNode;
    }

    public void insert(E element) {
        rootBinaryTreeNode = insert(element, rootBinaryTreeNode);
    }

    private BinaryTreeNode<E> insert(E element, BinaryTreeNode<E> binaryTreeNode) {
        if (binaryTreeNode == null) {
            return new BinaryTreeNode<E>(element, null, null);
        }

        int compare = compare(element, binaryTreeNode.element);

        if (compare < 0) {
            binaryTreeNode.leftChild = insert(element, binaryTreeNode.leftChild);
        } else if (compare > 0) {
            binaryTreeNode.rightChild = insert(element, binaryTreeNode.rightChild);
        } else {

        }

        return binaryTreeNode;
    }

    public void remove(E element) {
        rootBinaryTreeNode = remove(element, rootBinaryTreeNode);
    }

    private BinaryTreeNode<E> remove(E element, BinaryTreeNode<E> binaryTreeNode) {
        if (binaryTreeNode == null) {
            return null;
        }

        int compare = compare(element, binaryTreeNode.element);

        if (compare < 0) {
            binaryTreeNode.leftChild = remove(element, binaryTreeNode.leftChild);
        } else if (compare > 0) {
            binaryTreeNode.rightChild = remove(element, binaryTreeNode.rightChild);
        } else if (binaryTreeNode.leftChild != null && binaryTreeNode.rightChild != null) { // 两个孩子
            binaryTreeNode.element = findMin(binaryTreeNode).element;
            binaryTreeNode.rightChild = remove(binaryTreeNode.element, binaryTreeNode.rightChild);
        } else {
            binaryTreeNode = binaryTreeNode.leftChild != null ? binaryTreeNode.leftChild : binaryTreeNode.rightChild;
        }

        return binaryTreeNode;
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            System.out.print("Tree[\t");
            printTree(rootBinaryTreeNode);
            System.out.println("]");
        }
    }

    private void printTree(BinaryTreeNode<E> binaryTreeNode) {
        if (binaryTreeNode != null) {
            printTree(binaryTreeNode.leftChild);
            System.out.print(binaryTreeNode.element + "\t");
            printTree(binaryTreeNode.rightChild);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int nextInt = random.nextInt(10);
            System.out.println("Insert: " + nextInt);
            binarySearchTree.insert(nextInt);
        }

        binarySearchTree.printTree();
        System.out.println("Min: " + binarySearchTree.findMin());
        System.out.println("Max: " + binarySearchTree.findMax());
        System.out.println("Contains 5: " + binarySearchTree.contains(5));

        for (int i = 0; i < 5; i++) {
            int nextInt = random.nextInt(10);
            System.out.println("Remove: " + nextInt);
            binarySearchTree.remove(nextInt);
        }
        binarySearchTree.printTree();
    }
}
