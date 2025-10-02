/**
 * 
 */

/**
 * 
 */
public class BinaryNode<T>{
	private T data;
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	
	/**
	 * This is the parameterized BinaryNode constructor that takes a generic value T
	 * @param value
	 */
	public BinaryNode(T value) {
		this.data = value;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * This is the getter method for data field.
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * This is the setter method for data field.
	 */
	public void setData(T newData) {
		data = newData;
	}
	
	/**
	 * This is the getter method for left field.
	 */
	public BinaryNode<T> getLeft() {
		return left;
	}
	
	/**
	 * This is the setter method for left field.
	 */
	public void setLeft(BinaryNode<T> newLeft) {
		left = newLeft;
	}
	
	/**
	 * This is the getter method for right field.
	 */
	public BinaryNode<T> getRight() {
		return right;
	}
	
	/**
	 * This is the setter method for right field.
	 */
	public void setRight(BinaryNode<T> newRight) {
		right = newRight;
	}
}