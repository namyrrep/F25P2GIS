/**
 * 
 * @author William Perryman & Edwin Barrack
 * 
 * @version 10/9/2025
 */
public class BinaryNode<T> {
    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    /**
     * This is the parameterized BinaryNode constructor that takes a generic
     * value T
     * 
     * @param value
     *            is the given data input
     */
    public BinaryNode(T value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }


    /**
     * This is the getter method for data field.
     * 
     * @return T Generic Data Type
     */
    public T getData() {
        return data;
    }


    /**
     * This is the getter method for left field.
     * 
     * @return BinaryNode<T> returns the node on the left
     */
    public BinaryNode<T> getLeft() {
        return left;
    }


    /**
     * This is the setter method for left field.
     * 
     * @param newLeft
     *            is the new node being set as the left pointer
     * 
     */
    public void setLeft(BinaryNode<T> newLeft) {
        left = newLeft;
    }


    /**
     * This is the getter method for right field.
     * 
     * @return BinaryNode<T> returns the node on the right
     */
    public BinaryNode<T> getRight() {
        return right;
    }


    /**
     * This is the setter method for right field.
     * 
     * @param newRight is the new node on the right
     */
    public void setRight(BinaryNode<T> newRight) {
        right = newRight;
    }
}
