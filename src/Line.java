import java.util.ArrayList;

/**
 * This class is designed to hold a list of points. It is used inside the
 * LineDetector class to help simplify the operation of detecting lines.
 * 
 * @author David Kavanagh
 * 
 */
public class Line
{

	ArrayList<Point> points = new ArrayList<Point>();

	/**
	 * getPoints() - Getter for the points ArrayList
	 * 
	 * @return points - The points ArrayList
	 */
	public ArrayList<Point> getPoints()
	{
		return points;
	}

	/**
	 * setPoints() - Setter for the points ArrayList
	 * 
	 * @param points
	 *            - The points ArrayList
	 */
	public void setPoints(ArrayList<Point> points)
	{
		this.points = points;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "(" + points + ")";
	}

	/**
	 * getPoint() - This method allows for a specific point to be retrieved from
	 * the points ArrayList. It takes in an int as a parameter and returns the
	 * point at the index of that int
	 * 
	 * @param i
	 *            - an int that represents the index in the points ArrayList
	 * @return point 
	 *               - The point at the specified index
	 */
	public Point getPoint(int i)
	{
		Point point = points.get(i);
		return point;
	}

}
