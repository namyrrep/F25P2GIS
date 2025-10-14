/**
 * This is the City class that stores the cityRecords that we are using to sort
 * in trees.
 * 
 * @author William Perryman
 * @author Edwin Barrack
 * @version 10/9/2025
 */
public class City implements Comparable<City> {
    private String cityName;
    private int xValue;
    private int yValue;

    /**
     * This is parameterized constructor for the City class.
     * 
     * @param name
     *            is the given city name
     * @param x
     *            is the given city x cord
     * @param y
     *            is the given city y cord
     */
    public City(String name, int x, int y) {
        this.cityName = name;
        this.xValue = x;
        this.yValue = y;
    }


    /**
     * Help sorts by name.
     * 
     * @param other
     *            which is the city being compared
     * @return int depending how it is compared
     * @Override
     */
    public int compareTo(City other) {
        return this.cityName.compareTo(other.cityName);
    }


    /**
     * This is the getter method for cityName field.
     * 
     * @return String name of the city
     */
    public String getCityName() {
        return cityName;
    }


    /**
     * This is the getter method for xValue field.
     * 
     * @return int the current x value
     */
    public int getXValue() {
        return xValue;
    }


    /**
     * This is the getter method for yValue field.
     * 
     * @return int the y value
     */
    public int getYValue() {
        return yValue;
    }


    /**
     * This is the toString method for the City class.
     * 
     * @Override
     * 
     * @return String the city info
     */
    public String toString() {
        return cityName + " (" + xValue + ", " + yValue + ")";
    }


    /**
     * This is the equals method for the City class.
     * 
     * @Override
     * @param otherCity
     *            is the possible other city
     * 
     * @return boolean true if they are equal false if not
     */
    public boolean equals(Object otherCity) {
        // If the parameter is null or not the City class
        if (otherCity == null || otherCity.getClass() != this.getClass()) {
            return false;
        }
        // Gets a City object from the parameterized Object.
        City city = (City)otherCity;

        return this.getCityName().equals(city.getCityName())
            && this.xValue == city.xValue && this.yValue == city.yValue;
    }
}
