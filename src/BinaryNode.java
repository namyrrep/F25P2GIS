/**
 * 
 */

/**
 * 
 */
public class BinaryNode<T>{
	private T data;
	private BinaryNode left;
	private BinaryNode right;
	
	/**
	 * This is the parameterized BinaryNode constructor that takes a generic value T
	 * @param value
	 */
	public BinaryNode(T value) {
		this.data = value;
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
	public BinaryNode getLeft() {
		return left;
	}
	
	/**
	 * This is the setter method for left field.
	 */
	public void setLeft(BinaryNode newLeft) {
		left = newLeft;
	}
	
	/**
	 * This is the getter method for right field.
	 */
	public BinaryNode getRight() {
		return right;
	}
	
	/**
	 * This is the setter method for right field.
	 */
	public void setRight(BinaryNode newRight) {
		right = newRight;
	}
}