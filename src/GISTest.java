import java.io.IOException;
import student.TestCase;

/**
 * @author William Perryman
 * @author Edwin Barrack
 * @version 10/9/2025
 */
public class GISTest extends TestCase {

    private GIS it;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        it = new GISDB();
    }


    /**
     * Test clearing on initial
     * 
     * @throws IOException
     */
    public void testRefClearInit() throws IOException {
        assertTrue(it.clear());
    }


    /**
     * Print testing for empty trees
     * 
     * @throws IOException
     */
    public void testRefEmptyPrints() throws IOException {
        assertFuzzyEquals("", it.print());
        assertFuzzyEquals("", it.debug());
        assertFuzzyEquals("", it.info("CityName"));
        assertFuzzyEquals("", it.info(5, 5));
        assertFuzzyEquals("", it.delete("CityName"));
        assertFuzzyEquals("", it.delete(5, 5));
    }


    /**
     * Print bad input checks
     * 
     * @throws IOException
     */
    public void testRefBadInput() throws IOException {
        assertFalse(it.insert("CityName", -1, 5));
        assertFalse(it.insert("CityName", 5, -1));
        assertFalse(it.insert("CityName", 100000, 5));
        assertFalse(it.insert("CityName", 5, 100000));
        assertFuzzyEquals("", it.search(-1, -1, -1));
    }


    /**
     * Insert some records and check output requirements for various commands
     * 
     * @throws IOException
     */
    public void testRefOutput() throws IOException {
        assertTrue(it.insert("Chicago", 100, 150));
        assertTrue(it.insert("Atlanta", 10, 500));
        assertTrue(it.insert("Tacoma", 1000, 100));
        assertTrue(it.insert("Baltimore", 0, 300));
        assertTrue(it.insert("Washington", 5, 350));
        assertFalse(it.insert("X", 100, 150));
        assertTrue(it.insert("L", 101, 150));
        assertTrue(it.insert("L", 11, 500));
        System.out.println(it.print());
        System.out.println(it.debug());
        assertFuzzyEquals("1  Atlanta (10, 500)\n" + "2    Baltimore (0, 300)\n"
            + "0Chicago (100, 150)\n" + "3      L (11, 500)\n"
            + "2    L (101, 150)\n" + "1  Tacoma (1000, 100)\n"
            + "2    Washington (5, 350)\n", it.print());
        assertFuzzyEquals("2    Baltimore (0, 300)\n"
            + "3      Washington (5, 350)\n" + "1  Atlanta (10, 500)\n"
            + "2    L (11, 500)\n" + "0Chicago (100, 150)\n"
            + "1  Tacoma (1000, 100)\n" + "2    L (101, 150)\n", it.debug());
        assertFuzzyEquals("L (101, 150)\nL (11, 500)", it.info("L"));
        assertFuzzyEquals("L", it.info(101, 150));
        // assertFuzzyEquals("Tacoma (1000, 100)", it.delete("Tacoma"));
        it.clear();

        /**
         * 
         * 
         * 
         * assertFuzzyEquals("3\nChicago", it.delete(100, 150));
         * assertFuzzyEquals("L (101, 150)\n" + "Atlanta (10, 500)\n"
         * + "Baltimore (0, 300)\n" + "Washington (5, 350)\n"
         * + "L (11, 500)\n5", it.search(0, 0, 2000));
         * assertFuzzyEquals("Baltimore (0, 300)\n4", it.search(0, 300, 0))
         */
    }


    /**
     * Tests the city class
     * 
     * @throws IOException
     */
    public void testCity() throws IOException {
        City myCity = new City("Hi", 1, 1);
        City otherCity = new City("Hi", 2, 2);
        myCity = new City("Richmond", 1, 1);

        myCity.setXValue(2);
        assertEquals(myCity.getXValue(), 2);
        myCity.setYValue(2);
        assertEquals(myCity.getYValue(), 2);

        assertTrue(myCity.equals(myCity));
        assertFalse(myCity.equals(null));
        assertFalse(myCity.equals(it));
        assertFalse(myCity.equals(otherCity));
        otherCity = new City("Richmond", 2, 2);
        assertTrue(myCity.equals(otherCity));
        otherCity.setXValue(1);
        assertFalse(myCity.equals(otherCity));
        otherCity.setXValue(2);
        otherCity.setYValue(1);
        assertFalse(myCity.equals(otherCity));
    }


    /**
     * Edwin's Tests for kdTree
     * 
     * @throws IOException
     */
    public void testKDTree() throws IOException {
        System.out.println(it.search(100, 100, 100));
        assertEquals(it.search(100, 100, 100), "0");
        assertEquals(it.info(100, 100), "");
        assertTrue(it.insert("root", 100, 100));
        assertTrue(it.insert("left", 75, 100));
        assertTrue(it.insert("right", 125, 100));
        assertTrue(it.insert("left2", 75, 75));
        assertTrue(it.insert("right2", 125, 125));
        assertTrue(it.insert("left3", 75, 125));
        assertTrue(it.insert("right3", 125, 75));
        assertTrue(it.insert("left4", 50, 100));
        assertTrue(it.insert("right4", 150, 100));
        assertFalse(it.insert("why", 100, 100));
        assertFalse(it.insert("me", 75, 100));
        assertTrue(it.insert("Tester", 100, 150));
        assertFuzzyEquals(it.debug(), "2    left2 (75, 75)\r\n"
            + "1  left (75, 100)\r\n" + "3      left4 (50, 100)\r\n"
            + "2    left3 (75, 125)\r\n" + "0root (100, 100)\r\n"
            + "2    right3 (125, 75)\r\n" + "1  right (125, 100)\r\n"
            + "3      Tester (100, 150)\r\n" + "2    right2 (125, 125)\r\n"
            + "3      right4 (150, 100)");

        assertEquals(it.info(100, 100), "root");
        assertEquals(it.info(75, 100), "left");
        assertEquals(it.info(125, 100), "right");
        assertEquals(it.info(75, 75), "left2");
        assertEquals(it.info(125, 125), "right2");
        assertEquals(it.info(75, 125), "left3");
        assertEquals(it.info(125, 75), "right3");
        assertEquals(it.info(50, 100), "left4");
        assertEquals(it.info(150, 100), "right4");
        assertEquals(it.info(100, 150), "Tester");

        assertFuzzyEquals("root (100, 100)\r\n" + "left (75, 100)\r\n"
            + "left2 (75, 75)\r\n" + "left3 (75, 125)\r\n"
            + "left4 (50, 100)\r\n" + "right (125, 100)\r\n"
            + "right3 (125, 75)\r\n" + "right2 (125, 125)\r\n"
            + "Tester (100, 150)\r\n" + "right4 (150, 100)\r\n" + "10", it
                .search(100, 100, 50));
        assertFuzzyEquals("root (100, 100)\r\n" + "left (75, 100)\r\n"
            + "right (125, 100)\r\n" + "8", it.search(100, 100, 25));
    }


    /**
     * Will's Test for BSTree
     * 
     * @throws IOException
     */
    public void testBSTree() throws IOException {
        assertTrue(it.insert("root", 100, 100));
        assertTrue(it.insert("left", 75, 100));
        assertTrue(it.insert("left", 75, 101));
        assertTrue(it.insert("right", 125, 100));

        System.out.println(it.info("left"));
        assertFuzzyEquals("left (75, 100)\r\n" + "left (75, 101)\r\n" + "", it
            .info("left"));

        assertFuzzyEquals("right (125, 100)\n", it.info("right"));
        assertEquals("", it.info("wrong"));

        it.clear();
        // Now testing delete, Single City
        assertTrue(it.insert("SingleCity", 100, 100));
        assertFuzzyEquals("SingleCity (100, 100)\n", it.delete("SingleCity"));
        assertEquals("", it.info("SingleCity")); // Verify removal
        it.clear();
        // Now multiple deletes
        assertTrue(it.insert("Duplicate", 50, 50));
        assertTrue(it.insert("Duplicate", 75, 75));
        assertTrue(it.insert("Duplicate", 25, 25));
        String result = it.delete("Duplicate");
        // Should remove all three cities with name "Duplicate"
        assertTrue(result.contains("Duplicate (50, 50)"));
        assertTrue(result.contains("Duplicate (75, 75)"));
        assertTrue(result.contains("Duplicate (25, 25)"));
        assertEquals("", it.info("Duplicate")); // All removed
        // Now leafs
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Leaf", 150, 150));
        assertFuzzyEquals("Leaf (150, 150)\n", it.delete("Leaf"));
        assertEquals("", it.info("Leaf"));
        System.out.println(it.info("Root"));
        assertFuzzyEquals("Root (100, 100)", it.info("Root"));

        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("LeftChild", 50, 50));
        assertTrue(it.insert("RightChild", 150, 150));
        assertTrue(it.insert("LeftGrand", 25, 25));
        assertTrue(it.insert("RightGrand", 175, 175));

        assertFuzzyEquals("Root (100, 100)\n", it.delete("Root"));
        assertEquals("", it.info("Root"));
        it.clear();
        assertTrue(it.insert("M", 100, 100)); // Root
        assertTrue(it.insert("F", 50, 50)); // Left child
        assertTrue(it.insert("T", 150, 150)); // Right child
        assertTrue(it.insert("C", 25, 25)); // Left-left grandchild
        assertTrue(it.insert("K", 75, 75)); // Left-right grandchild

        // Now delete "F" which has exactly 2 children (C and K)
        String results = it.delete("F");
        assertTrue(results.contains("F"));

        // Verify the tree structure is maintained
        assertEquals("", it.info("F")); // F should be gone
        assertFuzzyEquals("C (25, 25)", it.info("C")); // C should still exist
        assertFuzzyEquals("K (75, 75)", it.info("K")); // K should still exist

        it.clear();
        assertTrue(it.insert("M", 100, 100)); // Root
        assertTrue(it.insert("F", 50, 50)); // Left child
        assertTrue(it.insert("T", 150, 150)); // Right child
        assertTrue(it.insert("C", 25, 25)); // Left-left
        assertTrue(it.insert("K", 75, 75)); // Left-right
        assertTrue(it.insert("J", 70, 70)); // Makes K have a left child
        assertTrue(it.insert("L", 80, 80)); // Makes K have a right child

        // Now when deleting F, deleteMax(F.left) will need to recurse
        // because the max path is C -> K -> L (L is max, but K has right child)
        String resultb = it.delete("F");
        assertTrue(resultb.contains("F"));
    }
}
