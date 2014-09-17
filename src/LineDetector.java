import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Collections;

/**
 * LineDetector class is designed to read in a number of integers from a file.
 * The value of those integers is used to create points with X and Y
 * coordinates. This class will then use those points to try detect lines. If
 * three or more points share the same slope as the origin point, there is a
 * line. The line will then be drawn using Std.draw.
 * 
 * @author David Kavanagh
 * 
 */
public class LineDetector
{
	private static ArrayList<Point> points = new ArrayList<Point>();
	private static ArrayList<Point> comparePoints = new ArrayList<Point>();
	private static ArrayList<Line> lines = new ArrayList<Line>();
	private static double slope;
	private static File file;

	/**
	 * main() - This is the main method for the LineDetector class. This method
	 * is necessary for the user to be able to interact with the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// create a new scanner object
		Scanner sc = new Scanner(System.in);

		System.out
				.println(" Please enter the name of the file you would like to use:");

		// read in the file name and make it a string
		String fileName = sc.nextLine();

		start(fileName);

		detectLines();

	}

	/**
	 * start() - This method is designed to start the program. The user enters
	 * the name of the file that the points will get read in from. The points
	 * are then stored into the points ArrayList. This method will only be
	 * called once at the start of the program
	 * 
	 */
	public static void start(String fileName)
	{
		// Use ints to set the size of the drawing scale
		int xMaxScale = 0;
		int yMaxScale = 0;
		int xMinScale = 0;
		int yMinScale = 0;

		// create a new file object
		file = new File(fileName);

		try
		{
			// create a new scanner for scanning files
			Scanner fileScanner = new Scanner(file);

			// read in the number of points
			int num = fileScanner.nextInt();

			// create a count so that the number of points is only printed
			// once
			int count = 0;

			for (int i = 0; i < num; i++)
			{
				// read in the x and y coordinates of the points from the
				// file
				int xCoord = fileScanner.nextInt();
				int yCoord = fileScanner.nextInt();

				// find the max and min values to set drawing scale
				if (xCoord > xMaxScale)
					xMaxScale = xCoord;
				if (yCoord > yMaxScale)
					yMaxScale = yCoord;
				if (xCoord < xMinScale)
					xMinScale = xCoord;
				if (yCoord < yMinScale)
					yMinScale = yCoord;

				// print out the number of coordinates once
				while (count == 0)
				{
					System.out.print(num + "\n");
					count = 1;
				}

				System.out.print(xCoord + " : ");
				System.out.print(yCoord);
				System.out.print("\n");

				Point point = new Point(xCoord, yCoord);
				// create a new point

				getPoints().add(point);
				// add the point to the ArrayList

			}
		} catch (FileNotFoundException e)
		{
			// create a new scanner object
			Scanner sc = new Scanner(System.in);

			System.out.print("No file with that name exists. \n");
			System.out
					.println(" Please enter the name of the file you would like to use:");

			String newName = sc.nextLine();

			start(newName);
			e.printStackTrace();
		}

		// set the scale of the x and y axis using the fields that have been set
		StdDraw.setXscale(xMinScale, xMaxScale);
		StdDraw.setYscale(yMinScale, yMaxScale);
	}

	/**
	 * getFile() - Getter for the imported file. Only used for JUnit testing
	 * 
	 * @return file - the imported file
	 */
	public static File getFile()
	{
		return file;
	}

	/**
	 * setFile() - Setter for the imported file. Only used for JUnit testing
	 * 
	 * @param file
	 *            - the imported file
	 */
	public static void setFile(File file)
	{
		LineDetector.file = file;
	}

	/**
	 * getLines() - Getter for the lines ArrayList. Only used for JUnit Testing
	 * 
	 * @return lines - The lines ArrayList
	 */
	public static ArrayList<Line> getLines()
	{
		return lines;
	}

	/**
	 * setLines() - Setter for the lines ArrayList. Only used for JUnit Testing
	 * 
	 * @param lines
	 *            - The lines ArrayList
	 */
	public static void setLines(ArrayList<Line> lines)
	{
		LineDetector.lines = lines;
	}

	/**
	 * getPoints() - Getter for the points ArrayList
	 * 
	 * @return points - The points ArrayList
	 */
	public static ArrayList<Point> getPoints()
	{
		return points;
	}

	/**
	 * setPoints() - Setter for the points ArrayList
	 * 
	 * @return points - The points ArrayList
	 */
	public static void setPoints(ArrayList<Point> points)
	{
		LineDetector.points = points;
	}

