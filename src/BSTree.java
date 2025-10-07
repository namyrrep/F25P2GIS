/**
 * 
 */

/**
 * 
 */
public class BSTree<T> {
    private BinaryNode<T> root;
    
    /**
     * 
     * @param key value needed for node
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
    public boolean insert(T input) {
        
        return true;
    }
    
    /**
     * @return String of the tree
     */
    public String toString() {
        
        return "";
    }    
}
