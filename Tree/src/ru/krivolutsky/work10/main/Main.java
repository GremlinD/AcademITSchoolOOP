package ru.krivolutsky.work10.main;

import ru.krivolutsky.work10.classes.BinaryTree;
import ru.krivolutsky.work10.classes.TreeNode;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(9);
        tree.insert(4);
        tree.insert(8);
        tree.insert(11);
        tree.insert(13);
        tree.insert(8);
        tree.insert(-11);

        TreeNode<Integer> treeNode = tree.searchNode(8);
        treeNode = tree.searchNode(10);
        System.out.println(tree.getCount());

        tree.deleteByValue(4);
        System.out.print("ew");
    }
}
