package ru.krivolutsky.work10.classes;

public class TreeNode<T extends Comparable<? super T>> implements Comparable<TreeNode<T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    public void setData(T data) {
        this.data = data;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(TreeNode<T> o) {
        return data.compareTo(o.data);
    }
}
