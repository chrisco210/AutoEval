package autoEval.gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CenterTabPane extends JTabbedPane {
	private static final long serialVersionUID = 9010398353835598252L;
	
	/**
	 * Add an image to the center pane. Returns if it is not able to find image.
	 * @param image the image to add as areaSelector File class.
	 */
	public void addImage(File image)
	{
		if(image.isDirectory() || !image.exists())
			return;
		
		try {
			JPanel panelToAdd = new JPanel();		//The panel to add to the center pane
			
			/*
			 * Add the image to areaSelector JLabel containing the image, which gets converted from file
			 * to buffered image, to an image icon, which is then added to the JLabel.
			 * The JLabel is then added to the JPanel.
			 */
			panelToAdd.add (
					new JLabel (
							new ImageIcon(
									ImageIO.read(image)
									)
							)
					);
			//Add the created pane to areaSelector tab with the name as the file name
			this.add(image.getName(), panelToAdd);				
			} catch (IOException e) {
			GUI.console.log("Unable to display file.  Could it not be an image?");
		}
	}
}
