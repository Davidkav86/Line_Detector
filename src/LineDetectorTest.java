import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

/**
 * Test class that is designed to comprehensively test the main methods in the
 * LineDetector class. Ensures that all methods are working as they are designed
 * to.
 * 
 * @author David Kavanagh
 * 
 */
public class LineDetectorTest
{

	/**
	 * TestFileImport() - This method tests to see if the start method is
	 * reading in the file correctly. Once the file is read the points on the
	 * file are placed into a Points ArrayList. This method tests to see if that
	 * list is populated.
	 * 
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testFileImport()
	{
		LineDetector lineDetector = new LineDetector();

		String fileName = "points.txt";
		// enter the name of the file into the start method
		lineDetector.start(fileName);
		// test to see if the Points list has been populated with points
		assertTrue(lineDetector.getPoints() != null);
	}

	/**
	 * testFileContents() - This method tests to make sure that the file is
	 * being read correctly. It tests to see if the ints are being read from it.
	 * Then it tests to make sure that the correct number of points are being
	 * created from it.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testFileContents() throws IOException
	{

		LineDetector lineDetector = new LineDetector();
		// create a new file object
		File addThisFile = new File("Points.txt");

		lineDetector.setFile(addThisFile);

		Scanner checkPoints = new Scanner(addThisFile);

		int numOfPoints = checkPoints.nextInt();
		// test that first in being read is correct
		assertTrue(numOfPoints == 8);

		String fileName = "points.txt";
		// pass the name of the file into the start method
		lineDetector.start(fileName);

		ArrayList<Point> test = new ArrayList<Point>();
		// populate a test array with the all the points
		test = lineDetector.getPoints();
		// test to ensure that the correct number of points were made
		assertEquals(8, test.size());
		// test to ensure that the an incorrect number of were not made
		assertFalse(test.size() == 10);

	}

	/**
	 * testSortBySlope() - This method tests sortBySlope() in the LineDetector
	 * class. A comparePoints ArrayList is populated randomly with Points. A
	 * sorted ArrayList is populated with points in order of their slope with
	 * respect to the origin Point. First the two arrays are tested to ensure
	 * they are not equal. Then the comparePoints list is passed into the
	 * sortBySlope() and both lists are tested to ensure that they are now equal
	 * 
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testSortBySlope()
	{
		LineDetector lineDetector = new LineDetector();

		// create an origin point and four more points
		Point origin = new Point(1000, 0);
		Point p1 = new Point(3000, 7000);
		Point p2 = new Point(0, 1000);
		Point p3 = new Point(7000, 3000);
		Point p4 = new Point(20000, 21000);

		// create an ArrayList, enter points in random order
		ArrayList<Point> comparePoints = new ArrayList<Point>();
		comparePoints.add(p1);
		comparePoints.add(p2);
		comparePoints.add(p3);
		comparePoints.add(p4);

		// create an ArrayList, enter the points in sorted by slope order
		ArrayList<Point> sorted = new ArrayList<Point>();
		sorted.add(p1);
		sorted.add(p4);
		sorted.add(p3);
		sorted.add(p2);

		// test to see if the two lists are not equal
		assertFalse(sorted.equals(comparePoints));

		// create an ArrayList to test the comparePoints list
		ArrayList<Point> test = new ArrayList<Point>();

		// pass the comparePoints list into the sortBySlope method
		lineDetector.sortBySlope(origin, comparePoints);

		// add the comparePoints to the test list
		test.addAll(comparePoints);

		// test to see if the test list is in the same order as the already
		// sorted list
		assertTrue(test.equals(sorted));
	}

	/**
	 * testSortByYCoordinates() - This method tests sortByYCoordinates() in the
	 * LineDetector class. A line ArrayList is populated randomly with Points. A
	 * sorted ArrayList is populated with points in order of their Y
	 * coordinates. First the two arrays are tested to ensure they are not
	 * equal. Then the line list is passed into sortByYCoordinates() and both
	 * lists are tested to ensure that they are now equal
	 * 
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testSortByYCoordinates()
	{
		LineDetector lineDetector = new LineDetector();

		// create five points
		Point p1 = new Point(3000, 7000);
		Point p2 = new Point(0, 1000);
		Point p3 = new Point(7000, 3000);
		Point p4 = new Point(20000, 21000);
		Point p5 = new Point(1000, 0);

		// create an ArrayList, enter points in random order
		ArrayList<Point> line = new ArrayList<Point>();
		line.add(p1);
		line.add(p2);
		line.add(p3);
		line.add(p4);
		line.add(p5);

		// create an ArrayList, enter the points in order of their Y coordinates
		ArrayList<Point> sorted = new ArrayList<Point>();
		sorted.add(p5);
		sorted.add(p2);
		sorted.add(p3);
		sorted.add(p1);
		sorted.add(p4);

		// test to ensure that the two lists are not equal
		assertFalse(sorted.equals(line));

		// pass the comparePoints list into the sortByYCoordinates() method
		lineDetector.sortByYCoordinates(line);

		// test to see if the line list is in the same order as the already
		// sorted list
		assertTrue(sorted.equals(line));
	}

	/**
	 * testSortByXCoordinates() - This method tests sortByXCoordinates() in the
	 * LineDetector class. A line ArrayList is populated randomly with Points. A
	 * sorted ArrayList is populated with points in order of their X
	 * coordinates. First the two arrays are tested to ensure they are not
	 * equal. Then the line list is passed into sortByXCoordinates() and both
	 * lists are tested to ensure that they are now equal
	 * 
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testSortByXCoordinates()
	{
		LineDetector lineDetector = new LineDetector();

		// create five points
		Point p1 = new Point(3000, 7000);
		Point p2 = new Point(0, 1000);
		Point p3 = new Point(7000, 3000);
		Point p4 = new Point(20000, 21000);
		Point p5 = new Point(1000, 0);

		// create an ArrayList, enter points in random order
		ArrayList<Point> line = new ArrayList<Point>();
		line.add(p1);
		line.add(p2);
		line.add(p3);
		line.add(p4);
		line.add(p5);

		// create an ArrayList, enter the points in order of their X coordinates
		ArrayList<Point> sorted = new ArrayList<Point>();
		sorted.add(p2);
		sorted.add(p5);
		sorted.add(p1);
		sorted.add(p3);
		sorted.add(p4);

		// test to ensure that the two lists are not equal
		assertFalse(sorted.equals(line));

		// pass the comparePoints list into the sortByXCoordinates() method
		lineDetector.sortByXCoordinates(line);

		// test to see if the line list is in the same order as the already
		// sorted list
		assertTrue(sorted.equals(line));
	}

	/**
	 * testDuplicateLines() - This method tests the detectDuplicateLines()
	 * method in the LineDetector class. It creates two points ArrayLists that
	 * are identical. Creates a line object and passes one list into the line
	 * objects points list, then uses the detectDuplicateLines() method to 
	 * to see if they are duplicates the method returns true.
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testDetectDuplicates()
	{
		LineDetector lineDetector = new LineDetector();

		// create a new line object
		Line line = new Line();

		// create five points
		Point p1 = new Point(3000, 7000);
		Point p2 = new Point(0, 1000);
		Point p3 = new Point(7000, 3000);
		Point p4 = new Point(20000, 21000);
		Point p5 = new Point(1000, 0);

		// create an ArrayList of points
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);

		// add the points to the ArrayList of points in the line object
		line.setPoints(points);

		// create an ArrayList of lines
		ArrayList<Line> lines = new ArrayList<Line>();

		// add the line to the list of lines
		lines.add(line);

		// pass the list into the lines list in the lineDetector object
		lineDetector.setLines(lines);

		// create an ArrayList that is the exact same as the list in the line
		// object
		ArrayList<Point> compare = new ArrayList<Point>();
		compare.add(p1);
		compare.add(p2);
		compare.add(p3);
		compare.add(p4);
		compare.add(p5);

		// test to see if detectDuplicateLines() returns true
		assertTrue(lineDetector.detectDuplicateLines(compare));

	}

}
