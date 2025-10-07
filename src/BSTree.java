/**
 * 
 */

/**
 * 
 */
public class BSTree<T extends Comparable<T>> {
    private BinaryNode<T> root;

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
        if (root.getData().compareTo(e) >= 0)
            root.setLeft(insertHelp(root.getLeft(), e));
        else
            root.setRight(insertHelp(root.getRight(), e));
        return root;
    }


    /**
     * @return String of the tree
     */
    public String toString() {

        return "";
    }
}
