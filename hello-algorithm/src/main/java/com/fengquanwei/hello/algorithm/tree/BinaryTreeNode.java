package com.fengquanwei.hello.algorithm.tree;

/**
 * 二叉树节点
 * 每个节点都不能有多于两个的儿子
 *
 * @author fengquanwei
 * @create 2018/2/12 21:07
 **/
public class BinaryTreeNode<E> {
    E element;
    BinaryTreeNode<E> leftChild;
    BinaryTreeNode<E> rightChild;

    public BinaryTreeNode(E element) {
        this(element, null, null);
    }

    public BinaryTreeNode(E element, BinaryTreeNode<E> leftChild, BinaryTreeNode<E> rightChild) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
