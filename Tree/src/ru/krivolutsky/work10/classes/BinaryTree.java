package ru.krivolutsky.work10.classes;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree {
    private TreeNode<Integer> node;
    private int elementCount = 0;

    public BinaryTree() {
    }

    public BinaryTree(int data) {
        node = new TreeNode<>();
        node.data = data;
    }

    public TreeNode<Integer> getFirst() {
        return this.node;
    }

    public void insert(int data) {
        if (node == null) {
            node = new TreeNode<>();
            node.data = data;
            elementCount++;
            return;
        }
        TreeNode<Integer> tmp = node;
        while (true) {
            if (data < tmp.data) {
                if (tmp.left != null) {
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

    public TreeNode<Integer> searchNode(int data) {
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

    public void deleteByValue(int data) {
        TreeNode<Integer> delete = node;
        TreeNode<Integer> prevDelete = null;
        while (true) {
            if (data == delete.data) {
                if (delete.left == null && delete.right == null) {
                    if (prevDelete != null) {
                        if (prevDelete.right.equals(delete)) {
                            prevDelete.right = null;
                        } else {
                            prevDelete.left = null;
                        }
                    } else {
                        node = null;
                    }
                    elementCount--;
                    return;
                } else if (delete.left == null) {
                    if (prevDelete != null) {
                        if (prevDelete.right.equals(delete)) {
                            prevDelete.right = delete.right;
                        } else {
                            prevDelete.left = delete.right;
                        }
                    } else {
                        node = node.right;
                    }
                    return;
                } else if (delete.right == null) {
                    if (prevDelete != null) {
                        if (prevDelete.right.equals(delete)) {
                            prevDelete.right = delete.left;
                        } else {
                            prevDelete.left = delete.left;
                        }
                    } else {
                        node = node.left;
                    }
                    elementCount--;
                    return;
                } else {
                    TreeNode<Integer> min = delete.right;
                    TreeNode<Integer> prevMin = null;
                    while (min.left != null) {
                        prevMin = min;
                        min = min.left;
                    }
                    TreeNode<Integer> node = min;
                    if (min.right == null) {
                        if (prevMin != null) {
                            if (prevMin.left.equals(min)) {
                                prevMin.left = null;
                            } else {
                                prevMin.right = null;
                            }
                        } else if (prevDelete != null) {
                            if (prevDelete.data < delete.data) {
                                prevDelete.right = null;
                            } else {
                                prevDelete.left = null;
                            }
                        } else {
                            min.left = delete.left;
                            this.node = min;
                        }
                    } else {
                        if (prevMin != null) {
                            if (prevMin.left.equals(min)) {
                                prevMin.left = min.right;
                            } else {
                                prevMin.right = min.right;
                            }
                        } else {
                            if (prevDelete != null) {
                                prevDelete.right = delete.right;
                            } else {
                                min.left = delete.left;
                                this.node = min;
                            }
                        }
                    }
                    if (prevDelete != null) {
                        if (prevDelete.data <= delete.data) {
                            if (!Objects.equals(min, delete.right)) {
                                min.right = delete.right;
                            }
                            min.left = delete.left;
                            prevDelete.right = node;
                        } else {
                            if (!min.equals(delete.right)) {
                                min.right = delete.right;
                            }
                            min.left = delete.left;
                            prevDelete.left = node;
                        }
                    } else {
                        min.left = this.node.left;
                        min.right = this.node.right;
                        this.node = min;
                    }
                }
                elementCount--;
                return;
            } else if (data < delete.data) {
                if (delete.left != null) {
                    prevDelete = delete;
                    delete = delete.left;
                } else {
                    return;
                }
            } else {
                if (delete.right != null) {
                    prevDelete = delete;
                    delete = delete.right;
                } else {
                    return;
                }
            }
        }
    }

    public void walkWide(Consumer<Integer> consumer) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(node);
        while (queue.peek() != null) {
            TreeNode<Integer> treeNode = queue.element();
            consumer.accept(queue.element().data);
            queue.remove();
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
    }

    public void circumventDepthWithRecursion(TreeNode<Integer> node, Consumer<Integer> consumer) {
        consumer.accept(node.data);
        if (node.left != null) {
            circumventDepthWithRecursion(node.left, consumer);
        }
        if (node.right != null) {
            circumventDepthWithRecursion(node.right, consumer);
        }
    }

    public void bypassInDepth(Consumer<Integer> consumer) {
        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        stack.add(node);
        while (stack.peekLast() != null) {
            TreeNode<Integer> treeNode = stack.getLast();
            consumer.accept(stack.getLast().data);
            stack.removeLast();
            if (treeNode.right != null) {
                stack.addLast(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.addLast(treeNode.left);
            }
        }
    }
}