	/**
	 * detectLines() - This method takes a point as the origin. The rest of the
	 * points are placed into a compare points ArrayList where they are sorted
	 * in the order of the slope that they make with the origin. The points are
	 * sorted using a Selection Sort that has been implemented specifically to
	 * sort the comparePoints ArrayList with respect to the origin point
	 * 
	 * 
	 */
	public static void detectLines()
	{
		for (int i = 0; i < getPoints().size(); i++)
		{
			Point origin = getPoints().get(i);

			// populate the camparePoints ArrayList with the points
			for (int k = 0; k < getPoints().size(); k++)
			{
				Point compare = getPoints().get(k);

				comparePoints.add(compare);
			}

			// remove the origin from the comparePoints ArrayList
			comparePoints.remove(origin);

			// pass the origin and the comparePoints list into sortBySlope()
			sortBySlope(origin, comparePoints);

			// System.out.print(" Sorted by slope: " + comparePoints + "\n");

			// call the creatLines method
			createLines(origin);

		}
	}

	/**
	 * createLines() - This method takes in the origin point as a parameter. It
	 * then uses a counter to check if 3 points in a row have the same slope
	 * with respect to the origin. If that happens a Line object with an
	 * ArrayList of is created and the points are passed into the ArrayList. If
	 * any more points have the same slope they are added to the ArrayList. The
	 * line is printed to screen and the stdDraw method is used to draw the
	 * line.
	 * 
	 * @param origin
	 *            - The point that is chosen as the origin. The lines will be
	 *            checked and calculated from this point
	 */
	public static void createLines(Point origin)
	{
		// instantiate two counters that will be used to create the line
		int counter = 0;
		int counterTwo = 0;

		for (int i = 0; i < comparePoints.size(); i++)
		{
			// assign the firstPoint
			Point firstPoint = comparePoints.get(i);
			Point secondPoint = null;

			// a test to ensure that secondPoint will not go out of bounds
			// if it reaches the end of the list, break is used to jump out of
			// the loop
			if (i + 1 >= comparePoints.size())
			{
				break;
			}
			else
			{
				secondPoint = comparePoints.get(i + 1);
			}

			// increment the counter if the firstPoint and secondPoint share the
			// same slope with the origin
			if (origin.slopeTo(firstPoint) == origin.slopeTo(secondPoint))
			{
				counter++;
			}
			// if the slopes are not the some reset the counter
			else if (origin.slopeTo(firstPoint) != origin.slopeTo(secondPoint))
			{
				counter = 0;
			}

			// if the counter reaches 2, that means 3 points have the same slope
			// with the origin so a line is created using those points and the
			// origin
			if (counter == 2)
			{
				// create a temporary ArrayList and add the origin before any
				// other point gets added
				ArrayList<Point> temp = new ArrayList<Point>();
				temp.add(origin);

				// goes back 3 places in the comparePoints ArrayList and adds
				// the next 3 points to the temporary list
				for (int j = comparePoints.indexOf(secondPoint) - 2; j < comparePoints
						.size(); j++)
				{
					// add the first 3 points to the temporary list
					// use a counter to ensure this happens only 3 times
					if (counterTwo < 3)
					{
						Point linePoint = comparePoints.get(j);
						temp.add(linePoint);
						slope = origin.slopeTo(linePoint);
					}
					// if there is any more points with the same slope add them
					else if (slope == origin.slopeTo(comparePoints.get(j)))
					{
						temp.add(comparePoints.get(j));
					}
					counterTwo++;
				}

				// reset counterTwo in case the origin point has another line
				// segment
				counterTwo = 0;

				// sort the temporary list by y coordinates
				sortByYCoordinates(temp);

				// call to the detectDuplicateLines() method. It will return
				// true if there is any duplicates.
				if (detectDuplicateLines(temp))
				{
					// do nothing
				}
				// if there is no duplicates. create line and draw
				else
				{
					// Create a new line
					Line line = new Line();
					// add the temporary ArrayList to the line
					line.setPoints(temp);

					lines.add(line);
					// pass the line into the drawLines method
					drawLines(line);

					System.out.print("\n Line: " + line.points.size() + " : "
							+ line.toString() + " Origin: " + origin + "\n");
				}
			}
		}

		// clear the comparePoints ArrayList before the next iteration
		comparePoints.clear();
	}

	/**
	 * drawLines() - This point will draw the lines that this program is
	 * designed to detect. It uses the StdDraw class to set the scale of the
	 * window that will appear and draws any lines that are created.
	 * 
	 * @param origin
	 *            - The point that is chosen as the origin. The lines will be
	 *            checked and calculated from this point
	 * 
	 * @param line
	 *            - The line that is created from the origin during the running
	 *            of this program
	 */
	public static void drawLines(Line line)
	{
		// set the radius of the pen
		StdDraw.setPenRadius(0.005);

		// get the last point in the line objects point ArrayList
		Point drawTo = line.getPoint(line.points.size() - 1);
		// get the first point
		Point firstPoint = line.points.get(0);

		// if all the points y values are the same, sort the line
		// by x values to ensure it fully drawn.
		if (firstPoint.getY() == drawTo.getY())
		{
			// sort the line by x values
			sortByXCoordinates(line.points);
			// get the last point in the line objects point ArrayList
			Point drawToX = line.getPoint(line.points.size() - 1);
			// draw the line
			Point firstPointX = line.points.get(0);
			// draw the line
			firstPointX.drawTo(drawToX);
		}
		else
		{
			// draw the line
			firstPoint.drawTo(drawTo);
		}
	}

