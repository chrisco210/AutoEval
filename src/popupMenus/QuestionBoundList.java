package popupMenus;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A collection of points and question types to make points and areas easier to deal with
 * @author Christopher
 *
 */
public class QuestionBoundList {
	/**
	 * The first point of the rectangle selection
	 */
	private ArrayList<Point> bound1 = new ArrayList<Point>(10);
	
	/**
	 * The second point of the rectangle selection
	 */
	private ArrayList<Point> bound2 = new ArrayList<Point>(10);
	
	/**
	 * Types of response
	 */
	private ArrayList<AreaType> types = new ArrayList<AreaType>(10);
	
	/**
	 * Gets the desired ArrayList of points
	 * @param b the bound number to get
	 * @return the requested bounds as an ArrayList of points
	 */
	
	public ArrayList<Point> getBoundList(int b)
	{
		return(b == 1 ? bound1 : bound2);
	}
	
	/**
	 * Get the area types
	 * @return An arraylist of AreaTypes corresponding to each point
	 */
	public ArrayList<AreaType> getTypes()
	{
		return(types);
	}
	
	/**
	 * Get the type of a specific area
	 * @param s The index of the type to get
	 * @return An AreaType of the requested index
	 */
	public AreaType getType(int s)
	{
		return(types.get(s));
	}
	
	/**
	 * 
	 * @param xPos1 The x value of the first point
	 * @param yPos1 The y value of the first point
	 * @param xPos2 The x value of the second point
	 * @param yPos2 The y value of the second point
	 * @param type The type of area that is being selected
	 */
	public void add(int xPos1, int yPos1, int xPos2, int yPos2, AreaType type)
	{
		bound1.add(new Point(xPos1, yPos1));
		bound2.add(new Point(xPos2, yPos2));
		types.add(type);
	}
	
	/**
	 * Removes the desired element from all 3 arrays
	 */
	public void remove(int r)
	{
		bound1.remove(r);
		bound2.remove(r);
		types.remove(r);
	}
	
	/**
	 * Get the amount of questions
	 * @return The number of elements in the bound1 arrayList. Note: This does not check the integrity of the list
	 */
	public int size()
	{
		return(bound1.size());
	}
	
	/**
	 * Checks if the 
	 * @return whether all the arrays have the same number of elements
	 */
	public boolean checkIntegrity()
	{
		return((bound1.size() == bound2.size()) && (bound2.size() == types.size()));
	}
}
