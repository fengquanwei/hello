package com.fengquanwei.hello.algorithm.tree;

/**
 * AVL 树节点
 *
 * @author fengquanwei
 * @create 2018/2/16 17:24
 **/
public class AvlTreeNode<E> {
    E element;
    AvlTreeNode<E> leftChild;
    AvlTreeNode<E> rightChild;
    int height;

    public AvlTreeNode(E element) {
        this(element, null, null);
    }

    public AvlTreeNode(E element, AvlTreeNode<E> leftChild, AvlTreeNode<E> rightChild) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 0;
    }
}
