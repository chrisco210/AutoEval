package cf.rachlinski.autoEval.popupMenus;

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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.util.QuestionBoundList;
import cf.rachlinski.autoEval.gui.GUI;

public class ImageAreaSelector extends AbstractAreaSelector implements ActionListener, MouseListener, MouseMotionListener{

	private static final int BOUND_LIST_SIZE = 16;

	/*		--------Variables--------		*/
	private BufferedImage imgBuff;
	private Point tempBound1;
	private Point tempBound2;
	private QuestionBoundList boundList= new QuestionBoundList(ImageAreaSelector.BOUND_LIST_SIZE);
	private boolean isSelecting = false;
	
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
	JPanel imgSubPane;
	ImageIcon imgIco;
	
	private TypeSelector t = new TypeSelector();		//Instantiate early to expand visibility
	
	public QuestionBoundList getQuestionBoundList()
	{
		return(boundList);
	}
	
	/**
	 * Displays areaSelector form to select an area on an image
	 * @param f The image to diplay in the selector
	 * @throws IOException 
	 */
	public ImageAreaSelector(File f) throws IOException
	{
		imgBuff = ImageIO.read(f);		//Create buffered image from the given file
		imgIco = new ImageIcon(imgBuff);
		displayImg = new JLabel(imgIco);		//Put the buffered image onto areaSelector jlabel with image icon
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
		imgPane = new JScrollPane(displayImg);		//scrollpane for the image
		fullPane = new BorderLayout();
		pane.setLayout(fullPane);
		pane.add(imgPane, BorderLayout.CENTER);		//Create areaSelector jscrollpane for the image
		
		//Position labels, and text fields - point 1
		Pos1Label = new JLabel("Point 1");
		xPos1 = new JTextField("X Position");
		yPos1 = new JTextField("Y Position");
		
		//Position labels, and text fields - point 1
		Pos2Label = new JLabel("Point 2");
		xPos2 = new JTextField("X Position");
		yPos2 = new JTextField("Y Position");
		
		//apply areaSelector grid layout to them, and put them on the bottom.  For point 1
		gLay = new GridLayout(3,3);
		bottomPanel.setLayout(gLay);
		bottomPanel.add(Pos1Label);
		bottomPanel.add(xPos1);
		bottomPanel.add(yPos1);
		
		//apply areaSelector grid layout to them, and put them on the bottom.  For point 2
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
	
	
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if(eventSource == visualizeButton)
		{
			visualize();
		}
		else if(eventSource == okButton)
		{
			//setTypes();		//Set the types from the type selector
			boundList.addAllTypes(t.getAreaTypes());
			
			GUI.userHasSetImageArea = true;
			
			hide();
		}
	}
	
	private void visualize()
	{
		t.showFrame();		//Display the frame
		
		//Set the point bounds 
		
		boundList.add(
				new Point(Integer.parseInt(xPos1.getText()), Integer.parseInt(yPos1.getText())),
				new Point(Integer.parseInt(xPos2.getText()), Integer.parseInt(yPos2.getText()))
		);
		
		Graphics2D g2d = imgBuff.createGraphics();
		
		//Array of colors 
		final Color[] colors = {Color.red, Color.blue, Color.green, Color.orange, Color.black, Color.cyan, Color.yellow, Color.magenta, Color.pink, Color.gray};
		
		//Draw all the rectangles selected by the user
		for(int i = 0; i < boundList.size(); i++)
		{
			g2d.setColor(colors[i]);
			g2d.drawRect(boundList.getPointFromList(1, i).x, boundList.getPointFromList(1, i).y, boundList.getPointFromList(2, i).x, boundList.getPointFromList(2, i).y);
		}
		
		reloadVis();
		g2d.dispose();
	}



	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		
	}

	
	public void mouseMoved(MouseEvent arg0) 
	{	
	}
	public void mouseClicked(MouseEvent arg0) 
	{ 
		if(isSelecting)		//Finalize second point and selection and draw rectangle when the selection is finished
		{
			Graphics2D g2d = imgBuff.createGraphics();	//Create graphics object
			
			//Get the real value of the click event, assuming that the frame is larger than the image
			int selectXValue = arg0.getX() - ((pane.getWidth() - imgBuff.getWidth()) / 2);
			int selectYValue = arg0.getY() - ((imgPane.getHeight() - imgBuff.getHeight()) / 2);
			
			//Make sure the selection is not less than 0
			if(selectXValue < 0)
				selectXValue = 0;
			if(selectYValue < 0)
				selectYValue = 0;
			
			//Make sure selection does not exceed bounds of the image
			if(selectXValue > imgBuff.getWidth())
				selectXValue = imgBuff.getWidth() - 1;
			if(selectYValue > imgBuff.getHeight())
				selectYValue = imgBuff.getHeight() - 1;
			
			//Get real value of tempBound1, assuming that the frame is bigger than the image TODO account for frame being smaller than image
			tempBound2 = new Point(
					selectXValue,
					selectYValue
			);		//Get temp bound from the selection point
			ConsolePane.dbg(tempBound2);
			
			//array of colors to create mutli color boxes
			Color[] colors = {Color.red, Color.blue, Color.green, Color.orange, Color.black, Color.cyan, Color.yellow, Color.magenta, Color.pink, Color.gray};
			
			//Draw rectangle
			ConsolePane.dbg("DRAWING RECTANGLE");
			g2d.setColor(colors[1]);
			g2d.drawRect(tempBound1.x, tempBound1.y, tempBound2.x - tempBound1.x, tempBound2.y - tempBound2.y);
			
			/*
			bound1.add(tempBound1);
			bound2.add(tempBound2);
			*/
			
			//Add bounds to the bound list.  
			boundList.add(tempBound1, tempBound2);
			
			//Clear the temp bounds
			tempBound1 = null;
			tempBound2 = null;
			
			//Reload visibility of the image pane
			imgPane.setVisible(false);
			imgPane.setVisible(true);
			
			//Prompt the user to choose areaSelector question type
			t.showFrame();
			
			isSelecting = false;
		}
		else		//Start selection, get the first temp bound
		{
			ConsolePane.dbg("Selecting first point.");

			//Get the real value of the click event, assuming that the frame is larger than the image
			int selectXValue = arg0.getX() - ((pane.getWidth() - imgBuff.getWidth()) / 2);
			int selectYValue = arg0.getY() - ((imgPane.getHeight() - imgBuff.getHeight()) / 2);
			
			if(selectXValue < 0)
				selectXValue = 0;
			if(selectYValue < 0)
				selectYValue = 0;
			
			//Make sure selection does not exceed bounds of the image
			if(selectXValue > imgBuff.getWidth())
				selectXValue = imgBuff.getWidth() - 1;
			if(selectYValue > imgBuff.getHeight())
				selectYValue = imgBuff.getHeight() - 1;
			
			//Get real value of tempBound1, assuming that the frame is bigger than the image
			tempBound1 = new Point(
					selectXValue,
					selectYValue
					);		//Get temp bound from the selection point
			
			ConsolePane.dbg(tempBound1);		//Print point on debug
			
			isSelecting = true;
		}
	}
	public void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
}