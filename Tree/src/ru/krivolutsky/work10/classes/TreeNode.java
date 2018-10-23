package ru.krivolutsky.work10.classes;

public class TreeNode<T> {
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
}
