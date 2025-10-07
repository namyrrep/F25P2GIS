/**
 * 
 */
public class kdTree {
    private BinaryNode<City> root;

    /**
     * Initializes a new tree
     * 
     * @param currentCity
     */
    public kdTree(City currentCity) {
        root = new BinaryNode<City>(currentCity);
    }


    /**
     * Gets the current root
     * 
     * @return BinaryNode<City> returns the node
     */
    public BinaryNode<City> getRoot() {
        return root;
    }


    /**
     * Insert function to add to the tree
     * 
     * @param city
     * @return boolean if successfully inserted
     */
    public boolean insert(City city) {

        return true;
    }


    /**
     * Returns the tree printed out
     * 
     * @return String of the tree
     */
    public String toString() {

        return "";
    }

}
