/**
 * The BSTree is an implementation of the Binary Search Tree using OPENDSA notes
 * 
 * @author William Perryman & Edwin Barrack
 * @version 10/9/2025
 * 
 * @param <T>
 *            this class is generic
 */
public class BSTree<T extends Comparable<T>> {
    private BinaryNode<T> root;

    /**
     * Constructor for BSTree
     */
    public BSTree() {
        root = null;
    }


    /**
     * Inserts a value by passing it through to insert help a recursive
     * function from there it checks which way it should go by comparing
     * 
     * @param e
     *            is the value given
     */
    public void insert(T e) {
        root = insertHelp(root, e);
    }


    private BinaryNode<T> insertHelp(BinaryNode<T> node, T e) {
        if (node == null)
            return new BinaryNode<T>(e);
        if (e.compareTo(node.getData()) <= 0)
            node.setLeft(insertHelp(node.getLeft(), e));
        else
            node.setRight(insertHelp(node.getRight(), e));
        return node;
    }


    /**
     * Puts the tree in a string format, passes it through to a private
     * helpPrint method doing an in order print style
     * 
     * @return String of the tree
     */
    public String toString() {

        return helpPrint(root, 0, "");
    }


    /**
     * Finds the node given T input
     * 
     * @param input
     *            from whatever data type is passed in
     * @return String depending on if a node is found or not
     */
    public String findNode(T input) {

        return findNodeHelper(root, input);
    }


    private String findNodeHelper(BinaryNode<T> base, T target) {
        if (base == null)
            return "";
        String result = "";
        if (base.getData().compareTo(target) == 0) {
            result += base.getData().toString() + "\n";
            result += findNodeHelper(base.getLeft(), target);
        }
        else if (base.getData().compareTo(target) > 0) {
            result += findNodeHelper(base.getLeft(), target);
        }
        else {
            result += findNodeHelper(base.getRight(), target);
        }

        return result;
    }


    /**
     * Finds the the nodes and prints in order
     * 
     * @param rt
     *            the root of the node given
     * @param level
     *            what current level we are on
     * @param spaces
     * @return cur containing all the info.
     */
    private String helpPrint(BinaryNode<T> rt, int level, String spaces) {
        String cur = "";
        if (rt == null)
            return "";
        cur += helpPrint(rt.getLeft(), level + 1, spaces + "  ");
        cur += level + spaces + rt.getData().toString() + "\n";
        cur += helpPrint(rt.getRight(), level + 1, spaces + "  ");

        return cur;
    }


    /**
     * Removes nodes based on the specified comparison type
     *
     * @param target
     *            the item to remove
     * @param useEquals
     *            true to use equals() for exact matching,
     *            false to use compareTo() for natural ordering matching
     * @return String containing what was removed
     */
    public String removeNode(T target, boolean useEquals) {
        StringBuilder deleted = new StringBuilder();
        root = removeHelp(root, target, deleted, useEquals);
        return deleted.toString();
    }


    private BinaryNode<T> removeHelp(
        BinaryNode<T> base,
        T targ,
        StringBuilder result,
        boolean useEquals) {

        if (base == null) {
            return null;
        }

        int comparison = base.getData().compareTo(targ);

        if (useEquals) {
            // --- LOGIC FOR DELETING A SINGLE, EXACT NODE ---

            // We are in the correct region; now find the exact match
            if (base.getData().equals(targ)) {
                // Found the one to delete
                result.append(base.getData().toString()).append("\n");
                if (base.getLeft() == null) {
                    return base.getRight();
                }
// if (base.getRight() == null) {
// return base.getLeft();
// }
                BinaryNode<T> predecessor = getMax(base.getLeft());
                base.setData(predecessor.getData());
                base.setLeft(deleteMax(base.getLeft()));
                return base; // Deletion is done, stop here.
            }
            if (comparison < 0) {
                base.setRight(removeHelp(base.getRight(), targ, result, true));
                return base;
            }

            // If it's not an exact match, it must be another duplicate,
            // so we must continue searching ONLY on the left as per project
            // rules.
            base.setLeft(removeHelp(base.getLeft(), targ, result, true));
            return base;
        }
        else {
            // --- LOGIC FOR DELETING ALL DUPLICATES BY NAME ---
            if (comparison > 0) {
                base.setLeft(removeHelp(base.getLeft(), targ, result, false));
                return base;
            }
            else if (comparison < 0) {
                base.setRight(removeHelp(base.getRight(), targ, result, false));
                return base;
            }
            else {
                // Match Found (comparison == 0)
                result.append(base.getData().toString()).append("\n");

                if (base.getLeft() == null) {
                    return removeHelp(base.getRight(), targ, result, false);
                }
                else if (base.getRight() == null) {
                    return removeHelp(base.getLeft(), targ, result, false);
                }
                else {
                    BinaryNode<T> predecessor = getMax(base.getLeft());
                    base.setData(predecessor.getData());
                    base.setLeft(deleteMax(base.getLeft()));
                    return removeHelp(base, targ, result, false);
                }
            }
        }

    }


    private BinaryNode<T> getMax(BinaryNode<T> input) {
        if (input.getRight() == null) {
            return input;
        }
        return getMax(input.getRight());

    }


    private BinaryNode<T> deleteMax(BinaryNode<T> node) {
        // If the right child is null, this is the max node.
        // Replace it with its left child (which could be null).
        if (node.getRight() == null) {
            return node.getLeft();
        }

        // Continue searching for the max node down the right side.
        node.setRight(deleteMax(node.getRight()));
        return node;
    }
}
