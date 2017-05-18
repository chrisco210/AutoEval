package responses;

import java.awt.image.BufferedImage;
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
	
	//File, corner x coord, corner y coord, question height, question width
	public Survey(File f, int qX, int qY,int qHeight, int qWidth, int num)
	{
		surveySrc = f; 		//Set the input file
		questionXCorner = qX;
		questionYCorner = qY;
		questionWidth = qWidth;
		questionHeight = qHeight;
	}
	
	int getResponse(int qNum) throws IOException
	{
		return((int) Math.random()*5);
		
	}
	
	int getVisual()
	{
		return(0);
	}
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
