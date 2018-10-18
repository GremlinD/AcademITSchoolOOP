package ru.krivolutsky.work10.main;

import ru.krivolutsky.work10.classes.BinaryTree;
import ru.krivolutsky.work10.classes.TreeNode;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree firstTree = new BinaryTree(8);
        System.out.println(firstTree.getCount());
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(5);

        tree.insert(7);
        tree.insert(6);
        tree.insert(9);
        tree.insert(15);
        tree.insert(12);
        tree.insert(11);
        tree.insert(13);
        tree.insert(17);
        tree.insert(16);
        tree.insert(18);
        tree.insert(9);

        TreeNode<Integer> treeNode = tree.searchNode(10);
        System.out.println(treeNode.data);
        System.out.println(tree.getCount());

        tree.deleteByValue(11);

        Consumer<Integer> consumer = System.out::println;
        tree.walkWide(consumer);

        System.out.println();

        tree.circumventDepthWithRecursion(tree.getFirst(), consumer);

        System.out.println();

        tree.bypassInDepth(consumer);
    }
}
