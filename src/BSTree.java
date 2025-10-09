/**
 * 
 */

/**
 * 
 */
public class BSTree<T extends Comparable<T>> {
    private BinaryNode<T> root;

    public BSTree() {
        root = null;
    }


    /**
     * 
     * @param input
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
     * Finds the the nodes and prints.
     * 
     * @param rt
     * @param level
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
