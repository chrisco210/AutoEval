package responses;

import java.io.File;

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
	 * @param questionCount The number of questions
	 */
	public Survey(File f, int qX, int qY,int qHeight, int qWidth, int num)
	{
		surveySrc = f; 		//Set the input file
		questionXCorner = qX;
		questionYCorner = qY;
		questionWidth = qWidth;
		questionHeight = qHeight;
		questionCount = num;
		System.out.println(surveySrc.toString());
		System.out.println("QuestionXCorner: " + questionXCorner);
		System.out.println("QuestionYCorner: " + questionYCorner);
		System.out.println("Question Width: " + questionWidth);
		System.out.println("Question height: " + questionHeight);
		System.out.println("Question Count: " + questionCount);
	}
	
	/**
	 * Visual method of reading the survey.  Not yet implemented
	 * @return The selected response, as an integer
	 */
	int getVisual()
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
		if(!surveySrc.exists())
			return(false);
		else if(!surveySrc.canRead())
			return(false);
		return(true);
	}
}
