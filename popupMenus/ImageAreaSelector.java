package popupMenus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ImageAreaSelector extends AbstractAreaSelector implements ActionListener{
	/*		--------Variables--------		*/
	private BufferedImage imgBuff;
	private BufferedImage origImg;
	
	/*		--------GUI ELEMENTS--------		*/
	JLabel displayImg;
	JScrollPane imgPane;
	BorderLayout fullPane;
	JLabel Pos1Label;
	JTextField xPos1;
	JTextField yPos1;
	JTextField xPos2;
	JTextField yPos2;
	GridLayout gLay;
	JLabel Pos2Label;
	JPanel bottomPanel;
	JButton visualizeButton;
	JButton okButton;
	JLabel imageSizeLabel;
	
	
	
	//Integers to 
	/**
	 * The first point of the rectangle selection
	 */
	private Point bound1;
	/**
	 * The second point of the rectangle selection
	 */
	private Point bound2;
	
	/**
	 * Displays a form to select an area on an image
	 * @param f The image to diplay in the selector
	 * @throws IOException 
	 */
	public ImageAreaSelector(File f) throws IOException
	{
		imgBuff = ImageIO.read(f);
		displayImg = new JLabel(new ImageIcon(imgBuff));
		origImg = imgBuff;
		displayForm();
		displaySelector();
	}
	
	/**
	 * Displays the image and components to set points
	 */
	public void displaySelector() 
	{
		bottomPanel = new JPanel();
		imgPane = new JScrollPane(displayImg);
		fullPane = new BorderLayout();
		pane.setLayout(fullPane);
		pane.add(imgPane, BorderLayout.CENTER);		//Create a jscrollpane for the image
		
		//Position labels, and text fields - point 1
		Pos1Label = new JLabel("Point 1");
		xPos1 = new JTextField("X Position");
		yPos1 = new JTextField("Y Position");
		
		//Position labels, and text fields - point 1
		Pos2Label = new JLabel("Point 2");
		xPos2 = new JTextField("X Position");
		yPos2 = new JTextField("Y Position");
		
		//apply a grid layout to them, and put them on the bottom.  For point 1
		gLay = new GridLayout(3,3);
		bottomPanel.setLayout(gLay);
		bottomPanel.add(Pos1Label);
		bottomPanel.add(xPos1);
		bottomPanel.add(yPos1);
		
		//apply a grid layout to them, and put them on the bottom.  For point 2
		bottomPanel.add(Pos2Label);
		bottomPanel.add(xPos2);
		bottomPanel.add(yPos2);
		
		//Accept and visualize buttons
		visualizeButton = new JButton("Visualize");
		visualizeButton.addActionListener(this);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		imageSizeLabel = new JLabel("Size: "+imgBuff.getWidth() + "x" + imgBuff.getHeight());
		bottomPanel.add(imageSizeLabel);		//Filler for the extra grid space
		bottomPanel.add(visualizeButton);
		bottomPanel.add(okButton);
		
		//Add the bottom panel to the layout
		pane.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Gets the bounds of the area selected as a Point
	 * @param b the bound number to get
	 * @return the requested bound as a Point class
	 */
	public Point getBound(int b)
	{
		switch(b)
		{
		case 1:
			return(bound1);
		case 2:
			return(bound2);
		default:
			return(null);
		}
	}
	/**
	 * Set the Point class based on the data given
	 */
	private void setBound1()
	{
		bound1 = new Point(Integer.parseInt(xPos1.getText()), Integer.parseInt(yPos1.getText()));
	}
	/**
	 * Se the point class based on the data given
	 */
	private void setBound2()
	{
		bound2 = new Point(Integer.parseInt(xPos2.getText()), Integer.parseInt(yPos2.getText()));
	}
	
	//BIG PROBLEM: without pressing the visualize button, the values will not get set.
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if(eventSource == visualizeButton)
		{
			//Set the point bounds
			setBound1();
			setBound2();
			Graphics2D g2d = imgBuff.createGraphics();
			g2d.setColor(Color.red);
			g2d.drawRect(bound1.x, bound1.y, bound2.x - bound1.x, bound2.y - bound1.y);		//Draw the rectangle on the image
			reloadVis();
			g2d.dispose();
			imgBuff = origImg;
		}
		else if(eventSource == okButton)
		{
			System.out.println("OK Button Pressed");
			try{
				System.out.println(getBound(1).toString());
				System.out.println(getBound(2).toString());
				hide();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
