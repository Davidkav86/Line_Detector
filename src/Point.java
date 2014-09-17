import java.util.Comparator;

/**
 * This Class is for a Point object. The point object will have a X and a Y coordinate.
 * This class is used to create point objects. It is also used to sort point objects
 * by their Y coordinates and to draw from this.point to another point.
 * 
 * @author David Kavanagh
 *
 */
public class Point implements Comparable<Point>
{

	// Initialise inner class comparator for point objects
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder(); 

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y)
	{
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw()
	{
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that)
	{
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that)
	{
		double slope = 0;
		double positive = Double.POSITIVE_INFINITY;
		double negative = Double.NEGATIVE_INFINITY;
		double positiveZero = +0.0;
		double accurate = 1.00;
		
		if (that.x - this.x != 0)
		{
			slope = ((that.y - this.y)*accurate / (that.x - this.x)*accurate);
			// equation for slope
		}

		if (this.x == that.x || this.x - that.x == 0)
			return positive;
		if (this.y == that.y)
			return positiveZero;
		if (this.x == that.x && this.y == that.y)
			return negative;
		else
			return slope;
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that)
	{
		if (this.y < that.y)
			return -1;
		if (this.y == that.y)
			return 0;
		if (this.y > that.y)
			return +1;

		return 0;
	}

	// return string representation of this point
	public String toString()
	{
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args)
	{

	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	/**
	 * SlopeOrder - An inner class that implements a point Comparator. This
	 * Class is used to sort an ArrayList of Points by their slope.
	 * 
	 * @author David Kavanagh
	 *
	 */
	public class SlopeOrder implements Comparator<Point>
	{
		
		@Override
		public int compare(Point p, Point q)
		{
			double comparePSlope = Point.this.slopeTo(p);
			double compareQSlope = Point.this.slopeTo(q);
			if (comparePSlope < compareQSlope)
				return -1;
			if (comparePSlope == compareQSlope)
				return 0;
			if (comparePSlope > compareQSlope)
				return 1;
			return 0;

		}
		

	}
}