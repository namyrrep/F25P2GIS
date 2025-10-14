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
        String result = helpSearch(root, x, y, r, 0, count);
        // Adjust counting to exclude the initial root visit if any nodes were visited
        if (count[0] > 0) {
            count[0]--;
        }
        return result + count[0];
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
            if (node.getData().getXValue() >= x - r) {
                result += helpSearch(node.getLeft(), x, y, r, dimension + 1,
                    num);
            } 
            // Go Right if x value is less than distance r from x
            if (node.getData().getXValue() <= x + r) {
                result += helpSearch(node.getRight(), x, y, r, dimension + 1,
                    num);
            }
        }
        else {
            // Go left if y value is greater than distance r from y
            if (node.getData().getYValue() >= y - r) {
                result += helpSearch(node.getLeft(), x, y, r, dimension + 1, num);
            } 
            // Go right if y value is less than distance r from y
            if (node.getData().getYValue() <= y + r) {
                result += helpSearch(node.getRight(), x, y, r, dimension + 1, num);
            }
        }
        return result;
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


    /**
     * Helps find the min node
     * 
     * @param node
     *            City
     * @param descrim
     *            Dimension Comparison
     * @param dimension
     *            Actual Dimension
     * @param nodesVisited
     *            Counter for nodes visited
     * @return BinaryNode<City>
     */
    private BinaryNode<City> findMin(
        BinaryNode<City> node,
        int descrim,
        int dimension,
        int[] nodesVisited) {
        BinaryNode<City> temp1, temp2;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        if (node == null) {
            return null;
        }

        nodesVisited[0]++; // Count this node as visited

        // Gets the minimum descrim value from left branch.
        temp1 = findMin(node.getLeft(), descrim, dimension + 1, nodesVisited);
        if (temp1 != null) {
            x1 = temp1.getData().getXValue();
            y1 = temp1.getData().getYValue();
        }
        // Must check right branch if ordered by opposite dimension
        if (descrim % 2 != dimension % 2) {
            // Gets the minimum descrim value from right branch.
            temp2 = findMin(node.getRight(), descrim, dimension + 1,
                nodesVisited);
            if (temp2 != null) {
                x2 = temp2.getData().getXValue();
                y2 = temp2.getData().getYValue();
            }
            // Condition for one or two child node
            if ((temp1 == null) || (temp2 != null && ((descrim % 2 == 0
                && x1 > x2) || (descrim % 2 == 1 && y1 > y2)))) {
                temp1 = temp2;
                x1 = x2;
                y1 = y2;
            }
        }
        // Compares the smallest found node to the parent node.
        int x3 = node.getData().getXValue();
        int y3 = node.getData().getYValue();
        if ((temp1 == null) || ((descrim % 2 == 0 && x1 > x3) || (descrim
            % 2 == 1 && y1 > y3))) {
            return node;
        }
        return temp1;
    }


    /**
     * Removes a node given the X and Y coordinates
     * 
     * @param node
     *            The root and recursive nodes
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @param dimension
     * @param sb
     *            StringBuilder to store city name
     * @param nodesVisited
     *            Counter for nodes visited
     * @param isOriginalTarget
     *            True if this is the original target node being deleted
     * @return BinaryNode after removal
     */
    private BinaryNode<City> helpRemove(
        BinaryNode<City> node,
        int x,
        int y,
        int dimension,
        StringBuilder sb,
        int[] nodesVisited,
        boolean isOriginalTarget) {
        // If there is nothing to remove.
        if (node == null) {
            return null;
        }

        nodesVisited[0]++;

        // Found node to delete.
        if (node.getData().getXValue() == x && node.getData()
            .getYValue() == y) {
            // Only append city name if this is the original target
            if (isOriginalTarget) {
                sb.append(node.getData().getCityName());
            }
            // If there is a right subtree
            if (node.getRight() != null) {
                BinaryNode<City> minNode = findMin(node.getRight(), dimension,
                    dimension + 1, nodesVisited);
                node.setData(minNode.getData());
                node.setRight(helpRemove(node.getRight(), minNode.getData()
                    .getXValue(), minNode.getData().getYValue(), dimension + 1,
                    sb, nodesVisited, false)); // Not original target
            } // If there is a left subtree, but not a right.
            else if (node.getLeft() != null) {
                BinaryNode<City> minNode = findMin(node.getLeft(), dimension,
                    dimension + 1, nodesVisited);
                node.setData(minNode.getData());
                node.setRight(helpRemove(node.getLeft(), minNode.getData()
                    .getXValue(), minNode.getData().getYValue(), dimension + 1,
                    sb, nodesVisited, false)); // Not original target
                node.setLeft(null);
            } // If there is no tree
            else {
                return null;
            }
            return node;
        }
        // If the node has not been found.
        if ((dimension % 2 == 0 && x < node.getData().getXValue()) || (dimension
            % 2 == 1 && y < node.getData().getYValue())) {
            node.setLeft(helpRemove(node.getLeft(), x, y, dimension + 1, sb,
                nodesVisited, isOriginalTarget));
        }
        else {
            node.setRight(helpRemove(node.getRight(), x, y, dimension + 1, sb,
                nodesVisited, isOriginalTarget));
        }
        return node;
    }


    /**
     * Provides the template for the helpRemove() method.
     * 
     * @param x
     *            Coordinate
     * @param y
     *            Coordinate
     * @return String with node count and city name
     */
    public String delete(int x, int y) {
        StringBuilder cityName = new StringBuilder();
        int[] nodesVisited = { 0 };
        root = helpRemove(root, x, y, 0, cityName, nodesVisited, true);
        if (cityName.length() == 0) {
            return "";
        }
        return nodesVisited[0] + "\n" + cityName.toString();
    }

}