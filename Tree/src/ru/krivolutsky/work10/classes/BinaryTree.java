package ru.krivolutsky.work10.classes;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private int elementCount = 0;
    private Comparator<T> comparator;

    public BinaryTree() {
    }

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            elementCount++;
            return;
        }
        TreeNode<T> tmp = root;

        while (true) {
            if (comparator.compare(data, tmp.getData()) < 0) {
                (Comparable <? super T >) data.compareTo(tmp.getData());
                if (tmp.getLeft() != null) {
                    tmp = tmp.getLeft();
                } else {
                    tmp.setLeft(new TreeNode<>(data));
                    elementCount++;
                    return;
                }
            } else {
                if (tmp.getRight() != null) {
                    tmp = tmp.getRight();
                } else {
                    tmp.setRight(new TreeNode<>(data));
                    elementCount++;
                    return;
                }
            }
        }
    }

    public TreeNode<T> searchNode(T data) {
        TreeNode<T> tmp = root;
        while (true) {
            if (data.compareTo(tmp.getData()) == 0) {
                return tmp;
            } else if (data.compareTo(tmp.getData()) < 0) {
                if (tmp.getLeft() != null) {
                    tmp = tmp.getLeft();
                } else {
                    return null;
                }
            } else {
                if (tmp.getRight() != null) {
                    tmp = tmp.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public int getCount() {
        return elementCount;
    }

    public boolean deleteByValue(T data) {
        TreeNode<T> delete = root;
        TreeNode<T> prevDelete = null;
        while (true) {
            if (data == delete.getData()) {
                if (delete.getLeft() == null && delete.getRight() == null) {
                    if (prevDelete != null) {
                        if (prevDelete.getRight().equals(delete)) {
                            prevDelete.setRight(null);
                        } else {
                            prevDelete.setLeft(null);
                        }
                    } else {
                        root = null;
                    }
                    elementCount--;
                    return true;
                } else if (delete.getLeft() == null) {
                    if (prevDelete != null) {
                        if (prevDelete.getRight().equals(delete)) {
                            prevDelete.setRight(delete.getRight());
                        } else {
                            prevDelete.setLeft(delete.getRight());
                        }
                    } else {
                        root = root.getRight();
                    }
                    return true;
                } else if (delete.getRight() == null) {
                    if (prevDelete != null) {
                        if (prevDelete.getRight().equals(delete)) {
                            prevDelete.setRight(delete.getLeft());
                        } else {
                            prevDelete.setLeft(delete.getLeft());
                        }
                    } else {
                        root = root.getLeft();
                    }
                    elementCount--;
                    return true;
                } else {
                    TreeNode<T> min = delete.getRight();
                    TreeNode<T> prevMin = null;
                    while (min.getLeft() != null) {
                        prevMin = min;
                        min = min.getLeft();
                    }
                    TreeNode<T> node = min;
                    if (min.getRight() == null) {
                        if (prevMin != null) {
                            if (prevMin.getLeft().equals(min)) {
                                prevMin.setLeft(null);
                            } else {
                                prevMin.setRight(null);
                            }
                        } else if (prevDelete != null) {
                            if (prevDelete.getData().compareTo(delete.getData()) < 0) {
                                prevDelete.setRight(null);
                            } else {
                                prevDelete.setLeft(null);
                            }
                        } else {
                            min.setLeft(delete.getLeft());
                            this.root = min;
                        }
                    } else {
                        if (prevMin != null) {
                            if (prevMin.getLeft().equals(min)) {
                                prevMin.setLeft(min.getRight());
                            } else {
                                prevMin.setRight(min.getRight());
                            }
                        } else {
                            if (prevDelete != null) {
                                prevDelete.setRight(delete.getRight());
                            } else {
                                min.setLeft(delete.getLeft());
                                this.root = min;
                            }
                        }
                    }
                    if (prevDelete != null) {
                        if (prevDelete.getData().compareTo(delete.getData()) <= 0) {
                            if (!Objects.equals(min, delete.getRight())) {
                                min.setRight(delete.getRight());
                            }
                            min.setLeft(delete.getLeft());
                            prevDelete.setRight(node);
                        } else {
                            if (!min.equals(delete.getRight())) {
                                min.setRight(delete.getRight());
                            }
                            min.setLeft(delete.getLeft());
                            prevDelete.setLeft(node);
                        }
                    } else {
                        min.setLeft(this.root.getLeft());
                        min.setRight(this.root.getRight());
                        this.root = min;
                    }
                }
                elementCount--;
                return true;
            } else if (data.compareTo(delete.getData()) < 0) {
                if (delete.getLeft() != null) {
                    prevDelete = delete;
                    delete = delete.getLeft();
                } else {
                    return false;
                }
            } else {
                if (delete.getRight() != null) {
                    prevDelete = delete;
                    delete = delete.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public void walkWide(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (queue.peek() != null) {
            TreeNode<T> treeNode = queue.element();
            consumer.accept(queue.remove().getData());
            if (treeNode.getLeft() != null) {
                queue.add(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                queue.add(treeNode.getRight());
            }
        }
    }

    public void circumventDepthWithRecursion(Consumer<T> consumer) {
        circumventDepthWithRecursion(this.root, consumer);
    }

    private void circumventDepthWithRecursion(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());
        if (node.getLeft() != null) {
            circumventDepthWithRecursion(node.getLeft(), consumer);
        }
        if (node.getRight() != null) {
            circumventDepthWithRecursion(node.getRight(), consumer);
        }
    }

    public void bypassInDepth(Consumer<T> consumer) {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.add(root);
        while (stack.peekLast() != null) {
            TreeNode<T> treeNode = stack.getLast();
            consumer.accept(stack.removeLast().getData());
            if (treeNode.getRight() != null) {
                stack.addLast(treeNode.getRight());
            }
            if (treeNode.getLeft() != null) {
                stack.addLast(treeNode.getLeft());
            }
        }
    }
}
