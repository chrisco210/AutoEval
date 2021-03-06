package cf.rachlinski.autoEval.responses;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MultipleChoice extends AbstractResponse {
	private int optionCount;
	BufferedImage questionData;
	private int threshhold = 127;
	private int[] blackCount;
	
	/**
	 * Multiple choice question constructor without threshhold
	 * @param b a BufferedImage of the question area
	 * @param r the amount of questions
	 */
	public MultipleChoice(BufferedImage b, int r)
	{
		questionData = b;
		optionCount = r;
		blackCount = new int[optionCount + 1];
	}
	/**
	 * Multiple choice question constructor with threshhold value
	 * @param b a BufferedImage of the question area
	 * @param r the amount of questions
	 * @param t minimum amount of dark pixels to accept it as a valid response
	 */
	public MultipleChoice(BufferedImage b, int r, int t)
	{
		questionData = b;
		optionCount = r;
		threshhold = t;
		blackCount = new int[optionCount + 1];
	}

	/**
	 * Function to get the chosen response from a question
	 * @return the chosen response, from 1 - optionCount.  Returns 0 if no response can be chosen
	 */
	public int getResponse() {
		/* Diagram
		 * Where each of the options is is determined by the height.
		 * Which question we are scanning is determined by the outer for loop, using integer q
		 * Because it is determined by height, and we are assuming that each of the options is of equal size (Subject to change), 
		 * we can easily determine where the option area is.  See diagram for more detail
		 * Width is scanned using integer j
		 * +----------+
		 * |..........| 
		 * |..........| #Assume q = 1
		 * +----------+ this height would be determined by (Question Height / Number of options) * q
		 * |          | This area in between the heights would be scanned 
		 * |          |  
		 * +----------+ This height would be determined by (Question Height / Number of options) * (q + 1)
		 * |          |  
		 * |          |
		 * +----------+
		 * |          |
		 * |          |
		 * +----------+

		 */
		for(int q = 0; q < optionCount; q++)		//Cycle through each possible response area
		{
			for(int i = (questionData.getHeight() / optionCount) * q; i < (questionData.getHeight() / optionCount) * (q + 1); i++)		//Cycles through each of the question zones
			{
				for(int j = 0; j < questionData.getWidth(); j++)		//Cycles through the rows of the image
				{
					Color c = new Color(questionData.getRGB(j, i));
					if((c.getRed() < threshhold) && (c.getBlue() < threshhold) && (c.getGreen() < threshhold))		//Gets the RGB value of the pixel of the image, will need to tinker with it.  
						blackCount[q + 1]++;			//If the pixel is selected, the blackCount of the question is increased
				}
			}
		}
		
		int chosenResponse = 0;		//The response that is decided 
		int maxNum = blackCount[0];
		for(int i = 1; i < blackCount.length; i++)
		{
			if(blackCount[i] > maxNum)
			{
				maxNum = blackCount[i];
				chosenResponse = i;
			}
		}

		return(chosenResponse);		//Return the response
	}
}
