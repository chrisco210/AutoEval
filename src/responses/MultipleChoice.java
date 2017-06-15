package responses;

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
		for(int q = 0; q < optionCount; q++)		//Cycle through each response
		{
			for(int i = (questionData.getHeight() / optionCount) * q; i < (questionData.getHeight() / optionCount) * (q + 1); i++)		//Cycles through each of the question zones
			{
				for(int j = 0; j < questionData.getWidth(); j++)		//Cycles through the rows of the image
				{
					Color c = new Color(questionData.getRGB(j, i));
					System.out.println("Red: " + c.getRed() + "Blue: " + c.getBlue() + "Green: " + c.getGreen());
					if((c.getRed() < threshhold) && (c.getBlue() < threshhold) && (c.getGreen() < threshhold))		//Gets the RGB value of the pixel of the image, will need to tinker with it.  
						blackCount[q + 1]++;			//If the pixel is selected, the blackCount of the question is increased
					System.out.println("(" + j + "," + i + ")" + blackCount[q]);		//debug, to be removed
				}
			}
		}
		
		int chosenResponse = 0;		//The response that is decided 
		int maxNum = blackCount[0];
		System.out.println("----------------------------------------------------");
		for(int i = 1; i < blackCount.length; i++)
		{
			System.out.println(i);
			System.out.println(blackCount[i] + " Pixels \n -------------");
			if(blackCount[i] > maxNum)
			{
				maxNum = blackCount[i];
				chosenResponse = i;
			}
		}
		//This needs to be fixed, it will not work
		//if(1 == 2)		//For those who used a threshhold, check if it is valid or something
		//	return(0);
		return(chosenResponse);		//Return the response
	}
}
