package cf.rachlinski.autoEval.util;

import java.awt.Point;
import java.util.ArrayList;

import cf.rachlinski.autoEval.popupMenus.AreaType;

/**
 * A collection of points and question types to make points and areas easier to deal with
 * @author Christopher
 *
 */
public class QuestionBoundList {
	@SuppressWarnings("unused")
	private int size; 		//The size of the arraylists
	private ArrayList<Point> bound1, bound2;		//The lists of question bounds
	private ArrayList<AreaType> types;		//The list of area types
	
	/**
	 * @param size The size of the arrayLists
	 */
	public QuestionBoundList(int size)
	{
		this.size = size;
		this.bound1 = new ArrayList<Point>(size);
		this.bound2 = new ArrayList<Point>(size);
		this.types = new ArrayList<AreaType>(size);
	}
	
	/*------------------ Management Methods ------------- */
	
	/**
	 * Add a question area to the bound list
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
	 * Add a question area to the bound list
	 * @param point1 The first bound of the rectangle
	 * @param point2 The second bound of the rectangle
	 * @param type The type of the area that is being added
	 */
	public void add(Point point1, Point point2, AreaType type)
	{
		bound1.add(point1);
		bound2.add(point2);
		types.add(type);
	}
	
	/**
	 * Add a question to the bound list, without adding an area type
	 * @param point1 the first point to add
	 * @param point2 the second point to add
	 */
	public void add(Point point1, Point point2)
	{
		bound1.add(point1);
		bound2.add(point2);
	}
	
	/**
	 * Add an AreaType to the bounds list, without adding an area
	 * @param type
	 */
	public void add(AreaType type)
	{
		types.add(type);
	}
	
	/**
	 * Add a list of types to the list of types
	 * @param typeList The type list to add
	 */
	public void addAllTypes(ArrayList<AreaType> typeList)
	{
		types.addAll(typeList);
	}
	
	/**
	 * Removes the desired element from all 3 arrays
	 * @param r The element to remove
	 */
	public void remove(int r)
	{
		bound1.remove(r);
		bound2.remove(r);
		types.remove(r);
	}
	
	
	

	/*------------- Get Methods ------------- */
	
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
	 * Get a point from the desired list
	 * @param list The list to select from
	 * @param point The point to get from the list
	 * @return The desired point from the list
	 */
	public Point getPointFromList(int list, int point)
	{
		return(getBoundList(list).get(point));
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
	
	/*---------- Utility Methods -------------*/
	
	/**
	 * Checks if the 
	 * @return whether all the arrays have the same number of elements
	 */
	public boolean checkIntegrity()
	{
		return((bound1.size() == bound2.size()) && (bound2.size() == types.size()));
	}
	
	/**
	 * Get the amount of questions
	 * @return The number of elements in the bound1 arrayList. Note: This does not check the integrity of the list
	 */
	public int size()
	{
		return(bound1.size());
	}
}
