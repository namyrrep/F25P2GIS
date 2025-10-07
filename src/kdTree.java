/**
 * 
 */
public class kdTree {
    private BinaryNode<City> root;

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
    	//If root is null, add city to the root.
    	if (root == null) {
    		this.setRoot(new BinaryNode<City>(city));
    		return true;
    	}
    	
    	//Use root for the initial insert, which will be null.
    	if (node == null) {
    		node = root;
    	}
    	
    	//Check the dimension to see if we are checking the X or Y coordinate.
    	if (dimension % 2 == 0) {
    		//Go Left if x value is less.
    		if (city.getXValue() < node.getData().getXValue()) {
    			if (node.getLeft() == null) {
    				node.setLeft(new BinaryNode<City>(city));
    				return true;
    			}
    			return insert(node.getLeft(), city, dimension + 1);
    		}
    		//Returns false if equal
    		if (node.getData().getXValue() == city.getXValue() &&
    			node.getData().getYValue() == city.getYValue()) {
    			return false;
    		}
    		//Go right if x value is greater.
    		if (node.getRight() == null) {
    			node.setRight(new BinaryNode<City>(city));
    			return true;
    		}
    		return insert(node.getRight(), city, dimension + 1);
    	}
    	//Go Left if y value is less than.
    	if (city.getYValue() < node.getData().getYValue()) {
    		if (node.getLeft() == null) {
    			node.setLeft(new BinaryNode<City>(city));
    			return true;
    		}
    		return insert(node.getLeft(), city, dimension + 1);
    	}
    	//Returns false if equal
		if (node.getData().getXValue() == city.getXValue() &&
			node.getData().getYValue() == city.getYValue()) {
			return false;
		}
    	//Go right if y value is greater.
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
    public String printKDTree(BinaryNode<City> node) {
    	//Use root for the initial insert, which will be null.
    	if (node == null) {
    		node = root;
    	}
    	
    	String str = "";
    	
    	if (node.getLeft() != null) { //left branch
    		str += printKDTree(node.getLeft());
    	}
    	
    	str += node.toString() + "\n"; //current value
    	
    	if (node.getRight() != null) { //right branch
    		str += printKDTree(node.getRight());
    	}

    	return str;
    }
}