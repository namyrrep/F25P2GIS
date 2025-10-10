/**
 * The BSTree is an implementation of the Binary Search Tree using OPENDSA notes
 * 
 * @author William Perryman & Edwin Barrack
 * @version 10/9/2025
 * 
 * @param <T>
 *            this class is generic
 */
public class BSTree<T extends Comparable<T>> {
    private BinaryNode<T> root;

    /**
     * Constructor for BSTree
     */
    public BSTree() {
        root = null;
    }


    /**
     * Inserts a value by passing it through to insert help a recursive
     * function from there it checks which way it should go by comparing
     * 
     * @param input
     *            is the value given
     * @return boolean if correctly inserted
     */
    public void insert(T e) {
        root = insertHelp(root, e);
    }


    private BinaryNode<T> insertHelp(BinaryNode<T> node, T e) {
        if (node == null)
            return new BinaryNode<T>(e);
        if (e.compareTo(node.getData()) <= 0)
            node.setLeft(insertHelp(node.getLeft(), e));
        else
            node.setRight(insertHelp(node.getRight(), e));
        return node;
    }


    /**
     * Puts the tree in a string format, passes it through to a private
     * helpPrint method doing an in order print style
     * 
     * @return String of the tree
     */
    public String toString() {

        return helpPrint(root, 0, "");
    }


    /**
     * Finds the node given T input
     * 
     * @param T
     *            from whatever data type is passed in
     * @return String depending on if a node is found or not
     */
    public String findNode(T input) {

        return findNodeHelper(root, input);
    }


    private String findNodeHelper(BinaryNode<T> base, T target) {
        if (base == null)
            return "";
        String result = "";
        if (base.getData().compareTo(target) == 0) {
            result += base.getData().toString() + "\n";
            result += findNodeHelper(base.getLeft(), target);
        }
        else if (base.getData().compareTo(target) > 0) {
            result += findNodeHelper(base.getLeft(), target);
        }
        else {
            result += findNodeHelper(base.getRight(), target);
        }

        return result;
    }


    /**
     * Finds the the nodes and prints in order
     * 
     * @param rt
     *            the root of the node given
     * @param level
     *            what current level we are on
     * @param spaces
     * @return cur containing all the info.
     */
    private String helpPrint(BinaryNode<T> rt, int level, String spaces) {
        String cur = "";
        if (rt == null)
            return "";
        cur += helpPrint(rt.getLeft(), level + 1, spaces + "  ");
        cur += level + spaces + rt.getData().toString() + "\n";
        cur += helpPrint(rt.getRight(), level + 1, spaces + "  ");

        return cur;
    }
}
