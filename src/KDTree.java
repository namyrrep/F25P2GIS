/**
 * This class represents are kdTree, a binary tree that switches
 * between sorting by x and y coordinates.
 *
 * @author William Perryman
 * @author Edwin Barrack
 * @version 10/9/2025
 */
public class KDTree {
    /**
     *
     * @author William Perryman
     * @author Edwin Barrack
     *
     * @version 10/9/2025
     *
     * @param <T>
     *            this is a generic class
     */
    private class KDNode {
        private City data;
        private KDNode leftChild;
        private KDNode rightChild;

        /**
         * This is the parameterized BinaryNode constructor that takes a generic
         * value T
         *
         * @param value
         *            is the given data input
         */
        public KDNode(City value) {
            this.data = value;
            this.leftChild = null;
            this.rightChild = null;
        }


        /**
         * This is the getter method for data field.
         *
         * @return T Generic Data Type
         */
        public int[] key() {
            int[] key = { this.data.getXValue(), this.data.getYValue() };
            return key;
        }


        /**
         * This is the setter for the data field
         *
         * @param input
         *            what we are setting data to
         */
        public City data() {
            return data;
        }


        /**
         * This is the setter for the data field
         *
         * @param input
         *            what we are setting data to
         */
        public void setData(City input) {
            data = input;
        }


        /**
         * This is the getter method for left field.
         *
         * @return BinaryNode<T> returns the node on the left
         */
        public KDNode left() {
            return leftChild;
        }


        /**
         * This is the setter method for left field.
         *
         * @param newLeft
         *            is the new node being set as the left pointer
         *
         */
        public void setLeft(KDNode newLeft) {
            leftChild = newLeft;
        }


        /**
         * This is the getter method for right field.
         *
         * @return BinaryNode<T> returns the node on the right
         */
        public KDNode right() {
            return rightChild;
        }


        /**
         * This is the setter method for right field.
         *
         * @param newRight
         *            is the new node on the right
         */
        public void setRight(KDNode newRight) {
            rightChild = newRight;
        }
    }

    private KDNode root;
    private static final int D = 2; // number of dimensions (2 for x and y)

    /**
     * The basic constructor that takes no parameters
     */
    public KDTree() {
        root = null;
    }


