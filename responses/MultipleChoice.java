package responses;

import java.awt.image.BufferedImage;

public class MultipleChoice extends AbstractResponse {
	private int optionCount;
	BufferedImage questionData;
	private int threshhold = 0;
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
		blackCount = new int[optionCount];
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
		blackCount = new int[optionCount];
	}

	/**
	 * Function to get the chosen response from a question
	 * @return the chosen response, from 1 - optionCount.  Returns 0 if no response can be chosen
	 */
	public int getResponse() {
		for(int q = 0; q < optionCount; q++)		//Cycle through each response
			for(int i = (questionData.getHeight() / 5) * q; i < (questionData.getHeight() / 5) * (q + 1); i++)		//Cycles through each of the question zones
				for(int j = 0; j < questionData.getWidth(); j++)		//Cycles through the rows of the image
					if(questionData.getRGB(i, j) < 0x00100)		//Gets the RGB value of the pixel of the image, will need to tinker with it.  
						blackCount[q]++;			//If the pixel is selected, the blackCount of the question is increased
		
		int chosenResponse = 0;		//The response that is decided 
		for(int i = 0; i < blackCount.length; i++)
		{
			if(blackCount[i] > blackCount[i - 1])		//This will throw an exception, arrayOutOfBounds
				chosenResponse = i + 1;
			System.out.println(i);		//debug, remove this
		}
		if(chosenResponse < threshhold)		//For those who used a threshhold, check if it is valid or something
			return(0);
		return(chosenResponse);		//Return the response
	}
}