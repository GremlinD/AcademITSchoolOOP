package ru.krivolutsky.work10.classes;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private int count = 0;
    private Comparator<T> comparator = null;

    public BinaryTree() {
    }

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T t1, T t2) {
        if (this.comparator != null) {
            return this.comparator.compare(t1, t2);
        } else {
            if (Objects.equals(t1, t2)) {
                return 0;
            }
            if (t1 == null || t2 == null) {
                return (t1 == null) ? 1 : -1;
            }
            int tmp = ((Comparable<T>) t1).compareTo(t2);
            if (tmp > 0) {
                return 1;
            }
            if (tmp < 0) {
                return -1;
            }
        }
        throw new UnsupportedOperationException("Ошибка при стандартном сравнении, используйте свой компаратор.");
    }

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            count++;
            return;
        }
        TreeNode<T> tmp = root;

        while (true) {
            if (compare(data, tmp.getData()) < 0) {
                if (tmp.getLeft() != null) {
                    tmp = tmp.getLeft();
                } else {
                    tmp.setLeft(new TreeNode<>(data));
                    count++;
                    return;
                }
            } else {
                if (tmp.getRight() != null) {
                    tmp = tmp.getRight();
                } else {
                    tmp.setRight(new TreeNode<>(data));
                    count++;
                    return;
                }
            }
        }
    }

    public TreeNode<T> searchNode(T data) {
        TreeNode<T> tmp = root;
        while (true) {
            int compareNumber = compare(data, tmp.getData());
            if (compareNumber == 0) {
                return tmp;
            } else if (compareNumber < 0) {
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
        return count;
    }

    public boolean deleteByValue(T data) {
        TreeNode<T> delete = root;
        TreeNode<T> prevDelete = null;
        while (data != delete.getData()) {
            if (compare(data, delete.getData()) < 0) {
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

        if (delete.getLeft() == null && delete.getRight() == null) {
            if (prevDelete != null) {
                if (prevDelete.getRight() == delete) {
                    prevDelete.setRight(null);
                } else {
                    prevDelete.setLeft(null);
                }
            } else {
                root = null;
            }
            count--;
            return true;
        } else if (delete.getLeft() == null) {
            if (prevDelete != null) {
                if (prevDelete.getRight() == delete) {
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
                if (prevDelete.getRight() == delete) {
                    prevDelete.setRight(delete.getLeft());
                } else {
                    prevDelete.setLeft(delete.getLeft());
                }
            } else {
                root = root.getLeft();
            }
            count--;
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
                    if (prevMin.getLeft() == min) {
                        prevMin.setLeft(null);
                    } else {
                        prevMin.setRight(null);
                    }
                } else if (prevDelete != null) {
                    if (compare(prevDelete.getData(), delete.getData()) < 0) {
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
                    if (prevMin.getLeft() == min) {
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
                if (compare(prevDelete.getData(), delete.getData()) <= 0) {
                    if (min != delete.getRight()) {
                        min.setRight(delete.getRight());
                    }
                    min.setLeft(delete.getLeft());
                    prevDelete.setRight(node);
                } else {
                    if (min != delete.getRight()) {
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
        count--;
        return true;
    }

    public void walkWide(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (queue.isEmpty()) {
            TreeNode<T> treeNode = queue.remove();
            consumer.accept(treeNode.getData());
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
        while (stack.isEmpty()) {
            TreeNode<T> treeNode = stack.removeLast();
            consumer.accept(treeNode.getData());
            if (treeNode.getRight() != null) {
                stack.addLast(treeNode.getRight());
            }
            if (treeNode.getLeft() != null) {
                stack.addLast(treeNode.getLeft());
            }
        }
    }
}
