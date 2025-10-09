/**
 * 
 */
public class kdTree {
    private BinaryNode<City> root;

    public kdTree() {
        root = null;
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
    private boolean helpInsert(
        BinaryNode<City> node,
        City city,
        int dimension) {
        // If root is null, add city to the root.
        if (root == null) {
            this.setRoot(new BinaryNode<City>(city));
            return true;
        }

        // Check the dimension to see if we are checking the X or Y coordinate.
        if (dimension % 2 == 0) {
            // Go Left if x value is less.
            if (city.getXValue() < node.getData().getXValue()) {
                if (node.getLeft() == null) {
                    node.setLeft(new BinaryNode<City>(city));
                    return true;
                }
                return helpInsert(node.getLeft(), city, dimension + 1);
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
            return helpInsert(node.getRight(), city, dimension + 1);
        }
        // Go Left if y value is less than.
        if (city.getYValue() < node.getData().getYValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryNode<City>(city));
                return true;
            }
            return helpInsert(node.getLeft(), city, dimension + 1);
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
        return helpInsert(node.getRight(), city, dimension + 1);
    }


    /**
     * This inserts using the helpInsert method
     * 
     * @param city
     * @return
     */
    public boolean insert(City city) {
        return helpInsert(root, city, 0);
    }


    /**
     * Returns the City at (x, y) if possible
     * 
     * @param x
     * @param y
     * @return String Name of City
     */
    public String info(int x, int y) {
        return helpInfo(root, x, y, 0);
    }


    /**
     * This is the helper method for info
     * 
     * @param node
     * @param x
     * @param y
     * @param dimension
     * @return String Name of City
     */
    public String helpInfo(BinaryNode<City> node, int x, int y, int dimension) {
        // If the root is null, there are no City's in this tree.
        if (root == null) {
            return "";
        }

        // Returns if the correct coordinates are found.
        if (x == node.getData().getXValue() && y == node.getData()
            .getYValue()) {
            return node.getData().getCityName();
        }

        // Search by x
        if (dimension % 2 == 0) {
            if (x < node.getData().getXValue()) {
                return helpInfo(node.getLeft(), x, y, dimension + 1);
            }
            return helpInfo(node.getRight(), x, y, dimension + 1);
        }
        // Search by y
        if (y < node.getData().getYValue()) {
            return helpInfo(node.getLeft(), x, y, dimension + 1);
        }
        return helpInfo(node.getRight(), x, y, dimension + 1);
    }


    /**
     * Returns a list of City's in the radius r away from the location (x, y).
     * 
     * @param x
     * @param y
     * @param r
     * @return String List of City's and # of nodes visited
     */
    public String search(int x, int y, int r) {
        int count[] = { 0 };
        return helpSearch(root, x, y, r, count) + count[0];
    }


    /**
     * The helper method for the search method.
     * 
     * @param node
     * @param x
     * @param y
     * @param r
     * @param num
     * @return String List of City's and # of nodes visited
     */
    public String helpSearch(
        BinaryNode<City> node,
        int x,
        int y,
        int r,
        int num[]) {
        // If root is null, there are no City's in radius.
        if (root == null) {
            return "";
        }
        // Returns "" if node is null
        if (node == null) {
            return "";
        }
        num[0]++;
        String result = "";
        // Calculate using the function for a circle.
        double distance = (Math.pow(r, 2) - Math.pow((x - node.getData()
            .getXValue()), 2)) - Math.pow((y - node.getData().getYValue()), 2);

        // Do both children
        if (distance >= 0) {
            result += node.getData().toString() + "\n";
            result += helpSearch(node.getLeft(), x, y, r, num);
            result += helpSearch(node.getRight(), x, y, r, num);
        }
        // No children otherwise.
        return result;
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

        // left branch
        str += printPreOrder(node.getLeft(), dimension + 1, spaces + "  ");

        // current value
        str += dimension + spaces + node.getData().toString() + "\n";

        // right branch
        str += printPreOrder(node.getRight(), dimension + 1, spaces + "  ");

        return str;
    }


    /**
     * Prints the pre-order helper method
     * 
     * @return
     */
    public String debug() {
        return printPreOrder(root, 0, "");
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
