package mainPackage;

import java.awt.image.BufferedImage;

public class Question {
	private int optionHeight;
	BufferedImage questionData;
	
	public Question(BufferedImage b, int r)
	{
		questionData = b;
		optionHeight = r;
		
	}
	
	public int getResponse(int qNum)	
	{
		return((int) Math.random() * 5);		//for now it will just do this, for testing purposes
	}
}