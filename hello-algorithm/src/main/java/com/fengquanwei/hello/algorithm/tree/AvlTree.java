package com.fengquanwei.hello.algorithm.tree;

import java.util.Comparator;
import java.util.Random;

/**
 * AVL 树（一种平衡二叉树实现）
 * 每个节点的左子树和右子树的高度最多差 1 的二叉查找树
 *
 * @author fengquanwei
 * @create 2018/2/15 22:13
 **/
public class AvlTree<E> {
    private AvlTreeNode rootAvlTreeNode;
    private Comparator<? super E> comparator;

    public AvlTree() {
        this(null);
    }

    public AvlTree(Comparator<? super E> comparator) {
        this.rootAvlTreeNode = null;
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
        rootAvlTreeNode = null;
    }

    public boolean isEmpty() {
        return rootAvlTreeNode == null;
    }

    public boolean contains(E element) {
        return contains(element, rootAvlTreeNode);
    }

    private boolean contains(E element, AvlTreeNode<E> avlTreeNode) {
        if (avlTreeNode == null) {
            return false;
        }

        int compare = compare(element, avlTreeNode.element);

        if (compare < 0) {
            return contains(element, avlTreeNode.leftChild);
        } else if (compare > 0) {
            return contains(element, avlTreeNode.rightChild);
        } else {
            return true;
        }
    }

    public E findMin() {
        AvlTreeNode<E> minAvlTreeNode = findMin(rootAvlTreeNode);

        if (minAvlTreeNode == null) {
            return null;
        } else {
            return minAvlTreeNode.element;
        }
    }

    private AvlTreeNode<E> findMin(AvlTreeNode<E> avlTreeNode) {
        if (avlTreeNode != null) {
            while (avlTreeNode.leftChild != null) {
                avlTreeNode = avlTreeNode.leftChild;
            }
        }

        return avlTreeNode;
    }

    public E findMax() {
        AvlTreeNode<E> maxAvlTreeNode = findMax(rootAvlTreeNode);

        if (maxAvlTreeNode == null) {
            return null;
        } else {
            return maxAvlTreeNode.element;
        }
    }

    private AvlTreeNode<E> findMax(AvlTreeNode<E> avlTreeNode) {
        if (avlTreeNode != null) {
            while (avlTreeNode.rightChild != null) {
                avlTreeNode = avlTreeNode.rightChild;
            }
        }

        return avlTreeNode;
    }

    public void insert(E element) {
        rootAvlTreeNode = insert(element, rootAvlTreeNode);
    }

    private AvlTreeNode<E> insert(E element, AvlTreeNode<E> avlTreeNode) {
        if (avlTreeNode == null) {
            return new AvlTreeNode<E>(element, null, null);
        }

        int compare = compare(element, avlTreeNode.element);

        if (compare < 0) {
            avlTreeNode.leftChild = insert(element, avlTreeNode.leftChild);
        } else if (compare > 0) {
            avlTreeNode.rightChild = insert(element, avlTreeNode.rightChild);
        } else {

        }

        return balance(avlTreeNode);
    }

    public void remove(E element) {
        rootAvlTreeNode = remove(element, rootAvlTreeNode);
    }

    private AvlTreeNode<E> remove(E element, AvlTreeNode<E> avlTreeNode) {
        if (avlTreeNode == null) {
            return null;
        }

        int compare = compare(element, avlTreeNode.element);

        if (compare < 0) {
            avlTreeNode.leftChild = remove(element, avlTreeNode.leftChild);
        } else if (compare > 0) {
            avlTreeNode.rightChild = remove(element, avlTreeNode.rightChild);
        } else if (avlTreeNode.leftChild != null && avlTreeNode.rightChild != null) { // 两个孩子
            avlTreeNode.element = findMin(avlTreeNode).element;
            avlTreeNode.rightChild = remove(avlTreeNode.element, avlTreeNode.rightChild);
        } else {
            avlTreeNode = avlTreeNode.leftChild != null ? avlTreeNode.leftChild : avlTreeNode.rightChild;
        }

        return balance(avlTreeNode);
    }

    private int height(AvlTreeNode<E> avlTreeNode) {
        return avlTreeNode == null ? -1 : avlTreeNode.height;
    }

    private AvlTreeNode<E> balance(AvlTreeNode<E> avlTreeNode) {
        if (avlTreeNode == null) {
            return null;
        }

        if (height(avlTreeNode.leftChild) - height(avlTreeNode.rightChild) > 1) {
            if (height(avlTreeNode.leftChild.leftChild) >= height(avlTreeNode.leftChild.rightChild)) {
                avlTreeNode = singleRotateWithLeftChild(avlTreeNode);
            } else {
                avlTreeNode = doubleRotateWithLeftChild(avlTreeNode);
            }
        } else if (height(avlTreeNode.rightChild) - height(avlTreeNode.leftChild) > 1) {
            if (height(avlTreeNode.rightChild.rightChild) >= height(avlTreeNode.rightChild.leftChild)) {
                avlTreeNode = singleRotateWithRightChild(avlTreeNode);
            } else {
                avlTreeNode = doubleRotateWithRightChild(avlTreeNode);
            }
        }

        avlTreeNode.height = Math.max(height(avlTreeNode.leftChild), height(avlTreeNode.rightChild)) + 1;
        return avlTreeNode;
    }

    // 左左单旋转
    private AvlTreeNode<E> singleRotateWithLeftChild(AvlTreeNode<E> k2) {
        AvlTreeNode<E> k1 = k2.leftChild;
        k2.leftChild = k1.rightChild;
        k1.rightChild = k2;

        k2.height = Math.max(height(k2.leftChild), height(k2.rightChild)) + 1;
        k1.height = Math.max(height(k1.leftChild), k2.height) + 1;

        return k1;
    }

    // 右右单旋转
    private AvlTreeNode<E> singleRotateWithRightChild(AvlTreeNode<E> k1) {
        AvlTreeNode<E> k2 = k1.rightChild;
        k1.rightChild = k2.leftChild;
        k2.leftChild = k1;

        k1.height = Math.max(height(k1.leftChild), height(k1.rightChild)) + 1;
        k2.height = Math.max(k1.height, height(k2.rightChild)) + 1;

        return k2;
    }

    // 左右双旋转
    private AvlTreeNode<E> doubleRotateWithLeftChild(AvlTreeNode<E> k3) {
        k3.leftChild = singleRotateWithRightChild(k3.leftChild);
        return singleRotateWithLeftChild(k3);
    }

    // 右左双旋转
    private AvlTreeNode<E> doubleRotateWithRightChild(AvlTreeNode<E> k3) {
        k3.rightChild = singleRotateWithLeftChild(k3.rightChild);
        return singleRotateWithRightChild(k3);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            System.out.print("Tree[\t");
            printTree(rootAvlTreeNode);
            System.out.println("]");
        }
    }

    private void printTree(AvlTreeNode<E> AvlTreeNode) {
        if (AvlTreeNode != null) {
            printTree(AvlTreeNode.leftChild);
            System.out.print(AvlTreeNode.element + "\t");
            printTree(AvlTreeNode.rightChild);
        }
    }

    public static void main(String[] args) {
        AvlTree binarySearchTree = new AvlTree();

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
