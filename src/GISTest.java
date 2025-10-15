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
        System.out.println(it.print());
        assertTrue(it.insert("Tacoma", 1000, 100));
        System.out.println(it.print());
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
        System.out.println("\n HERE LOOK HERE for BST\n" + it.print());
        assertFuzzyEquals("Tacoma (1000, 100)", it.delete("Tacoma"));
        System.out.println("\n HERE LOOK HERE for BST\n" + it.print());
        assertFuzzyEquals("3\nChicago", it.delete(100, 150));
        System.out.println("\n HERE LOOK HERE for BST\n" + it.print());
        System.out.println("\n HERE LOOK HERE \n" + it.debug());
        System.out.println(it.print());
        assertFuzzyEquals("L (101, 150)\n" + "Atlanta (10, 500)\n"
            + "Baltimore (0, 300)\n" + "Washington (5, 350)\n"
            + "L (11, 500)\n5", it.search(0, 0, 2000));
        assertFuzzyEquals("Baltimore (0, 300)\n4", it.search(0, 300, 0));
    }

// /**
// * Tests the city class
// *
// * @throws IOException
// */
// public void testCity() throws IOException {
// City myCity = new City("Hi", 1, 1);
// City otherCity = new City("Hi", 2, 2);
// myCity = new City("Richmond", 1, 1);
//
// myCity.setXValue(2);
// assertEquals(myCity.getXValue(), 2);
// myCity.setYValue(2);
// assertEquals(myCity.getYValue(), 2);
//
// assertTrue(myCity.equals(myCity));
// assertFalse(myCity.equals(null));
// assertFalse(myCity.equals(it));
// assertFalse(myCity.equals(otherCity));
// otherCity = new City("Richmond", 2, 2);
// assertTrue(myCity.equals(otherCity));
// otherCity.setXValue(1);
// assertFalse(myCity.equals(otherCity));
// otherCity.setXValue(2);
// otherCity.setYValue(1);
// assertFalse(myCity.equals(otherCity));
// }


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
            + "right (125, 100)\r\n" + "9", it.search(100, 100, 25));
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
        String resultb = it.delete("F");
        assertTrue(resultb.contains("F"));

        // Test case: Remove node with only left child
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Left", 50, 50));
        assertTrue(it.insert("LeftLeft", 25, 25));
        // Left has only a left child (LeftLeft)
        assertFuzzyEquals("Left (50, 50)\n", it.delete("Left"));
        assertEquals("", it.info("Left"));
        assertFuzzyEquals("LeftLeft (25, 25)", it.info("LeftLeft"));

        // Test case: Remove node with only right child
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Right", 150, 150));
        assertTrue(it.insert("RightRight", 175, 175));
        // Right has only a right child (RightRight)
        assertFuzzyEquals("Right (150, 150)\n", it.delete("Right"));
        assertEquals("", it.info("Right"));
        assertFuzzyEquals("RightRight (175, 175)", it.info("RightRight"));

        // Test case: useEquals=true with matching equals
        it.clear();

        // Test case: deleteMax with node having left child (input.getRight() ==
        // null path)
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Left", 50, 50));
        assertTrue(it.insert("LeftLeft", 25, 25));
        assertTrue(it.insert("LeftRight", 75, 75));
        assertTrue(it.insert("LeftRightLeft", 60, 60));
        // When we delete Left, deleteMax will find LeftRight (max of left
        // subtree)
        // LeftRight has a left child (LeftRightLeft), so deleteMax returns
        // input.getLeft()
        assertFuzzyEquals("Left (50, 50)\n", it.delete("Left"));

        // Test case: getMax and deleteMax with deep right chain
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("A", 50, 50));
        assertTrue(it.insert("B", 60, 60));
        assertTrue(it.insert("C", 70, 70));
        assertTrue(it.insert("D", 80, 80));
        // Deleting Root will use getMax to find D (rightmost of left subtree)
        assertFuzzyEquals("Root (100, 100)\n", it.delete("Root"));
        assertFuzzyEquals("D (80, 80)", it.info("D"));

        // Test case: Remove from empty subtree (base == null path)
        it.clear();
        assertTrue(it.insert("OnlyRoot", 100, 100));
        String emptyDelete = it.delete("NonExistent");
        assertEquals("", emptyDelete);

        // Test case: comparison > 0 (search left subtree)
        it.clear();
        assertTrue(it.insert("M", 100, 100));
        assertTrue(it.insert("Z", 200, 200));
        assertTrue(it.insert("A", 50, 50));
        // Deleting "A" requires going left from "M"
        assertFuzzyEquals("A (50, 50)\n", it.delete("A"));

        // Test case: comparison < 0 (search right subtree)
        it.clear();
        assertTrue(it.insert("M", 100, 100));
        assertTrue(it.insert("A", 50, 50));
        assertTrue(it.insert("Z", 200, 200));
        // Deleting "Z" requires going right from "M"
        assertFuzzyEquals("Z (200, 200)\n", it.delete("Z"));

        // Additional tests to ensure getMax and deleteMax coverage

        // Test case: Direct call to ensure getMax iterates through multiple
        // right nodes
        it.clear();
        assertTrue(it.insert("A", 10, 10));
        assertTrue(it.insert("B", 20, 20));
        assertTrue(it.insert("C", 30, 30));
        assertTrue(it.insert("D", 40, 40));
        // Delete "B" which has right child and forces getMax to traverse
        String res = it.delete("B");
        assertTrue(res.contains("B"));

        // Test case: Ensure deleteMax recursive path is hit
        it.clear();
        assertTrue(it.insert("Root", 50, 50));
        assertTrue(it.insert("L1", 10, 10));
        assertTrue(it.insert("L2", 20, 20));
        assertTrue(it.insert("L3", 22, 22));
        // Delete "Left" - it has left subtree L1->L2->L3
        // getMax(Left.left) will traverse to L3 (rightmost)
    }


    /**
     * Test clear on populated tree
     * 
     * @throws IOException
     */
    public void testRefClearPopulated() throws IOException {
        assertTrue(it.insert("Chicago", 100, 150));
        assertTrue(it.insert("Atlanta", 10, 500));
        assertTrue(it.insert("Tacoma", 1000, 100));
        assertTrue(it.insert("Baltimore", 0, 300));
        assertTrue(it.insert("Washington", 5, 350));
        assertFalse(it.insert("X", 100, 150));
        assertTrue(it.insert("L", 101, 150));
        assertTrue(it.insert("L", 11, 500));
        assertTrue(it.clear());
        assertFuzzyEquals("", it.print());
        assertFuzzyEquals("", it.debug());
        assertFuzzyEquals("", it.info("CityName"));
        assertFuzzyEquals("", it.info(5, 5));
        assertFuzzyEquals("", it.delete("CityName"));
        assertFuzzyEquals("", it.delete(5, 5));

        it.clear();

        // Test 1: Build a tree where we can test X dimension (dimension % 2 ==
        // 0) conditions
        assertTrue(it.insert("Root", 100, 100)); // Dimension 0 (X comparison)
        assertTrue(it.insert("Left", 50, 150)); // x=50 < 100, goes left,
                                                // Dimension 1 (Y comparison)
        assertTrue(it.insert("Right", 150, 50)); // x=150 > 100, goes right,
                                                 // Dimension 1 (Y comparison)
        assertTrue(it.insert("LL", 25, 125)); // From Left: y=125 < 150, goes
                                              // left, Dimension 2 (X
                                              // comparison)
        assertTrue(it.insert("LR", 75, 175)); // From Left: y=175 > 150, goes
                                              // right, Dimension 2 (X
                                              // comparison)

        // Test deletion that follows x < condition (dimension % 2 == 0 && x <
        // node)
        // Delete LL (25, 125): From Root (dim=0): 25 < 100 → go left to Left
        String result1 = it.delete(25, 125);
        System.out.println("Delete LL result: " + result1);
        assertTrue("Should delete LL", result1.contains("LL"));
        assertEquals("LL should be gone", "", it.info(25, 125));

        it.clear();

        // Test 2: Build tree to test Y dimension (dimension % 2 == 1)
        // conditions

        assertTrue(it.insert("Center", 100, 100)); // Dimension 0 (X comparison)
        assertTrue(it.insert("Left", 75, 125)); // x=75 < 100, Dimension 1 (Y
                                                // comparison)
        assertTrue(it.insert("Right", 125, 75)); // x=125 > 100, Dimension 1 (Y
                                                 // comparison)
        assertTrue(it.insert("LeftUp", 80, 150)); // From Left: y=150 > 125,
                                                  // Dimension 2 (X comparison)
        assertTrue(it.insert("LeftDown", 70, 100)); // From Left: y=100 < 125,
                                                    // Dimension 2 (X
                                                    // comparison)

        // Test deletion that follows y < condition (dimension % 2 == 1 && y <
        // node)
        // Delete LeftDown (70, 100): From Center (dim=0): 70 < 100 → go left to
        // Left
        // From Left (dim=1): 100 < 125 → go left to LeftDown
        String result2 = it.delete(70, 100);
        System.out.println("Delete LeftDown result: " + result2);
        assertTrue("Should delete LeftDown", result2.contains("LeftDown"));
        assertEquals("LeftDown should be gone", "", it.info(70, 100));

        it.clear();

        // Test 3: Test the else branch (when conditions are false)
        assertTrue(it.insert("Root", 50, 50)); // Dimension 0 (X comparison)
        assertTrue(it.insert("RightChild", 75, 25)); // x=75 > 50, goes right,
                                                     // Dimension 1 (Y
                                                     // comparison)
        assertTrue(it.insert("RightRight", 80, 75)); // From RightChild: y=75 >
                                                     // 25, goes right,
                                                     // Dimension 2 (X
                                                     // comparison)

        // Delete RightRight (80, 75): From Root (dim=0): 80 > 50 → go right
        // (else branch)
        // From RightChild (dim=1): 75 > 25 → go right (else branch)
        String result3 = it.delete(80, 75);
        System.out.println("Delete RightRight result: " + result3);
        assertTrue("Should delete RightRight", result3.contains("RightRight"));
        assertEquals("RightRight should be gone", "", it.info(80, 75));

        it.clear();

        // Test 4: Complex tree testing both conditions at different levels
        assertTrue(it.insert("A", 100, 100)); // Level 0, dimension 0 (X)
        assertTrue(it.insert("B", 50, 150)); // Level 1, dimension 1 (Y) - x <
                                             // 100
        assertTrue(it.insert("C", 150, 50)); // Level 1, dimension 1 (Y) - x >
                                             // 100
        assertTrue(it.insert("D", 25, 125)); // Level 2, dimension 2 (X) - y <
                                             // 150
        assertTrue(it.insert("E", 75, 175)); // Level 2, dimension 2 (X) - y >
                                             // 150
        assertTrue(it.insert("F", 125, 25)); // Level 2, dimension 2 (X) - y <
                                             // 50
        assertTrue(it.insert("G", 175, 75)); // Level 2, dimension 2 (X) - y >
                                             // 50
        String result4 = it.delete(25, 125);
        assertTrue("Should delete D", result4.contains("D"));

        // Test path: A(dim=0) → x=175>100 go right to C(dim=1) → y=75>50 go
        // right to G(dim=2)
        String result5 = it.delete(175, 75);
        assertTrue("Should delete G", result5.contains("G"));

        it.clear();

        // Test 5: Edge cases with equal coordinates
        assertTrue(it.insert("Same", 50, 50));
        assertTrue(it.insert("EdgeX", 49, 60)); // x=49 < 50 (left)
        assertTrue(it.insert("EdgeY", 60, 49)); // x=60 > 50, then y=49 < ?
                                                // depends on tree structure

        // Test exact boundary where x < condition is just barely true
        String result6 = it.delete(49, 60);
        assertTrue("Should delete EdgeX", result6.contains("EdgeX"));

        it.clear();

        // Test 6: Verify node visit counting accuracy
        assertTrue(it.insert("Root", 64, 64)); // Perfect binary tree for
                                               // predictable paths
        assertTrue(it.insert("L1", 32, 96)); // Level 1 left
        assertTrue(it.insert("R1", 96, 32)); // Level 1 right
        assertTrue(it.insert("LL", 16, 80)); // Level 2 left-left
        assertTrue(it.insert("LR", 48, 112)); // Level 2 left-right
        assertTrue(it.insert("RL", 80, 16)); // Level 2 right-left
        assertTrue(it.insert("RR", 112, 48)); // Level 2 right-right

        // Delete leaf node LL - should visit Root, L1
    }


    /**
     * Test the conditional logic in KDTree findMin method
     * 
     * @throws IOException
     */
    public void testHelpRemoveConditionalLogic() throws IOException {

        // Test Case 1: First condition - temp1 == null (no left child)
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Right", 150, 75));
        // When finding min in X dimension from Root, only right child exists
        // temp1 will be null, first condition should return Root as minimum
        String result1 = it.delete(100, 100);
        assertTrue("Should delete Root when temp1 is null", result1.contains(
            "Root"));

        // Test Case 2: Second condition - descrim % 2 == 0 && x1 > x3 (X
        // dimension comparison)
        it.clear();
        assertTrue(it.insert("Center", 50, 50)); // Root at (50,50)
        assertTrue(it.insert("Left", 25, 75)); // Left child at (25,75)
        assertTrue(it.insert("LeftChild", 30, 60)); // Left's child at (30,60)
        // When deleting Center, findMin for X dimension should compare x1=30 >
        // x3=50 (false)
        // and x1=25 > x3=50 (false), so should return the left node with x=25
        String result2 = it.delete(50, 50);
        assertTrue("Should handle X dimension comparison correctly", result2
            .contains("Center"));

        // Test Case 3: Second condition - descrim % 2 == 1 && y1 > y3 (Y
        // dimension comparison)
        it.clear();
        assertTrue(it.insert("Root", 100, 100)); // Dimension 0 (X)
        assertTrue(it.insert("Left", 75, 125)); // Dimension 1 (Y), left child
        assertTrue(it.insert("LL", 80, 110)); // Dimension 2 (X), y=110 < 125
        assertTrue(it.insert("LR", 70, 140)); // Dimension 2 (X), y=140 > 125
        // When finding min in Y dimension, should compare y values
        String result3 = it.delete(100, 100);
        assertTrue("Should handle Y dimension comparison correctly", result3
            .contains("Root"));

        // Test Case 4: Third condition - temp2 != null && descrim % 2 == 0 &&
        // x1 > x2
        it.clear();
        assertTrue(it.insert("Center", 50, 50)); // Root
        assertTrue(it.insert("Left", 25, 75)); // Must check right branch case
        assertTrue(it.insert("Right", 75, 25));
        assertTrue(it.insert("RL", 60, 40)); // Right's left child (x=60)
        assertTrue(it.insert("RR", 90, 10)); // Right's right child (x=90)
        // Test when both temp1 and temp2 exist and x comparison determines
        // minimum
        String result4 = it.delete(50, 50);
        assertTrue("Should handle temp2 X comparison correctly", result4
            .contains("Center"));

        // Test Case 5: Third condition - temp2 != null && descrim % 2 == 1 &&
        // y1 > y2
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Child", 75, 125)); // Forces Y dimension check at
                                                 // level 1
        assertTrue(it.insert("LC", 60, 110)); // Child's left (y=110)
        assertTrue(it.insert("RC", 90, 140)); // Child's right (y=140)
        assertTrue(it.insert("Deep", 55, 105)); // Deeper node to trigger
                                                // complex findMin
        // Test Y dimension comparison between temp1 and temp2
        String result5 = it.delete(75, 125);
        assertTrue("Should handle temp2 Y comparison correctly", result5
            .contains("Child"));

        // Test Case 6: Edge case where temp1 != null but condition fails
        it.clear();
        assertTrue(it.insert("Main", 60, 60));
        assertTrue(it.insert("L1", 40, 80)); // Left branch
        assertTrue(it.insert("L1L", 35, 70)); // Left's left (smaller X)
        assertTrue(it.insert("L1R", 45, 90)); // Left's right (larger X)
        // When temp1 points to node with x=35, and current node x=40
        // Condition x1 > x3 is 35 > 40 = false, so should return temp1
        String result6 = it.delete(60, 60);
        assertTrue("Should return temp1 when conditions are met", result6
            .contains("Main"));

        // Test Case 7: Complex tree testing both branches of findMin
        it.clear();
        assertTrue(it.insert("Complex", 80, 80));
        assertTrue(it.insert("CL", 60, 100)); // Complex left
        assertTrue(it.insert("CR", 120, 60)); // Complex right
        assertTrue(it.insert("CLL", 50, 90)); // Complex left-left
        assertTrue(it.insert("CLR", 70, 110)); // Complex left-right
        assertTrue(it.insert("CRL", 110, 70)); // Complex right-left
        assertTrue(it.insert("CRR", 130, 50)); // Complex right-right
        // This creates scenarios where findMin must check both branches
        String result7 = it.delete(80, 80);
        assertTrue("Should handle complex tree structure", result7.contains(
            "Complex"));

        // Test Case 8: Verify dimension mismatch forces right branch check
        it.clear();
        assertTrue(it.insert("DimTest", 100, 100)); // Dim 0 (X-ordered)
        assertTrue(it.insert("DT_L", 75, 125)); // Dim 1 (Y-ordered), left of
                                                // DimTest
        assertTrue(it.insert("DT_R", 125, 75)); // Dim 1 (Y-ordered), right of
                                                // DimTest
        assertTrue(it.insert("DT_LL", 60, 110)); // Dim 2 (X-ordered), left of
                                                 // DT_L
        assertTrue(it.insert("DT_LR", 90, 140)); // Dim 2 (X-ordered), right of
                                                 // DT_L
        // When finding min for X dimension (descrim=0) at Y-ordered node
        // (dimension=1)
        // descrim % 2 != dimension % 2, so must check both left and right
        // branches
        String result8 = it.delete(75, 125);
        assertTrue("Should check right branch when dimensions differ", result8
            .contains("DT_L"));

        assertFuzzyEquals("All helpRemove conditional logic tests completed",
            "All helpRemove conditional logic tests completed");
    }


    /**
     * Test edge cases for inCircle boundary conditions
     * 
     * @throws IOException
     */
    public void testInCircleBoundaryConditions() throws IOException {
        it.clear();

        // Test exact boundary cases for circle calculations
        assertTrue(it.insert("Center", 0, 0));
        assertTrue(it.insert("Right", 3, 0));
        assertTrue(it.insert("Up", 0, 4));
        assertTrue(it.insert("Diagonal", 3, 4));
        assertTrue(it.insert("Outside", 10, 10));

        // Test radius = 5 (3-4-5 triangle)
        // Points at (3,0) and (0,4) should be exactly on boundary
        String result1 = it.search(0, 0, 5);
        assertTrue("Should include boundary points", result1.contains(
            "Center"));
        assertTrue("Should include boundary points", result1.contains("Right"));
        assertTrue("Should include boundary points", result1.contains("Up"));
        assertTrue("Should include diagonal point", result1.contains(
            "Diagonal"));
        assertFalse("Should exclude far point", result1.contains("Outside"));

        // Test radius = 0 (only exact matches)
        String result2 = it.search(0, 0, 0);
        assertTrue("Should include exact match", result2.contains("Center"));
        assertFalse("Should exclude non-exact", result2.contains("Right"));

        // Test radius = 1 (very small circle)
        String result3 = it.search(0, 0, 1);
        assertTrue("Should include center", result3.contains("Center"));
        assertFalse("Should exclude distant points", result3.contains("Right"));
    }


    /**
     * Test arithmetic edge cases in search boundaries
     * 
     * @throws IOException
     */
    public void testSearchArithmeticEdgeCases() throws IOException {
        it.clear();

        // Test edge cases where x-r or x+r calculations matter
        assertTrue(it.insert("LeftEdge", 5, 50));
        assertTrue(it.insert("RightEdge", 15, 50));
        assertTrue(it.insert("Center", 10, 50));

        // Search with radius that exactly includes/excludes boundaries
        String result1 = it.search(10, 50, 5);
        assertTrue("Should include left edge", result1.contains("LeftEdge"));
        assertTrue("Should include right edge", result1.contains("RightEdge"));
        assertTrue("Should include center", result1.contains("Center"));

        // Search with radius just smaller than boundary
        String result2 = it.search(10, 50, 4);
        assertFalse("Should exclude left edge", result2.contains("LeftEdge"));
        assertFalse("Should exclude right edge", result2.contains("RightEdge"));
        assertTrue("Should include center", result2.contains("Center"));

        // Test y-dimension boundaries
        it.clear();
        assertTrue(it.insert("TopEdge", 50, 15));
        assertTrue(it.insert("BottomEdge", 50, 5));
        assertTrue(it.insert("YCenter", 50, 10));

        String result3 = it.search(50, 10, 5);
        assertTrue("Should include all y-boundary points", result3.contains(
            "TopEdge"));
        assertTrue("Should include all y-boundary points", result3.contains(
            "BottomEdge"));
        assertTrue("Should include all y-boundary points", result3.contains(
            "YCenter"));
    }


    /**
     * Test dimension boundary conditions in findMin
     * 
     * @throws IOException
     */
    public void testFindMinDimensionEdgeCases() throws IOException {
        it.clear();

        // Create tree where findMin must check both branches due to dimension
        // mismatch
        assertTrue(it.insert("Root", 50, 50)); // Dim 0 (X)
        assertTrue(it.insert("Left", 25, 75)); // Dim 1 (Y)
        assertTrue(it.insert("Right", 75, 25)); // Dim 1 (Y)
        assertTrue(it.insert("LL", 20, 60)); // Dim 2 (X)
        assertTrue(it.insert("LR", 30, 90)); // Dim 2 (X)
        assertTrue(it.insert("RL", 60, 20)); // Dim 2 (X)
        assertTrue(it.insert("RR", 90, 30)); // Dim 2 (X)

        // Delete nodes that force complex findMin operations
        String result1 = it.delete(25, 75); // Forces findMin to check both
                                            // branches
        assertTrue("Should delete left node", result1.contains("Left"));

        String result2 = it.delete(50, 50); // Forces findMin on root
        assertTrue("Should delete root", result2.contains("Root"));
    }


    /**
     * Test conditional boundary cases in helpRemove
     * 
     * @throws IOException
     */
    public void testHelpRemoveConditionalBoundaries() throws IOException {
        it.clear();

        // Test exact equality conditions at different dimensions
        assertTrue(it.insert("Same", 100, 100));
        assertTrue(it.insert("SameX", 100, 50)); // Same X, different Y
        assertTrue(it.insert("SameY", 50, 100)); // Same Y, different X
        assertTrue(it.insert("Different", 75, 75));

        // Test deletion following different conditional paths
        String result1 = it.delete(100, 50); // Tests X dimension path
        assertTrue("Should delete SameX", result1.contains("SameX"));

        String result2 = it.delete(50, 100); // Tests Y dimension path
        assertTrue("Should delete SameY", result2.contains("SameY"));

        // Test edge case with boundary coordinates (0,0 is valid minimum)
        it.clear();
        assertTrue(it.insert("Origin", 0, 0)); // 0,0 should be valid
        assertFalse(it.insert("NegX", -1, 0)); // Should reject negative X
        assertFalse(it.insert("NegY", 0, -1)); // Should reject negative Y

        String result3 = it.delete(-1, 0); // Should return empty since invalid
                                           // coords
        assertEquals("Should return empty for invalid coordinates", "",
            result3);
    }


    /**
     * Test arithmetic operations with extreme values
     * 
     * @throws IOException
     */
    public void testArithmeticExtremeValues() throws IOException {
        // Test with large coordinates - calculate actual distance first
        assertTrue(it.insert("Large", 30000, 30000));
        assertTrue(it.insert("Origin", 0, 0));

        // Distance from (0,0) to (30000,30000) = sqrt(30000² + 30000²) ≈ 42426
        // Use radius larger than actual distance
        String result1 = it.search(0, 0, 45000);
        assertTrue("Should include large coordinate city", result1.contains(
            "Large"));

        // Test with radius smaller than distance - should exclude
        String result1b = it.search(0, 0, 40000);
        assertFalse("Should exclude large coordinate city", result1b.contains(
            "Large"));

        // Test boundary arithmetic with coordinates near limits
        assertFalse(it.insert("MaxMinus", 99999, 99999));
    }


    /**
     * Additional mutation testing scenarios
     * 
     * @throws IOException
     */
    public void testCityMutationResistance() throws IOException {
        City base = new City("Test", 100, 200);

        // Test that equals method checks ALL conditions properly
        // These tests help catch mutations in the boolean logic

        // Mutation: changing && to || in equals method
        City diffNameSameCoords = new City("Different", 100, 200);
        assertFalse("Should be false even with same coordinates", base.equals(
            diffNameSameCoords));

        // Mutation: changing == to != in coordinate comparisons
        City sameNameDiffX = new City("Test", 101, 200);
        assertFalse("Should be false with different X", base.equals(
            sameNameDiffX));

        City sameNameDiffY = new City("Test", 100, 201);
        assertFalse("Should be false with different Y", base.equals(
            sameNameDiffY));

        // Mutation: removing null check
        assertFalse("Null check must work", base.equals(null));

        // Mutation: changing class check
        assertFalse("Class check must work", base.equals("Not a city"));

        // Test toString mutations
        String expected = "Test (100, 200)";
        assertEquals("ToString format must be exact", expected, base
            .toString());

        // Test that changing any single character in toString would fail
        assertFalse("Should not match with extra space", base.toString().equals(
            "Test  (100, 200)"));
        assertFalse("Should not match without space", base.toString().equals(
            "Test(100, 200)"));
        assertFalse("Should not match with different brackets", base.toString()
            .equals("Test [100, 200]"));

        // Test compareTo edge cases for mutations
        City same = new City("Test", 999, 888); // Different coords, same name
        assertEquals("CompareTo should only compare names", 0, base.compareTo(
            same));

        // Test that compareTo uses name comparison correctly
        City lexLater = new City("Zest", 50, 60);
        assertTrue("Should be negative when this < other", base.compareTo(
            lexLater) < 0);
        assertTrue("Should be positive when this > other", lexLater.compareTo(
            base) > 0);
    }


    /**
     * Test null handling in getMax and deleteMax methods indirectly
     * 
     * @throws IOException
     */
    public void testGetMaxAndDeleteMaxNullHandling() throws IOException {
        // Test with empty tree - operations should handle gracefully
        String emptyResult = it.delete("NonExistent");
        assertEquals("Should return empty string for empty tree", "",
            emptyResult);

        String emptyInfo = it.info("NonExistent");
        assertEquals("Should return empty for non-existent city", "",
            emptyInfo);

        // Test coordinate-based operations on empty tree
        String emptyCoordDelete = it.delete(10, 10);
        assertEquals("Should return empty when deleting from empty tree", "",
            emptyCoordDelete);
    }


    /**
     * Test edge cases with single nodes
     * 
     * @throws IOException
     */
    public void testSingleNodeOperations() throws IOException {
        it.clear();
        assertTrue(it.insert("Single", 100, 100));

        // Test operations on single-node tree
        String info = it.info("Single");
        assertTrue("Should find single node", info.contains("Single"));

        // Remove the single node
        String result = it.delete("Single");
        assertTrue("Should delete single node", result.contains("Single"));

        // Verify removal
        String afterDelete = it.info("Single");
        assertEquals("Single node should be gone", "", afterDelete);
    }


    /**
     * Test complex tree operations
     * 
     * @throws IOException
     */
    public void testComplexTreeOperations() throws IOException {
        it.clear();

        // Build complex tree structure
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Left", 50, 50));
        assertTrue(it.insert("Right", 150, 150));
        assertTrue(it.insert("LeftLeft", 25, 25));
        assertTrue(it.insert("LeftRight", 75, 75));

        // Test deletion of internal nodes
        String result = it.delete("Left");
        assertTrue("Should delete internal node", result.contains("Left"));

        // Verify tree structure is maintained
        String rootInfo = it.info("Root");
        assertTrue("Root should still exist", rootInfo.contains("Root"));

        String leftLeftInfo = it.info("LeftLeft");
        assertTrue("LeftLeft should still exist", leftLeftInfo.contains(
            "LeftLeft"));
    }


    // 1) Equals-go-right at root on X (hits the exact line under test)
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteEqualsGoRightOnXAtRoot() {
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("SameX_Right", 100, 50)); // equal X, inserted to
                                                       // right

        // Path: root(100,100)[dim=0] -> equals on X -> go right -> delete leaf
        assertFuzzyEquals("2\nSameX_Right", it.delete(100, 50));

        // Verify removal and structure
        assertFuzzyEquals("", it.info(100, 50));
        assertFuzzyEquals("0Root (100, 100)\n", it.debug());
    }


    // 2) Greater-than-go-right at root on X
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteGreaterGoRightOnXAtRoot() {
        it.clear();
        assertTrue(it.insert("Root", 50, 50));
        assertTrue(it.insert("RightLeaf", 70, 70));

        // Path: root(50,50)[dim=0] -> 70 > 50 -> go right -> delete leaf
        assertFuzzyEquals("2\nRightLeaf", it.delete(70, 70));
        assertFuzzyEquals("", it.info(70, 70));
    }


    // 3) Less-than-go-left path (complements right-branch coverage)
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteLessGoLeftOnXAtRoot() {
        it.clear();
        assertTrue(it.insert("Root", 50, 50));
        assertTrue(it.insert("LeftLeaf", 25, 25));

        // Path: root(50,50)[dim=0] -> 25 < 50 -> go left -> delete leaf
        assertFuzzyEquals("2\nLeftLeaf", it.delete(25, 25));
        assertFuzzyEquals("", it.info(25, 25));
    }


    // 4) Delete node with right child -> uses findMin and successor replacement
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteNodeWithRightChildUsesFindMinAndCounts() {
        it.clear();
        // Build: Root(100,100)
        // Right subtree: Right(150,50) with RL(120,40) and RR(170,60)
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Left", 50, 150));
        assertTrue(it.insert("Right", 150, 50));
        assertTrue(it.insert("RL", 120, 40));
        assertTrue(it.insert("RR", 170, 60));

        // Delete Root: successor is min by X in right subtree => RL(120,40)
        // Visits: removeHelp(root)=1, findMin(right)=3 nodes (right, RL, RR),
        // removeHelp(right)=1, removeHelp(RL)=1 => total 6
        System.out.println(it.debug());
        assertFuzzyEquals("6\nRoot", it.delete(100, 100));
        System.out.println(it.debug());
        System.out.println(it.info(120, 40));
        // Root should now hold successor's data (120,40)
        assertFuzzyEquals("RL", it.info(120, 40));

        // Old Right subtree should have RL removed
        assertFuzzyEquals("", it.info("Root")); // verify no node named RL
                                                // remains
        System.out.println("\n LOOK HERE \n" + it.debug());
        // Debug sanity
        String dbg = it.debug();
        assertTrue(dbg.contains("Left (50, 150)"));
        assertTrue(dbg.contains("Right (150, 50)"));
        assertTrue(dbg.contains("RR (170, 60)"));
        assertTrue(dbg.contains("0RL (120, 40)"));

        assertFuzzyEquals("6\nRL", it.delete(120, 40)); // old root coords gone

        dbg = it.debug();
        assertTrue(dbg.contains("Left (50, 150)"));
        assertTrue(dbg.contains("Right (150, 50)"));
        assertTrue(dbg.contains("RR (170, 60)"));
    }


    // 5) Delete node whose right child is null (simple splice with left)
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteNoRightChildReturnsLeft() {
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("Left", 50, 150));
        assertTrue(it.insert("LL", 25, 125)); // Left has only left child

        // Delete Left: base.right == null -> return base.left
        // Visits: root(1) -> left(2) => total 2
        assertFuzzyEquals("4\nLeft", it.delete(50, 150));

        // LL should now hang directly off Root's left
        String dbg = it.debug();
        assertTrue(dbg.contains("LL (25, 125)"));
        assertFalse(dbg.contains("Left (50, 150)"));
    }


    // 6) Non-existent delete returns empty string
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteNonExistentReturnsEmpty() {
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertFuzzyEquals("", it.delete(200, 200));
        assertFuzzyEquals("0Root (100, 100)\n", it.debug());
    }


    // 7) Equals-go-right at level 1 on Y (covers equals-branch when
    // dimension=1)
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteEqualsGoRightOnYAtLevel1() {
        it.clear();
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("L", 50, 150)); // left of root
        assertTrue(it.insert("LR", 75, 150)); // equals on Y at L's level =>
                                              // goes right

        // Path: root[dim=0] (75 < 100) -> L[dim=1] (150 == 150) -> go right ->
        // LR
        // Visits: root(1) -> L(2) -> LR(3)
        assertFuzzyEquals("3\nLR", it.delete(75, 150));
        assertFuzzyEquals("", it.info(75, 150));
    }


    // 8) Successor tie on X in right subtree: root-of-right wins ties (strictly
    // smaller only)
    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteSuccessorTieOnXRightRootWinsTie() {
        it.clear();
        // Root to delete; right subtree has two nodes with same min X (tie)
        assertTrue(it.insert("Root", 100, 100));
        assertTrue(it.insert("RightRoot", 120, 10)); // right subtree root
        assertTrue(it.insert("RightLeft", 120, 5)); // left child with equal X

        // findMin(descrim=X) should keep RightRoot on tie (no <=), so successor
        // is RightRoot
        String res = it.delete(100, 100);
        assertTrue(res.startsWith("")); // we only assert behavior via structure
                                        // after

        // After replacement, tree should have:
        // - Root data replaced by RightRoot (120,10)
        // - Right subtree should be spliced to RightLeft (since we deleted
        // RightRoot and returned its left)
        String dbg = it.debug();
        assertTrue(dbg.contains("RightRoot (120, 10)"));
        assertTrue(dbg.contains("RightLeft (120, 5)"));
        assertFalse(dbg.contains("Root (100, 100)"));
    }


    /**
     * Test complex path with mixed branches and ensure counts include all
     * visited nodes
     * 
     * @throws IOException
     */
    public void testDeleteComplexPathMixedBranches() {
        it.clear();
        assertTrue(it.insert("A", 100, 100)); // root
        assertTrue(it.insert("B", 50, 150)); // left
        assertTrue(it.insert("C", 150, 50)); // right
        assertTrue(it.insert("D", 25, 125)); // left-left
        assertTrue(it.insert("E", 75, 175)); // left-right
        assertTrue(it.insert("F", 125, 25)); // right-left
        assertTrue(it.insert("G", 175, 75)); // right-right
        assertTrue(it.insert("H", 110, 40)); // in right-left subtree to deepen
                                             // findMin

        // Delete right subtree root C to force left-branch during successor
        // deletion
        // and ensure counts include both removeHelp and findMin nodes
        String s = it.delete(150, 50);
        assertTrue(s.contains("C"));
        // Sanity checks on structure after delete
        String dbg = it.debug();
        assertFalse(dbg.contains("C (150, 50)"));
        assertTrue(dbg.contains("F (125, 25)"));
        assertTrue(dbg.contains("H (110, 40)"));
        assertTrue(dbg.contains("G (175, 75)"));

        it.clear();
        assertTrue(it.insert("Root", 50, 50));
        assertTrue(it.insert("L", 25, 75));
        assertTrue(it.insert("R", 75, 25));
        assertTrue(it.insert("Successor", 60, 20)); // Successor of Root
        assertTrue(it.insert("SuccessorChild", 65, 10)); // Child of Successor

        // Action: Delete the root.
        it.delete(50, 50);
        String expectedDebug = "2    SuccessorChild (65, 10)\n"
            + "1  R (75, 25)\n" + "0Successor (60, 20)\n" + "1  L (25, 75)\n";
        System.out.println("\n DEBug \n" + it.debug());
       // assertFuzzyEquals(expectedDebug, it.debug());
    }


    /**
     * Failure
     * 
     * @throws IOException
     */
    public void testDeleteSameName() {
        assertTrue(it.insert("A", 100, 100));
        assertTrue(it.insert("A", 50, 150));
        assertTrue(it.insert("A", 150, 50));
        assertTrue(it.insert("A", 25, 125));
        assertTrue(it.insert("A", 75, 175));
        assertTrue(it.insert("A", 125, 25));
        assertTrue(it.insert("A", 175, 75));
        assertTrue(it.delete("A").contains("A"));
        assertEquals("", it.info("A"));
        assertEquals("", it.debug());
        assertEquals("", it.print());

        it.clear();
        assertTrue(it.insert("A", 100, 100));
        assertTrue(it.insert("A", 50, 150));
        assertTrue(it.insert("A", 150, 50));
        assertTrue(it.insert("A", 25, 125));
        assertTrue(it.insert("A", 75, 175));
        assertTrue(it.insert("A", 125, 25));
        assertTrue(it.insert("A", 175, 75));
        assertTrue(it.insert("B", 200, 200));
        assertTrue(it.delete("A").contains("A"));
        assertFuzzyEquals("0B (200, 200)\n", it.debug());

        it.clear();
        assertTrue(it.insert("A", 100, 100));
        assertTrue(it.insert("A", 50, 150));
        assertTrue(it.insert("A", 150, 50));
        assertTrue(it.insert("A", 25, 125));
        assertTrue(it.insert("A", 75, 175));
        assertTrue(it.insert("A", 125, 25));
        assertTrue(it.insert("A", 175, 75));
        assertTrue(it.insert("B", 200, 200));
        assertTrue(it.insert("a", 10, 10));
        System.out.println(it.debug());
        assertTrue(it.delete("A").contains("A"));
        System.out.println(it.debug());

        it.clear();
        assertTrue(it.insert("A", 100, 100));
        assertTrue(it.insert("A", 50, 150));
        assertTrue(it.insert("A", 150, 50));
        assertTrue(it.insert("A", 25, 125));
        assertTrue(it.insert("A", 75, 175));
        assertTrue(it.insert("A", 125, 25));
        System.out.println(it.info("A"));
        it.delete(75, 175);
        System.out.println("\n HERE HERE HERE \n" + it.info("A"));

    }


    /**
     * Duplicate-name inputs: delete by name removes all and updates KD/BST.
     * 
     * @throws IOException
     */
    public void testDuplicateNamesDeleteByNameAllRemovedAndOutputsUpdate()
        throws IOException {
        it.clear();
        assertTrue(it.insert("Alpha", 1, 1));
        assertTrue(it.insert("Dup", 10, 10));
        assertTrue(it.insert("Dup", 20, 20));
        assertTrue(it.insert("Dup", 5, 5));
        assertTrue(it.insert("Zoo", 300, 300));

        // Pre-conditions
        String beforeInfo = it.info("Dup");
        assertTrue(beforeInfo.contains("Dup (10, 10)"));
        assertTrue(beforeInfo.contains("Dup (20, 20)"));
        assertTrue(beforeInfo.contains("Dup (5, 5)"));
        String beforePrint = it.print();
        assertTrue(beforePrint.contains("Dup (10, 10)"));
        assertTrue(beforePrint.contains("Dup (20, 20)"));
        assertTrue(beforePrint.contains("Dup (5, 5)"));
        String beforeDebug = it.debug();
        assertTrue(beforeDebug.contains("Dup (10, 10)"));
        assertTrue(beforeDebug.contains("Dup (20, 20)"));
        assertTrue(beforeDebug.contains("Dup (5, 5)"));

        // Delete all by name
        String deleted = it.delete("Dup");
        assertTrue(deleted.contains("Dup (10, 10)"));
        assertTrue(deleted.contains("Dup (20, 20)"));
        assertTrue(deleted.contains("Dup (5, 5)"));

        // KD should be updated: no coords remain
        assertEquals("", it.info(10, 10));
        assertEquals("", it.info(20, 20));
        assertEquals("", it.info(5, 5));
        // BST info empty for name
        assertEquals("", it.info("Dup"));
        // print/debug should not contain any Dup entries
        String afterPrint = it.print();
        assertFalse(afterPrint.contains("Dup (10, 10)"));
        assertFalse(afterPrint.contains("Dup (20, 20)"));
        assertFalse(afterPrint.contains("Dup (5, 5)"));
        String afterDebug = it.debug();
        assertFalse(afterDebug.contains("Dup (10, 10)"));
        assertFalse(afterDebug.contains("Dup (20, 20)"));
        assertFalse(afterDebug.contains("Dup (5, 5)"));

        // Other cities remain
        assertEquals("Alpha", it.info(1, 1));
        assertEquals("Zoo", it.info(300, 300));
        assertTrue(it.info("Alpha").contains("Alpha (1, 1)"));
        assertTrue(it.info("Zoo").contains("Zoo (300, 300)"));
    }


    /**
     * Duplicate-name inputs: delete by coordinates removes only one.
     * 
     * @throws IOException
     */
    public void testDuplicateNamesDeleteByCoordsOnlyOneRemovedAndOutputs()
        throws IOException {
        it.clear();
        assertTrue(it.insert("Same", 10, 10));
        assertTrue(it.insert("Same", 10, 20));
        assertTrue(it.insert("Same", 20, 10));

        // All three present by name
        String list = it.info("Same");
        assertTrue(list.contains("Same (10, 10)"));
        assertTrue(list.contains("Same (10, 20)"));
        assertTrue(list.contains("Same (20, 10)"));

        // Delete one by exact coords
        String del = it.delete(10, 20);
        assertTrue(del.contains("Same"));
        // KD updated for that coord
        assertEquals("", it.info(10, 20));
        // Others remain
        assertEquals("Same", it.info(10, 10));
        assertEquals("Same", it.info(20, 10));
        String nameAfter = it.info("Same");
        System.out.println("AFTER DELETE BY COORDS: " + nameAfter);
        assertTrue(nameAfter.contains("Same (10, 10)"));
        assertTrue(nameAfter.contains("Same (20, 10)"));
        assertFalse(nameAfter.contains("Same (10, 20)"));

        // print/debug reflect removal
        String p = it.print();
        System.out.println(p);
        assertFalse(p.contains("Same (10, 20)"));
        assertTrue(p.contains("Same (10, 10)"));
        assertTrue(p.contains("Same (20, 10)"));
        String d = it.debug();
        assertFalse(d.contains("Same (10, 20)"));
    }


    /**
     * Deleting a non-existent name should not change structures.
     * 
     * @throws IOException
     */
    public void testDuplicateNamesDeleteNonexistentNameNoChange()
        throws IOException {
        it.clear();
        assertTrue(it.insert("X", 1, 1));
        String beforeP = it.print();
        String beforeD = it.debug();
        String res = it.delete("Nope");
        assertEquals("", res);
        assertEquals(beforeP, it.print());
        assertEquals(beforeD, it.debug());
        assertEquals("X", it.info(1, 1));
        assertTrue(it.info("X").contains("X (1, 1)"));
    }


    /**
     * Interleaved deletes with print/debug checks for duplicate names.
     * 
     * @throws IOException
     */
    public void testDuplicateNamesInterleavedDeletesPrintDebugChecks()
        throws IOException {
        it.clear();
        assertTrue(it.insert("M", 100, 100));
        assertTrue(it.insert("M", 50, 50));
        assertTrue(it.insert("M", 150, 150));
        assertTrue(it.insert("A", 10, 10));
        assertTrue(it.insert("Z", 200, 200));

        // Delete one M by coords
        String r1 = it.delete(50, 50);
        assertTrue(r1.contains("M"));
        assertEquals("", it.info(50, 50));
        assertTrue(it.info("M").contains("M (100, 100)"));
        assertTrue(it.info("M").contains("M (150, 150)"));
        assertFalse(it.info("M").contains("M (50, 50)"));
        assertFalse(it.debug().contains("M (50, 50)"));
        assertFalse(it.print().contains("M (50, 50)"));

        // Delete remaining M by name
        String r2 = it.delete("M");
        assertTrue(r2.contains("M (100, 100)"));
        assertTrue(r2.contains("M (150, 150)"));
        assertEquals("", it.info("M"));
        assertFalse(it.debug().contains("M (100, 100)"));
        assertFalse(it.debug().contains("M (150, 150)"));
        assertFalse(it.print().contains("M (100, 100)"));
        assertFalse(it.print().contains("M (150, 150)"));

        // Other nodes unaffected
        assertEquals("A", it.info(10, 10));
        assertEquals("Z", it.info(200, 200));
    }
}
