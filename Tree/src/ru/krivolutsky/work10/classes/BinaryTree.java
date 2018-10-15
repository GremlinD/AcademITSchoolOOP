package ru.krivolutsky.work10.classes;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeNode<Integer> node = new TreeNode<>();
    private int elementCount = 0;

    public BinaryTree(int data) {
        node.data = data;
    }

    public void insertNode(int data){
        TreeNode<Integer> tmp = node;
        while (true) {
            if (data < tmp.data) {
                if (node.left != null){
                    tmp = tmp.left;
                } else {
                    tmp.left = new TreeNode<>();
                    tmp.left.data = data;
                    elementCount++;
                    return;
                }
            } else {
                if (tmp.right != null) {
                    tmp = tmp.right;
                } else {
                    tmp.right = new TreeNode<>();
                    tmp.right.data = data;
                    elementCount++;
                    return;
                }
            }
        }
    }

    public TreeNode<Integer> searchNode(int data){
        TreeNode<Integer> tmp = node;
        while (true) {
            if (data == tmp.data) {
                return tmp;
            } else if (data < tmp.data) {
                if (tmp.left != null) {
                    tmp = tmp.left;
                } else {
                    return null;
                }
            } else {
                if (tmp.right != null) {
                    tmp = tmp.right;
                } else {
                    return null;
                }
            }
        }
    }

    public int getCount() {
        return elementCount;
    }

    public void deleteByValue(int data){
        TreeNode<Integer> tmp = node;
        TreeNode<Integer> prevDelete = node;
        while (true) {
            if (tmp.data == data) {
                if (tmp.equals(node)) {
                    TreeNode<Integer> min = tmp.right;
                    TreeNode<Integer> prev = tmp;
                    while (min.left != null) {
                        prev = min;
                        min = min.left;
                    }

                }
                if (tmp.right != null && tmp.left != null) {
                    TreeNode<Integer> min = tmp;
                    TreeNode<Integer> prev = tmp;
                    while (min.left != null) {
                        prev = min;
                        min = min.left;
                    }
                    if (min.right != null) {
                        prev.left = min.right;
                    } else {
                        prev.left = null;
                    }
                    if (prevDelete.right.equals(tmp)) {
                        prevDelete.right = min;
                    } else {
                        prevDelete.left = min;
                    }
                } else if (tmp.right == null && tmp.left == null) {
                    assert prevDelete.right != null;
                    if (prevDelete.right.equals(tmp)) {
                        prevDelete.right = null;
                    } else {
                        prevDelete.left = null;
                    }
                } else if (tmp.right != null) {
                    if (prevDelete.right.equals(tmp)) {
                        prevDelete.right = tmp.right;
                    } else {
                        prevDelete.left= tmp.right;
                    }
                } else {
                    if (prevDelete.left.equals(tmp)) {
                        prevDelete.left = tmp.left;
                    } else {
                        prevDelete.right = tmp.left;
                    }
                }
                break;
            } else if (data < tmp.data) {
                prevDelete = tmp;
                tmp = tmp.left;
            } else {
                prevDelete = tmp;
                tmp = tmp.right;
            }
        }
    }

    public void walkWide(){
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(node);
        while (queue.peek() != null) {
            TreeNode<Integer> treeNode = queue.element();
            System.out.println(queue.element().data);
            queue.remove();
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
    }

    public void circumventDepthWithRecursion(TreeNode<Integer> node) {
        System.out.println(node.data);
        if (node.left != null) {
            circumventDepthWithRecursion(node.left);
        }
        if (node.right != null) {
            circumventDepthWithRecursion(node.right);
        }
    }

    public void bypassInDepth() {
        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        stack.add(node);
        while (stack.peekLast() != null) {
            TreeNode<Integer> treeNode = stack.getLast();
            System.out.println(stack.getLast());
            stack.removeLast();
            stack.addLast(treeNode.right);
            stack.addLast(treeNode.left);
        }
    }
}