	/**
	 * sortByYCoordinates() - This method is an implementation of insertion sort
	 * designed to sort the Line objects ArrayList of points by their y
	 * coordinates. It takes in the ArrayList as a parameter and sorts it
	 * accordingly
	 * 
	 * @param line
	 *            - The Line objects ArrayList of points
	 * 
	 */
	public static void sortByYCoordinates(ArrayList<Point> line)
	{

		int size = line.size();

		for (int i = 0; i < size; i++)
		{
			// get the last two points in the list and iterate backwards
			// through the list
			for (int j = i; j > 0; j--)
			{
				Point firstPoint = line.get(j);
				Point secondPoint = line.get(j - 1);
				// if the first points compareTo method returns -1
				if (firstPoint.compareTo(secondPoint) < 0)
				{
					// call the exch method
					exch(line, j, j - 1);
				}
				// break out of for loop. This is to improve efficiency
				else
					break;
			}
		}
	}

	/**
	 * sortByXCoordinates() - This method is an implementation of insertion sort
	 * designed to sort the Line objects ArrayList of points by their x
	 * coordinates. It will only be used if a line is found and all the y values
	 * are the same. It ensures that the full line is drawn
	 * 
	 * @param line
	 *            - The Line objects ArrayList of points
	 * 
	 */
	public static void sortByXCoordinates(ArrayList<Point> line)
	{
		for (int i = 0; i < line.size(); i++)
		{
			// get the last two points in the list and iterate backwards
			// through the list
			for (int j = i; j > 0; j--)
			{
				Point firstPoint = line.get(j);
				Point secondPoint = line.get(j - 1);
				// if the first points x is less than the second points x
				if (firstPoint.getX() < secondPoint.getX())
				{
					// call the exch method
					exch(line, j, j - 1);
				}
				// break out of for loop. This is a test to improve efficiency
				else
					break;
			}
		}
	}

	/**
	 * exch() - This a private method that is used to sort two elements in an
	 * ArrayList of points. It is only called from inside the sortByYCoordinates
	 * and sortByXCoordinates method.
	 * 
	 * @param line
	 *            - The Line objects ArrayList of points
	 * @param i
	 *            - The index of the first element to be swapped
	 * @param j
	 *            - The index of the second element to be swapped
	 */
	private static void exch(ArrayList<Point> line, int i, int j)
	{
		Point swap = line.get(i);
		line.set(i, line.get(j));
		line.set(j, swap);

	}

	/**
	 * sortBySlope() - This method is an implementation of Insertion sort that
	 * is designed to sort the comparePoints ArrayList by the slope that each of
	 * the points makes with the origin. It uses the SLOPE_OREDER comparator
	 * from the Point class to sort the slopes.
	 * 
	 * @param origin
	 *            - The origin point.
	 */
	public static void sortBySlope(Point origin, ArrayList<Point> compare)
	{
		for (int i = 0; i < compare.size(); i++)
		{
			// get the last two points in the list and iterate backwards
			// through the list
			for (int j = i; j > 0; j--)
			{
				Point firstPoint = compare.get(j);
				Point secondPoint = compare.get(j - 1);
				// if the SLOPE_ORDER comparator returns 1
				if (origin.SLOPE_ORDER.compare(firstPoint, secondPoint) > 0)
				{
					// call the exch method
					exchSlope(compare, j, j - 1);
				}
				// break out of for loop. This is a test to improve efficiency
				else
					break;
			}
		}
	}

	/**
	 * exchSlope() - This method is used to sort two elements in an ArrayList of
	 * points. It is only called from inside the sortBySlope method
	 * 
	 * @param i
	 *            - The index of the first element to be swapped
	 * @param j
	 *            - The index of the second element to be swapped
	 */
	public static void exchSlope(ArrayList<Point> compare, int i, int j)
	{
		Point swap = compare.get(i);
		compare.set(i, compare.get(j));
		compare.set(j, swap);

	}

	/**
	 * detectDuplicateLines() - This method is designed to detect any duplicate
	 * lines that the program will detect. It iterates through the lines
	 * ArrayList and compares the current line with the lines already stored. If
	 * a duplicate line is found this method will return true, otherwise it will
	 * return false
	 * 
	 * @param currentline
	 *            - the line that will be checked against
	 * 
	 * @return boolean
	 */
	public static boolean detectDuplicateLines(ArrayList<Point> currentLine)
	{
		for (int i = 0; i < lines.size(); i++)
		{
			// get a line to compare to the current line from the list of lines
			ArrayList<Point> compare = lines.get(i).points;

			// if the lines are equal return true
			if (compare.equals(currentLine))
			{
				return true;
			}
		}
		return false;
	}

}
