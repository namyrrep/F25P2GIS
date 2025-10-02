// -------------------------------------------------------------------------
/**
 * Implementation of the GIS interface. This is what calls the BST and the
 * Bintree to do the work.
 *
 * @author William Perryman & Edwin Barrack
 * @version MileStone 1
 *
 */
public class GISDB implements GIS {

    /**
     * The maximum allowable value for a coordinate
     */
    public static final int MAXCOORD = 32767;

    /**
     * Dimension of the points stored in the tree
     */
    public static final int DIMENSION = 2;
    
    /**
     * This is the KD tree 
     */
    public kdTree kdtree;
    
    /**
     * This is the Binary search tree
     */
    public BSTree bstree;

    private bTree;
    private kTree;

    // ----------------------------------------------------------
    /**
     * Create a new GISDB object.
     */
    GISDB() {
        btree = new BSTree<String>();
        kTree = new kdTree();
    }


    // ----------------------------------------------------------
    /**
     * Reinitialize the database
     * 
     * @return True if the database has been cleared
     */
    public boolean clear() {
        Btree = new BSTree<String>();
        kTree = new kdTree();
        return true;
    }


    // ----------------------------------------------------------
    /**
     * A city at coordinate (x, y) with name name is entered into the database.
     * It is an error to insert two cities with identical coordinates,
     * but not an error to insert two cities with identical names.
     * 
     * @param name
     *            City name.
     * @param x
     *            City x-coordinate. Integer in the range 0 to 2^{15} − 1.
     * @param y
     *            City y-coordinate. Integer in the range 0 to 2^{15} − 1.
     * @return True iff the city is successfully entered into the database
     */
    public boolean insert(String name, int x, int y) {
        if (x >= 0 && x < MAXCOORD && y >= 0 && y < MAXCOORD) {
            City newCity = new City(name, x, y);
            if (kTree.insert(newCity)) {
                bTree.insert(name, newCity);
                return true;
            }
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * The city with these coordinates is deleted from the database
     * (if it exists).
     * Print the name of the city if it exists.
     * If no such city at this location exist, print that.
     * 
     * @param x
     *            City x-coordinate.
     * @param y
     *            City y-coordinate.
     * @return A string that is empty if there is no such city, otherwise
     *         a string with the number of KD-tree nodes visited during the
     *         deletion process, followed by the name of the city.
     */
    public String delete(int x, int y) {
        return "";
    }


    // ----------------------------------------------------------
    /**
     * The city with this name is deleted from the database (if it exists).
     * If two or more cities have this name, then ALL such cities must be
     * removed.
     * Print the coordinates of each city that is deleted.
     * If no such city at this location exists, print that.
     * 
     * @param name
     *            City name.
     * @return A string with the coordinates of each city that is deleted
     *         (listed in preorder as they are deleted).
     */
    public String delete(String name) {
        return "";
    }


    // ----------------------------------------------------------
    /**
     * Display the name of the city at coordinate (x, y) if it exists.
     * 
     * @param x
     *            X coordinate.
     * @param y
     *            Y coordinate.
     * @return The city name if there is such a city, null otherwise
     */
    public String info(int x, int y) {
        return "";
    }


    // ----------------------------------------------------------
    /**
     * Display the coordinates of all cities with this name, if any exist.
     * 
     * @param name
     *            The city name.
     * @return String representing the cities found
     */
    public String info(String name) {
        return "";
    }


    // ----------------------------------------------------------
    /**
     * All cities within radius distance from location (x, y) are listed.
     * A city that is exactly radius distance from the query point should be
     * listed.
     * This operation should be implemented so that as few nodes as possible in
     * the k-d tree are visited.
     * 
     * @param x
     *            Search circle center: X coordinate. May be negative.
     * @param y
     *            Search circle center: X coordinate. May be negative.
     * @param radius
     *            Search radius, must be non-negative.
     * @return String listing the cities found (if any) , followed by the count
     *         of the number of k-d tree nodes looked at during the
     *         search process. If the radius is bad, return an empty string.
     *         If k-d tree is empty, the number of nodes visited is zero.
     */
    public String search(int x, int y, int radius) {
        return "";
    }


    // ----------------------------------------------------------
    /**
     * Print a listing of the database as a preorder traversal of the k-d tree.
     * Each city should be printed on a separate line. Each line should start
     * with the level of the current node, then be indented by 2 * level spaces
     * for a node at a given level, counting the root as level 0.
     * 
     * @return String listing the cities as specified.
     */
    public String debug() {
        return "";
    }


    // ----------------------------------------------------------
    /**
     * Print a listing of the BST in alphabetical order on the names.
     * Each city should be printed on a separate line. Each line should start
     * with the level of the current node, then be indented by 2 * level spaces
     * for a node at a given level, counting the root as level 0.
     * 
     * @return String listing the cities as specified.
     */
    public String print() {
        return "";
    }
}
