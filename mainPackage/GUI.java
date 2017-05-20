package mainPackage;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.Survey;

public final class GUI extends JFrame implements ActionListener, KeyListener {
	/*		--------VARIABLES--------		*/
	private static final long serialVersionUID = 1L;
	public File source;
	public int[] questionAns;	//Store the responses to the questions.  
	public static int questionCount;
	//fucking terrible variable naming. I know.
	public static Point pq1;		//Option bound 1
	public static Point pq2;		//Option bound 2
	public static Point po1;		//Option bound 1
	public static Point po2;		//Option bound 2
	ImageAreaSelector a = null;		//Define ImageAreaSelector early so its scope reaches all functions, same for num
	NumberChooser num = new NumberChooser();
	/*		--------GUI ITEMS--------		*/
	JPanel imgDisplayPane;
	JPanel consoleDisplayPane;
	JMenuBar topMenu;
	JMenu file;
	JMenu edit;
	JMenu view;
	JMenu actions;
	JMenuItem open;
	JMenuItem run;
	JMenuItem newRun;
	JMenuItem export;
	JLabel statusLabel;
	ImageIcon surveyImage;
	JLabel imageLabel;
	Container pane;
	JMenuItem chooseQHeight;
	JMenuItem chooseAHeight;
	JMenuItem displayBounds;
	JTree survey;
	DefaultMutableTreeNode question;
	JMenuItem setQuestionCount;
	JTabbedPane centerPane;
	JTextArea consoleTextBox;
	JTextField consoleInput;
	
	public static void main(String[] args) throws IOException
	{
		new GUI();     
	}
	/**
	 * The main GUI of the program
	 * @throws IOException
	 */
	public GUI() throws IOException
	{
		setTitle("AutoEval");
       	setSize(1000, 750);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
       	pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		imgDisplayPane = new JPanel();
		consoleDisplayPane = new JPanel();
		centerPane = new JTabbedPane();
		
		//Menu Bar stuff.  Holy shit.
		topMenu = new JMenuBar();
		open = new JMenuItem("Open");
		open.addActionListener(this);
		actions = new JMenu("Actions");
		run = new JMenuItem("Parse Form (Pixel Count)");
		run.addActionListener(this);
		newRun = new JMenuItem("Parse Form(Visual)");
		newRun.addActionListener(this);
		chooseQHeight = new JMenuItem("Question Height");
		chooseQHeight.addActionListener(this);
		displayBounds = new JMenuItem("Display Bounds");
		displayBounds.addActionListener(this);
		setQuestionCount = new JMenuItem("# of questions...");
		setQuestionCount.addActionListener(this);
		export = new JMenuItem("Export...");
		export.addActionListener(this);
		actions.add(run);
		actions.add(newRun);
		file = new JMenu("File");
		file.add(open);
		file.add(export);
		view = new JMenu("View");
		edit = new JMenu("Image");
		edit.add(chooseQHeight);
		edit.add(setQuestionCount);
		topMenu.add(file);
		topMenu.add(edit);
		topMenu.add(actions);
		topMenu.add(view);
		pane.add(topMenu, BorderLayout.NORTH);
		
		//Console
		consoleTextBox = new JTextArea();
		consoleDisplayPane.setLayout(new BorderLayout());
		consoleInput = new JTextField();
		consoleInput.addKeyListener(this);
		consoleDisplayPane.add(consoleInput, BorderLayout.SOUTH);
		consoleDisplayPane.add(consoleTextBox, BorderLayout.CENTER);
		
		//Tabbed pane stuff
		centerPane.add("Image", imgDisplayPane);
		centerPane.add("Console", consoleDisplayPane);
		pane.add(centerPane, BorderLayout.CENTER);
		
		
		//Response tree
		question = new DefaultMutableTreeNode("Questions");
		survey = new JTree(question);
		pane.add(survey, BorderLayout.WEST);
		
		
		
		//Status Bar
		statusLabel = new JLabel();
		statusLabel.setText("Done.");
		pane.add(statusLabel, BorderLayout.SOUTH);
		
		
		setVisible(true);		//Display the form
	}
	
	//Handles 
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		//Read menu bar inputs
		if(e.getSource() == open){
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(this);
			if(i == JFileChooser.APPROVE_OPTION)
			{
				source = fc.getSelectedFile();
				System.out.println(source.getPath());
				
				try {
					a = new ImageAreaSelector(source);
				} catch (IOException e1) {
					consoleLog(e1.getLocalizedMessage());
				}
			}
			
			try{	//Remove any existing images from the display
				remove(imageLabel);
			} catch(Exception ex) { }
			
			//Image display
			BufferedImage img;
			
			//Attempt to display the image, catches IO exception
			try{
			img = ImageIO.read(source);
			surveyImage = new ImageIcon(img);
			imageLabel = new JLabel(surveyImage);
			imageLabel.setSize(100, 100);
			imgDisplayPane.add(imageLabel);
			} catch (IOException ex) {		//Handle IOException
				consoleLog(ex.getMessage());
				setStatus("Error. See stack trace.");
			}
			setVisible(false);
			setVisible(true);
			setStatus("Done.");
		}
		else if(e.getSource() == run)		//Start a pixel count read, not finished
		{
			//Instantiate the ImageAreaSelector class earlier, need to use it to get the point data
			System.out.println("Started pixel count parse");
			System.out.println("PREINSTANCOUNT: " + num.getValue());
			@SuppressWarnings("unused")
			Survey s = new Survey(source, a.getBound1().x, 
					a.getBound2().y, 
					a.getBound2().y - a.getBound1().y, 
					a.getBound2().x - a.getBound1().x,
					num.getValue()
			);
			a.destroy();
			num.destroy();
		}
		else if(e.getSource() == newRun)		//Start a visual comparison read, not finished
		{
			System.out.println("Started visual parse");
		}
		else if(e.getSource() == export)		//export the created data to a variety of formats
		{
			System.out.println("Export menu selected");
		}
		else if(e.getSource() == chooseQHeight)		//Choose question height
		{
			try {
				a.displayForm();
				a.displaySelector();
				a.reloadVis();
			} catch (Exception e1) {
				setStatus("Error.  See stack trace.");
				consoleLog(e1.getMessage());
			}
		}
		else if(e.getSource() == setQuestionCount)
		{
			num.displayForm();
			num.createInputField();
			num.reloadVis();
		}
		
		
	} 
	
	public void setStatus(String s)		//update the status bar
	{
		System.out.println(s);
		consoleLog(s);
		statusLabel.setText(s);
		statusLabel.setVisible(false);
		statusLabel.setVisible(true);
	}
	public void consoleLog(String s)
	{
		consoleTextBox.append("\n" + s);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
