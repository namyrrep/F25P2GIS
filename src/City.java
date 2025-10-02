/**
 * This is the City class that stores the cityRecords that we are using to sort in trees. 
 */
public class City {
	private String cityName;
	private int xValue;
	private int yValue;

	/**
	 * This is parameterized constructor for the City class.
	 */
	public City(String name, int x, int y) {
		this.cityName = name;
		this.xValue = x;
		this.yValue = y;
	}

	/**
	 * This is the getter method for cityName field.
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * This is the setter method for cityName field.
	 */
	public void setCityName(String newName) {
		cityName = newName;
	}

	/**
	 * This is the getter method for xValue field.
	 */
	public int getXValue() {
		return xValue;
	}

	/**
	 * This is the setter method for xValue field.
	 */
	public void setXValue(int newX) {
		xValue = newX;
	}

	/**
	 * This is the getter method for yValue field.
	 */
	public int getYValue() {
		return yValue;
	}

	/**
	 * This is the setter method for yValue field.
	 */
	public void setYValue(int newY) {
		yValue = newY;
	}

	/**
	 * This is the toString method for the City class.
	 * @Override
	 */
	public String toString() {

	}

	/**
	 * This is the equals method for the City class.
	 * @Override
	 */
	public boolean equals(Object otherCity) {
		//If the reference is the same
		if (this == otherCity) {
			return true;
		}
		//If the parameter is null or not the City class
		if (otherCity == null || otherCity.getClass() != this.getClass()) {
			return false;
		}
		//Gets a City object from the parameterized Object. 
		City city = (City) otherCity;
		
		return this.cityName.equals(city.cityName) && this.xValue == city.xValue && this.yValue == city.yValue;
	}
}