package popupMenus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import mainPackage.GUI;

public class ImageAreaSelector extends AbstractAreaSelector implements ActionListener, MouseListener, MouseMotionListener{
	/*		--------Variables--------		*/
	private BufferedImage imgBuff;
	private Point tempBound1;
	private Point tempBound2;
	
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
	
	private TypeSelector t = new TypeSelector();		//Instantiate early to expand visibility
	
	//------------------ ALL THIS IS DEPRECATED! TODO MIGRATE TO QUESTIONBOUNDLIST CLASS
	/**
	 * The first point of the rectangle selection
	 * @deprecated
	 */
	private ArrayList<Point> bound1 = new ArrayList<Point>(10);
	/**
	 * The second point of the rectangle selection
	 * @deprecated
	 */
	private ArrayList<Point> bound2 = new ArrayList<Point>(10);
	
	/**
	 * Types of response
	 * @deprecated
	 */
	private ArrayList<AreaType> types = new ArrayList<AreaType>(10);
	
	/**
	 * Add the selected point to the Point arraylists
	 * @deprecated
	 */
	private void setBounds()
	{
		bound1.add(new Point(Integer.parseInt(xPos1.getText()), Integer.parseInt(yPos1.getText())));
		bound2.add(new Point(Integer.parseInt(xPos2.getText()), Integer.parseInt(yPos2.getText())));
	}
	
	/**
	 * @deprecated
	 */
	private void setTypes()
	{
		types.clear();
		types.addAll(t.getAreaTypes());
	}
	/**
	 * Gets the desired ArrayList of points
	 * @param b the bound number to get
	 * @return the requested bounds as an ArrayList of points
	 * @deprecated
	 */
	public ArrayList<Point> getBoundList(int b)
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
	 * Get the type of a specific area
	 * @param s The index of the type to get
	 * @return An AreaType of the requested index
	 * @deprecated
	 */
	public AreaType getType(int s)
	{
		return(types.get(s));
	}
	/**
	 * Get the area types
	 * @return An arraylist of AreaTypes corresponding to each point
	 * @deprecated
	 */
	public ArrayList<AreaType> getTypes()
	{
		return(types);
	}
	//---------------------------------------------------------
	
	
	/**
	 * Displays a form to select an area on an image
	 * @param f The image to diplay in the selector
	 * @throws IOException 
	 */
	public ImageAreaSelector(File f) throws IOException
	{
		imgBuff = ImageIO.read(f);		//Create buffered image from the given file
		displayImg = new JLabel(new ImageIcon(imgBuff));		//Put the buffered image onto a jlabel with image icon
		displayForm();		//Display the form
		displaySelector();		//Add components
	}
	
	/** 
	 * Displays the image and components to set points
	 */
	public void displaySelector() 
	{
		//Add mouse listener to displayImg
		displayImg.addMouseListener(this); 
		displayImg.addMouseMotionListener(this);
		
		//Layout setup
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
	
	
	//TODO: Migrate to the QuestionBoundList class
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if(eventSource == visualizeButton)
		{
			visualize();
		}
		else if(eventSource == okButton)
		{
			setTypes();		//Set the types from the type selector
			hide();
		}
	}
	
	//TODO Remove this
	private void visualize()
	{
		t.showFrame();		//Display the frame
		
		//Set the point bounds and types
		setBounds();
		
		Graphics2D g2d = imgBuff.createGraphics();
		
		//Array of colors 
		Color[] colors = {Color.red, Color.blue, Color.green, Color.orange, Color.black, Color.cyan, Color.yellow, Color.magenta, Color.pink, Color.gray};
		
		//Draw all the rectangles selected by the user
		for(int i = 0; i < bound1.size(); i++)
		{
			g2d.setColor(colors[i]);
			g2d.drawRect(bound1.get(i).x, bound1.get(i).y, bound2.get(i).x, bound2.get(i).y);
		}
		reloadVis();
		g2d.dispose();
	}



	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		tempBound1 = new Point(arg0.getX(), arg0.getY());		//Store the mouse press in the temp bound
		
		
		GUI.consoleLog(tempBound1.toString());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		Graphics2D g2d = imgBuff.createGraphics();
		
		tempBound2 = new Point(arg0.getX(), arg0.getY());
		GUI.consoleLog(tempBound2.toString());
		
		Color[] colors = {Color.red, Color.blue, Color.green, Color.orange, Color.black, Color.cyan, Color.yellow, Color.magenta, Color.pink, Color.gray};
		
		g2d.setColor(colors[1]);
		g2d.drawRect(tempBound1.x, tempBound1.y, tempBound2.x, tempBound2.y);
		
		 
		
		tempBound1 = null;
		tempBound2 = null;
		
		imgPane.setVisible(false);
		imgPane.setVisible(true);
		
		t.showFrame();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		tempBound2 = arg0.getPoint();
		
		Graphics2D g2d = imgBuff.createGraphics();
		g2d.setColor(Color.blue);
		g2d.drawRect(tempBound1.x, tempBound1.y, tempBound2.x, tempBound2.y);
		
		imgPane.setVisible(false);
		imgPane.setVisible(true);
	}

	
	//These functions are just here from interfaces
	public void mouseMoved(MouseEvent arg0) {	}
	public void mouseClicked(MouseEvent arg0) { }
	public void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
}