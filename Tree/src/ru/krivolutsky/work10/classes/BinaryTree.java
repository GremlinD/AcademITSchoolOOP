package ru.krivolutsky.work10.classes;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeNode<Integer> node;
    private int elementCount = 0;

    public BinaryTree(){
    }
    public BinaryTree(int data) {
        node = new TreeNode<>();
        node.data = data;
    }

    public void insert(int data){
        if (node == null) {
            node = new TreeNode<>();
            node.data = data;
            elementCount++;
            return;
        }
        TreeNode<Integer> tmp = node;
        while (true) {
            if (data < tmp.data) {
                if (tmp.left != null){
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
        TreeNode<Integer> delete = node;
        TreeNode<Integer> prevDelete = null;
        TreeNode<Integer> min = null;
        TreeNode<Integer> prevMin = null;
        while (true) {
            if (data == delete.data) {
                if (delete.left == null && delete.right == null) {

                }
            } else if (data < delete.data) {
                if (delete.left != null) {
                    delete = delete.left;
                } else {
                    return;
                }
            } else {
                if (delete.right != null) {
                    delete = delete.right;
                } else {
                    return;
                }
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
