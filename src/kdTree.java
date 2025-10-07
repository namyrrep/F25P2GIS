/**
 * 
 */
public class kdTree {
    private BinaryNode<City> root;

    public kdTree() {
        root = null;
    }


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
     * Sets the current root to the given parameter
     * 
     * @param BinaryNode<City>
     */
    public void setRoot(BinaryNode<City> city) {
        root = city;
    }


    /**
     * Insert function to add to the tree
     * 
     * @param city
     * @return boolean if successfully inserted
     */
    public boolean insert(BinaryNode<City> node, City city, int dimension) {
        // If root is null, add city to the root.
        if (root == null) {
            this.setRoot(new BinaryNode<City>(city));
            return true;
        }

        // Use root for the initial insert, which will be null.
        if (node == null) {
            node = root;
        }

        // Check the dimension to see if we are checking the X or Y coordinate.
        if (dimension % 2 == 0) {
            // Go Left if x value is less.
            if (city.getXValue() < node.getData().getXValue()) {
                if (node.getLeft() == null) {
                    node.setLeft(new BinaryNode<City>(city));
                    return true;
                }
                return insert(node.getLeft(), city, dimension + 1);
            }
            // Returns false if equal
            if (node.getData().getXValue() == city.getXValue() && node.getData()
                .getYValue() == city.getYValue()) {
                return false;
            }
            // Go right if x value is greater.
            if (node.getRight() == null) {
                node.setRight(new BinaryNode<City>(city));
                return true;
            }
            return insert(node.getRight(), city, dimension + 1);
        }
        // Go Left if y value is less than.
        if (city.getYValue() < node.getData().getYValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryNode<City>(city));
                return true;
            }
            return insert(node.getLeft(), city, dimension + 1);
        }
        // Returns false if equal
        if (node.getData().getXValue() == city.getXValue() && node.getData()
            .getYValue() == city.getYValue()) {
            return false;
        }
        // Go right if y value is greater.
        if (node.getRight() == null) {
            node.setRight(new BinaryNode<City>(city));
            return true;
        }
        return insert(node.getRight(), city, dimension + 1);
    }


    /**
     * Returns the tree printed out in order format
     * 
     * @return String of the tree
     */
    public String printInOrder(BinaryNode<City> node) {
        String str = "";

        if (node == null) {
            return str;
        }

        // left branch
        str += printInOrder(node.getLeft());

        // current value
        str += node.toString() + "\n"; // current value

        // right branch
        str += printInOrder(node.getRight());

        return str;
    }


    /**
     * Returns the tree printed out in pre-order format
     * 
     * @return String of the tree
     */
    private String printPreOrder(
        BinaryNode<City> node,
        int dimension,
        String spaces) {
        String str = "";

        if (node == null) {
            return str;
        }

        // current value
        str += dimension + spaces + node.getData().toString() + "\n"; // current value

        // left branch
        str += printPreOrder(node.getLeft(), dimension++, spaces + "  ");

        // right branch
        str += printPreOrder(node.getRight(), dimension++, spaces + "  ");

        return str;
    }


    /**
     * Prints the pre-order helper method
     * 
     * @return
     */
    public String debug() {
        return printPreOrder(root, -1, "");
    }


    /**
     * Deletes the first instance of the parameterized coordinates.
     * 
     * @param x
     *            - The X coordinate for possible deletion
     * @param y
     *            - The Y coordinate for possible deletion
     * @return Name of the city deleted
     */
    public String deleteByCo(int x, int y) {

        return "";
    }

}
