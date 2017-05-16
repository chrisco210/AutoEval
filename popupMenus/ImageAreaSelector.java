package popupMenus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ImageAreaSelector extends AreaSelector {
	private BufferedImage imgBuff;
	JLabel displayImg;
	JScrollPane imgPane;
	
	public ImageAreaSelector(File f) throws IOException
	{
		imgBuff = ImageIO.read(f);
		displayImg = new JLabel(new ImageIcon(imgBuff));
	}
	
	public void displaySelector() 
	{
		imgPane = new JScrollPane(displayImg);
		super.pane.add(imgPane);		//Create a jscrollpane for the image
		//imgPane.addMouseListener();
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse detected");
		System.out.println(e.getX());
		
	}
}