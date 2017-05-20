package responses;

import java.awt.image.BufferedImage;

public class Question extends AbstractResponse {
	@SuppressWarnings("unused")
	private int optionHeight;
	BufferedImage questionData;
	
	public Question(BufferedImage b, int r)
	{
		questionData = b;
		optionHeight = r;
		
	}
}