    /**
     * This method returns the current dimension we are on
     *
     * @param dimension
     *            of tree
     * @return int
     */
    private int currDimension(int dimension) {
        return (dimension + 1) % D;
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
    private boolean inCircle(int[] middle, int r, int[] key) {
        return ((key[0] - middle[0]) * (key[0] - middle[0])) + ((key[1]
            - middle[1]) * (key[1] - middle[1])) <= (r * r);
    }


    /**
     * This is the wrapper method for search
     *
     * @param x
     *            of center
     * @param y
     *            of center
     * @param radius
     *            of circle
     * @return String containing results
     */
    public String search(int x, int y, int radius) {
        int[] visitCount = { 0 };
        int[] position = { x, y };
        StringBuilder rs = new StringBuilder();
        rshelp(root, position, radius, 0, visitCount, rs);
        return rs.toString() + visitCount[0];
    }


    /**
     * This is our search helper method
     *
     * @param rt
     * @param point
     * @param radius
     * @param lev
     * @param count
     * @return String containing results
     */
    private void rshelp(
        KDNode rt,
        int[] point,
        int radius,
        int lev,
        int[] count,
        StringBuilder rs) {
        // Of the node is null, return.
        if (rt == null)
            return;

        // Node has been visited.
        count[0]++;
        int[] rtkey = rt.key();

        // Checks to see if point is in circle.
        if (inCircle(point, radius, rtkey))
            rs.append(rt.data().toString() + "\n");

        // If there is a possible left position.
        if (rtkey[lev] > (point[lev] - radius))
            rshelp(rt.left(), point, radius, currDimension(lev), count, rs);

        if (rtkey[lev] <= (point[lev] + radius)) // Changed < to <=
            rshelp(rt.right(), point, radius, currDimension(lev), count, rs);
    }


    /**
     * Insert function to add to the tree
     *
     * @param city
     *            Used
     * @param node
     * @param dimension
     * @return boolean if successfully inserted
     */
    private boolean helpInsert(KDNode node, City city, int dimension) {
        // If root is null, add city to the root.
        if (node == null) {
            root = new KDNode(city);
            return true;
        }
        int[] cityKey = { city.getXValue(), city.getYValue() };
        int[] nodeKey = node.key();
        // If the current dimension for the city is less than node, go left.
        if (cityKey[dimension] < nodeKey[dimension]) {
            if (node.left() == null) {
                node.setLeft(new KDNode(city));
                return true;
            }
            return helpInsert(node.left(), city, currDimension(dimension));
        }
        // If points are the same, return false.
        if (cityKey[0] == nodeKey[0] && cityKey[1] == nodeKey[1])
            return false;
        // If the current dimension for the city is greater than node, go right.
        if (node.right() == null) {
            node.setRight(new KDNode(city));
            return true;
        }
        return helpInsert(node.right(), city, currDimension(dimension));
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
    private String helpInfo(KDNode node, int[] key, int dimension) {
        // If the node is null, there is no city at the given position.
        if (node == null) {
            return "";
        }
        int[] nodeKey = node.key();
        // If the current dimension for the city is less than node, go left.
        if (key[dimension] < nodeKey[dimension])
            return helpInfo(node.left(), key, currDimension(dimension));
        // Returns if the correct coordinates are found.
        if (key[0] == nodeKey[0] && key[1] == nodeKey[1])
            return node.data().getCityName();
        // If the current dimension for the city is greater than node, go right.
        return helpInfo(node.right(), key, currDimension(dimension));
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
        int[] point = { x, y };
        return helpInfo(root, point, 0);
    }


    /**
     * Returns the tree printed out in inorder format
     *
     * @param node
     *            of city
     * @param dimension
     *            of tree
     * @param spaces
     *            added
     * @return String of the tree
     */
    private String printInOrder(KDNode node, int dimension, String spaces) {
        // If node is null, there are no more nodes in branch.
        if (node == null)
            return "";
        String str = "";
        // Create indentation string with 2 * dimension spaces
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < 2 * dimension; i++) {
            indent.append(" ");
        }
        // left branch (inorder: left first)
        str += printInOrder(node.left(), dimension + 1, "");
        // current value (inorder: root second) - level + spaces + city data
        str += dimension + indent.toString() + node.data().toString() + "\n";
        // right branch (inorder: right last)
        str += printInOrder(node.right(), dimension + 1, "");
        return str;
    }


    /**
     * Prints the inorder helper method
     *
     * @return String
     */
    public String debug() {
        return printInOrder(root, 0, "");
    }


    /**
     * 334
     * This is the helper method to find the minimum node in a subtree
     *
     * 
     * 336
     * 
     * @param rt
     *            337
     *            of city
     *            338
     * @param descrim
     *            339
     *            of tree
     *            340
     * @param level
     *            341
     *            of tree
     *            342
     * @return KDNode
     *         343
     */
    /**
     * This is the helper method to find the minimum node in a subtree
     *
     * @param rt
     *            of city
     * @param descrim
     *            of tree
     * @param level
     *            of tree
     * @return KDNode
     */
    private KDNode findMin(KDNode rt, int descrim, int level, int[] count) {
        count[0]++;

        // Assume the root of this subtree is the minimum.
        // This makes it win all initial ties against its children.
        KDNode minNode = rt;

        // Check the left subtree.
        if (rt.left() != null) {
            KDNode minInLeft = findMin(rt.left(), descrim, currDimension(level),
                count);

            // If the left's min is STRICTLY smaller, it becomes the new
            // minimum.
            // The root wins ties because we don't use <=.
            if (minInLeft.key()[descrim] < minNode.key()[descrim]) {
                minNode = minInLeft;
            }
        }

        // Only search the right if the dimension we're checking is NOT the
        // current level's discriminating dimension.
        if (level != descrim) {
            if (rt.right() != null) {
                KDNode minInRight = findMin(rt.right(), descrim, currDimension(
                    level), count);

                // If the right's min is STRICTLY smaller, it's the new minimum.
                // The current minNode (either root or from left) wins ties.
                if (minInRight.key()[descrim] < minNode.key()[descrim]) {
                    minNode = minInRight;
                }
            }
        }

        return minNode;
    }


    /**
     * This is the helper method for remove
     *
     * @param base
     *            of city
     * @param targ
     *            City to be removed
     * @param dimension
     *            of tree
     * @param result
     *            StringBuilder to hold results
     * @return KDNode
     */
    private KDNode removeHelp(
        KDNode base,
        int[] cityKey,
        int dimension,
        int[] count,
        StringBuilder result) {

        if (base == null) {
            return null; // City not found.
        }
        count[0]++;
        int[] baseKey = base.key();
        int nextDim = currDimension(dimension);

        // Step 1: Find the node to delete.
        if (baseKey[0] == cityKey[0] && baseKey[1] == cityKey[1]) {
            result.append(base.data().getCityName()).append("\n");

            // Node found. Now handle the deletion cases.
            if (base.right() != null) {
                // CASE 1: Node has a right subtree. (Standard case)
                // Find the minimum node in the right subtree (the successor).
                KDNode successor = findMin(base.right(), dimension, nextDim,
                    count);
                // Replace the current node's data with the successor's.
                base.setData(successor.data());
                // Recursively delete the successor from the right subtree.
                base.setRight(removeHelp(base.right(), successor.key(), nextDim,
                    count, new StringBuilder()));
            }
            else if (base.left() != null) {
                // CASE 2: Node has only a left subtree. (Professor's specific
                // logic)
                // Find the minimum node in the left subtree.
                KDNode successor = findMin(base.left(), dimension, nextDim,
                    count);
                // Replace the current node's data with the successor's.
                base.setData(successor.data());
                // Recursively delete the successor from the left subtree.
                KDNode modifiedLeftSubtree = removeHelp(base.left(), successor
                    .key(), nextDim, count, new StringBuilder());
                // Move the modified left subtree to be the new right subtree.
                base.setRight(modifiedLeftSubtree);
                base.setLeft(null); // The left subtree is now empty.
            }
            else {
                // CASE 3: Node is a leaf.
                return null; // Simply remove it by returning null to the
                             // parent.
            }
        }
        // Step 2: If node not found, continue searching.
        else if (cityKey[dimension] < baseKey[dimension]) {
            base.setLeft(removeHelp(base.left(), cityKey, nextDim, count,
                result));
        }
        else { // This also handles the case of equal keys on the current
               // dimension but different on others
            base.setRight(removeHelp(base.right(), cityKey, nextDim, count,
                result));
        }
        return base;
    }


    /**
     * The city with these coordinates is deleted from the tree
     *
     * @param x
     *            City x-coordinate.
     * @param y
     *            City y-coordinate.
     * @return String A string that is empty if there is no such city,
     */
    public String delete(int x, int y) {
        StringBuilder result = new StringBuilder();
        int[] cityKey = { x, y };
        int[] visitCount = { 0 };
        root = removeHelp(root, cityKey, 0, visitCount, result);

        if (result.length() > 0) {
            // Change the format to match the test case
            return visitCount[0] + "\n" + result.toString();
        }
        return ""; // Or handle "not found" case as needed
    }

}
