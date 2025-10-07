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
     * @param key
     *            value needed for node
     */
    public BSTree(T key) {
        root = new BinaryNode<T>(key);
    }


    /**
     * 
     * @return BinaryNode<T> returns the current root
     */
    public BinaryNode<T> getRoot() {
        return root;
    }


    /**
     * 
     * @param input
     * @return boolean if correctly inserted
     */
    public void insert(T e) {
        root = insertHelp(root, e);
    }


    private BinaryNode<T> insertHelp(BinaryNode<T> root, T e) {
        if (root == null)
            return new BinaryNode<T>(e);
        if (e.compareTo(root.getData()) < 0)
            root.setLeft(insertHelp(root.getLeft(), e));
        else
            root.setRight(insertHelp(root.getRight(), e));
        return root;
    }


    /**
     * @return String of the tree
     */
    public String toString() {

        return helpPrint(root, 0, "");
    }


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
