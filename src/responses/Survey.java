package responses;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Survey {
	private static File surveySrc;
	private int questionHeight;
	private int questionXCorner;
	private int questionYCorner;
	private int questionWidth;
	private int questionCount;
	
	/**
	 * The survey class is the base class for getting responses.  It contains the full
	 * image of the survey, and the location of the response area.
	 * @param file The Image that the survey will read the responses from
	 * @param questionXCorner The x coordinate of the base point for the question area
	 * @param questionYCorner The y coordinate of the base point for the question area
	 * @param questionHeight The height of the question area
	 * @param questionWidth The width of the question area
	 * @param questionCount The number of options
	 */
	public Survey(File f, int qX, int qY,int qHeight, int qWidth, int num)
	{
		surveySrc = f; 		//Set the input file
		questionXCorner = qX;
		questionYCorner = qY;
		questionWidth = qWidth;
		questionHeight = qHeight;
		questionCount = num;
	}
	
	/**
	 * Alternative constructor for survey using points instead of coordinates and heights
	 * @param f The file to use
	 * @param p1 The first point on the image
	 * @param p2 The second point on the image
	 * @param num The number of questions
	 */
	public Survey(File f, Point p1, Point p2, int num)
	{
		surveySrc = f;
		questionXCorner = p1.x;
		questionYCorner = p1.y;
		questionWidth = p2.x - p1.x;
		questionHeight = p2.y - p1.y;
		questionCount = num;
	}
	
	/**
	 * Gets the response to the specified question number
	 * @param q is the question number to get the response to.  Not yet implemented
	 * @return the most likely response, 
	 * @throws IOException 
	 */
	public int getResponse() throws IOException
	{
		MultipleChoice q = new MultipleChoice(ImageIO.read(surveySrc).getSubimage(questionXCorner, questionYCorner, questionWidth, questionHeight), questionCount);
		return(q.getResponse());
	}
	
	/**
	 * Visual method of reading the survey.  Not yet implemented
	 * @return The selected response, as an integer
	 */
	public int getVisual()
	{
		return(0);
	}
	
	/**
	 * Checks if the survey to read is valid
	 * @return If the survey is valid
	 */
	@SuppressWarnings("unused")
	private boolean checkValid()
	{
		return(!(surveySrc.exists() || surveySrc.canRead()));
	}
}
