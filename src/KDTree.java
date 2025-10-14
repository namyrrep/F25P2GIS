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

    /**
     * The basic constructor that takes no parameters
     */
    public KDTree() {
        root = null;
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
     * @param y
     * @param radius
     * @return
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
     * @return
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
            rshelp(rt.left(), point, radius, (lev + 1) % 2, count, rs);

        if (rtkey[lev] <= (point[lev] + radius)) // Changed < to <=
            rshelp(rt.right(), point, radius, (lev + 1) % 2, count, rs);
    }


    /**
     * Insert function to add to the tree
     *
     * @param city
     *            Used
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
            return helpInsert(node.left(), city, (dimension + 1) % 2);
        }
        // If points are the same, return false.
        if (cityKey[0] == nodeKey[0] && cityKey[1] == nodeKey[1])
            return false;
        // If the current dimension for the city is greater than node, go right.
        if (node.right() == null) {
            node.setRight(new KDNode(city));
            return true;
        }
        return helpInsert(node.right(), city, (dimension + 1) % 2);
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
            return helpInfo(node.left(), key, (dimension + 1) % 2);
        // Returns if the correct coordinates are found.
        if (key[0] == nodeKey[0] && key[1] == nodeKey[1])
            return node.data().getCityName();
        // If the current dimension for the city is greater than node, go right.
        return helpInfo(node.right(), key, (dimension + 1) % 2);
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
    private String printPreOrder(KDNode node, int dimension, String spaces) {
        // If node is null, there are no more nodes in branch.
        if (node == null)
            return "";
        String str = "";
        // left branch
        str += printPreOrder(node.left(), dimension + 1, spaces + "  ");
        // current value
        str += dimension + spaces + node.data().toString() + "\n";
        // right branch
        str += printPreOrder(node.right(), dimension + 1, spaces + "  ");
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
        if (rt == null) {
            return null;
        }

        // Count the current node
        count[0]++;

        // The rest of your findMin logic remains the same...
        KDNode temp1, temp2;
        int[] key1 = null;
        int[] key2 = null;

        // IMPORTANT: Pass the count array in the recursive calls
        temp1 = findMin(rt.left(), descrim, (level + 1) % 2, count);

        if (temp1 != null)
            key1 = temp1.key();
        if (descrim != level) {
            // Pass the count array here too
            temp2 = findMin(rt.right(), descrim, (level + 1) % 2, count);
            if (temp2 != null)
                key2 = temp2.key();
            if ((temp1 == null) || ((temp2 != null)
                && (key1[descrim] > key2[descrim]))) {
                temp1 = temp2;
                key1 = key2;
            }
        }
        int[] rtkey = rt.key();
        if ((temp1 == null) || (key1[descrim] > rtkey[descrim]))
            return rt;
        else
            return temp1;
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
            return null; // City not found
        }
        count[0]++;
        int[] baseKey = base.key();

        // Step 1: Find the node to delete.
        if (baseKey[0] == cityKey[0] && baseKey[1] == cityKey[1]) {
            // Node found. Add its data to the result string.
            result.append(base.data().getCityName());

            // Case 1: Node has no left child. Replace with the right child.
            if (base.left() == null) {
                return base.right();
            }
            // Case 2: Node has no right child. Replace with the left child.
            if (base.right() == null) {
                return base.left();
            }

            // Case 3: Node has two children.
            KDNode successor = findMin(base.right(), dimension, (dimension + 1)
                % 2, count);
            base.setData(successor.data());
            // Recursively delete the successor from its original location.
            base.setRight(removeHelp(base.right(), successor.key(), (dimension
                + 1) % 2, count, new StringBuilder()));
        }
        else if (cityKey[dimension] < baseKey[dimension]) {
            base.setLeft(removeHelp(base.left(), cityKey, (dimension + 1) % 2,
                count, result));
        }
        else {
            base.setRight(removeHelp(base.right(), cityKey, (dimension + 1) % 2,
                count, result));
        }
        return base;
    }


    // You will also need to update your public delete method to pass the key
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
