package com.fengquanwei.hello.algorithm.tree;

/**
 * 树节点（第一儿子/下一兄弟表示法）
 *
 * @author fengquanwei
 * @create 2018/2/12 21:06
 **/
public class TreeNode<E> {
    E element;
    TreeNode<E> firstChild;
    TreeNode<E> nextSibling;
}
