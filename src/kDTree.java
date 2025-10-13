/**
 * This class represents are kdTree, a binary tree that switches
 * between sorting by x and y coordinates.
 * 
 * @author William Perryman
 * @author Edwin Barrack
 * @version 10/9/2025
 */
public class KDTree {
    private BinaryNode<City> root;

    /**
     * The basic constructor that takes no parameters
     */
    public KDTree() {
        root = null;
    }


    /**
     * Sets the current root to the given parameter
     * 
     * @param BinaryNode<City>
     *            city Used
     */
    public void setRoot(BinaryNode<City> city) {
        root = city;
    }


    /**
     * Insert function to add to the tree
     * 
     * @param city
     *            Used
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
     *            Used
     * @return boolean
     */
    public boolean insert(City city) {
        return helpInsert(root, city, 0);
    }


    /**
     * Returns the City at (x, y) if possible
     * 
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @return String Name of City
     */
    public String info(int x, int y) {
        return helpInfo(root, x, y, 0);
    }


    /**
     * This is the helper method for info
     * 
     * @param node
     *            City
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @param dimension
     *            of tree
     * @return String Name of City
     */
    public String helpInfo(BinaryNode<City> node, int x, int y, int dimension) {
        // If the root is null, there are no City's in this tree.
        if (node == null) {
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
     * This method returns true if point is within circle, false otherwise
     * 
     * @param node
     *            City
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @param r
     *            Coordinate
     * @return boolean
     */
    private boolean inCircle(BinaryNode<City> node, int x, int y, int r) {
        int xDiff = x - node.getData().getXValue();
        int yDiff = y - node.getData().getYValue();

        return (xDiff * xDiff) + (yDiff * yDiff) <= (r * r);
    }


    /**
     * The helper method for the search method.
     * 
     * @param node
     *            of city
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @param r
     *            radius
     * @param dimension
     * @param num
     *            cities
     * @return String List of City's and # of nodes visited
     */
    private String helpSearch(
        BinaryNode<City> node,
        int x,
        int y,
        int r,
        int dimension,
        int[] num) {
        // Returns "" if node is null
        if (node == null) {
            return "";
        }
        num[0]++; // Node visited
        String result = "";
        // Add current value if it is in the circle.
        if (inCircle(node, x, y, r)) {
            result += node.getData().toString() + "\n";
        }

        // Check the dimension to see if we are checking the X or Y coordinate.
        if (dimension % 2 == 0) {
            // Go Left if x value is greater than distance r from x
            if (node.getData().getXValue() > x - r) {
                result += helpSearch(node.getLeft(), x, y, r, dimension + 1,
                    num);
            } // Go Right if x value is less than distance r from x
            if (node.getData().getXValue() < x + r) {
                result += helpSearch(node.getRight(), x, y, r, dimension + 1,
                    num);
            }
            return result;
        }
        // Go left if y value is greater than distance r from x
        if (node.getData().getYValue() > y - r) {
            result += helpSearch(node.getLeft(), x, y, r, dimension + 1, num);
        } // Go right if y value is less than distance r from x
        if (node.getData().getYValue() < y + r) {
            result += helpSearch(node.getRight(), x, y, r, dimension + 1, num);
        }
        return result;

    }


    /**
     * Returns a list of City's in the radius r away from the location (x, y).
     * 
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @param r
     *            radius
     * @return String List of City's and # of nodes visited
     */
    public String search(int x, int y, int r) {
        int[] count = { 0 };
        return helpSearch(root, x, y, r, 0, count) + count[0];
    }


    /**
     * Returns the tree printed out in pre-order format
     * 
     * @param node
     *            of city
     * @param dimension
     *            of tree
     * @param spaces
     *            added
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
     * @return String
     */
    public String debug() {
        return printPreOrder(root, 0, "");
    }

}
