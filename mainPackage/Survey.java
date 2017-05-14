package mainPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Survey {
	private static File surveySrc;
	private int qHeight;
	private int optHeight;
	
	public Survey(File f, int qHeight, int optHeight)
	{
		this.qHeight = qHeight;
		this.optHeight = optHeight;
		surveySrc = f;
	}
	
	int getResponse(int qNum) throws IOException
	{
		BufferedImage b = ImageIO.read(surveySrc);
		BufferedImage qBuffer = b.getSubimage(0, 0, 0, 0);		//Change these values later
		Question q = new Question(qBuffer, qBuffer.getHeight() / 4);	//not sure what the height should be
		return(q.getResponse(qNum));
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
