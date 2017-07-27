package autoEval.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

import autoEval.ImageParser;
import popupMenus.About;
import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import popupMenus.StatSetup;
import responses.answers.Page;
import responses.answers.Question;
import export.ExportGUI;

/**
 * The main GUI
 * @author Christopher
 *
 */
public final class GUI extends JFrame {		//Only create one GUI.
	/*		--------VARIABLES--------		TODO move to new GUI/event Controller class*/
	private static final long serialVersionUID = 1L;
	public static ArrayList<File> source;
	public static ArrayList<Page> questionAns;	//Store the responses to the questions.  TODO fix the number of pages in the constructor
	public static int questionCount;
	public static ImageAreaSelector a = null;		//Define ImageAreaSelector early so its scope reaches all functions, same for num
	public static final NumberChooser num = new NumberChooser();
	
	/*		--------GUI ITEMS--------		*/
	private static MenuBar topMenu;		//Menu bar displayed on top of screen
	public static StatusBar statusLabel;		//Where the program status is displayed, bottom of screen
	private static CenterTabPane centerPane;		//Central tab pane
	
	//Image display
	public static ImageIcon surveyImage;
	public static JLabel imageLabel;
	
	public static Container pane;		//Main content pane

	public static ActionListener action;		//Action listener class
	
	public static ConsolePane console;		//Console
	
	//TODO Remove this or make it do something
	private static JTree survey;
	private static DefaultMutableTreeNode question;
	
	/**
	 * The main GUI of the program
	 * @throws IOException
	 */
	public GUI() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Setup frame properties
		setTitle("AutoEval");
       	setSize(1000, 750);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        action = new actionListener();
        
        //Create borderLayout
       	pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		//Create center tab pane
		centerPane = new CenterTabPane();
		
		//Create menu bar and add it to top of screen
		topMenu = new MenuBar();
		pane.add(topMenu, BorderLayout.NORTH);
		
		
		//Create console pane 
		console = new ConsolePane();
		
		
		//Tabbed pane stuff
		centerPane.add("Console", console);
		pane.add(centerPane, BorderLayout.CENTER);
		
		
		//Response tree
		question = new DefaultMutableTreeNode("Questions");
		survey = new JTree(question);
		pane.add(survey, BorderLayout.WEST);
		
		
		
		//Status Bar
		statusLabel = new StatusBar();
		statusLabel.setText("Done.");
		pane.add(statusLabel, BorderLayout.SOUTH);
		
		
		setVisible(true);		//Display the form
		setStatus("Done.");
	}
	
	
	

	/**
	 * @deprecated
	 */
	protected void loadFile()			//TODO Move to file controller
	{
		setStatus("Opening File");
		
		try{
			source.clear();
		} catch(Exception e1) {		}
		
		centerPane.removeAll();
		centerPane.add("Console", console);
		
		JFileChooser fc = new JFileChooser();
		int i = fc.showOpenDialog(this);
		if(i == JFileChooser.APPROVE_OPTION)
		{
			source = new ArrayList<File>(1);
			source.add(fc.getSelectedFile());
			consoleLog(source.toString());
			try {
				a = new ImageAreaSelector(source.get(0));		//A is the ImageAreaSelector class, sets the selected file. Instantiated here to allow it to display the image in the imageAreaSelector
			} catch (IOException e1) {
				GUI.consoleLog("Failed to create Image Area Selector.");
			}
		}
		else 
		{
			return;
		}
		
		try{	//Remove any existing images from the display
			remove(imageLabel);
		} catch(Exception ex) { 
			//This does not need to be handled
		}
		
		//Image to display
		BufferedImage img;
		
		//Attempt to display the image, catches IO exception
		for(File f : source)
		{
			try{
			JPanel j = new JPanel();
			img = ImageIO.read(f);
			surveyImage = new ImageIcon(img);
			imageLabel = new JLabel(surveyImage);
			imageLabel.setSize(100, 100);
			j.add(imageLabel);
			centerPane.add(f.toString(), j);
			} catch (IOException ex) {		//Handle IOException
				GUI.consoleLog("File " + f.toString() + " Failed to display.  Could it not be an image?");
			}
		}
		setVisible(false);
		setVisible(true);
		setStatus("Done.");
	}
	
	protected void loadFolder()		//TODO Move to file controller
	{
		setStatus("Opening File");
		try{
		source.clear();
		} catch (Exception e2) {
			
		}
		centerPane.removeAll();		//Clear all the tabs from the center pane
		centerPane.add("Console", console);		//re add the console
		
		File path = null;		//Prevent java from being stupid
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int i = fc.showOpenDialog(this);
		if(i == JFileChooser.APPROVE_OPTION)
		{
			path = fc.getSelectedFile();
		} 
		else 
		{
			return;
		}
		source = new ArrayList<File>(path.listFiles().length);
		source.addAll(Arrays.asList(path.listFiles()));
		try {
			a = new ImageAreaSelector(source.get(0));		//A is the ImageAreaSelector class, sets the selected file
		} catch (IOException e1) {
			GUI.consoleLog("Failed to create Image Area Selector.");
		}
		try{	//Remove any existing images from the display
			remove(imageLabel);
		} catch(Exception ex) { 
			
		}
		
		//Image display
		BufferedImage img;
		
		//Attempt to display the image, catches IO exception
		for(File f : path.listFiles())
		{
			
			try{
			JPanel j = new JPanel();
			img = ImageIO.read(f);
			surveyImage = new ImageIcon(img);
			imageLabel = new JLabel(surveyImage);
			imageLabel.setSize(100, 100);
			j.add(imageLabel);
			centerPane.add(f.toString(), j);
			} catch (IOException ex) {		//Handle IOException
				GUI.consoleLog("File " + f.toString() + " Failed to display.  Could it not be an image?");
			}
		}
		setVisible(false);
		setVisible(true);
		setStatus("Done.");
	}
	
	/**
	 * Log a message to the console
	 * @param s the string to display
	 * @deprecated
	 */
	public static void consoleLog(String s)
	{
		console.log(s);
	}
	/**
	 * Set the status bar, and logs it to the console
	 * @param s the string to display
	 * @deprecated
	 */
	public static void setStatus(String s)		//update the status bar
	{
		consoleLog(s);
		statusLabel.setText(s);
		statusLabel.setVisible(false);
		statusLabel.setVisible(true);
	}
	
	/**
	 * Subclass to handle Action Events from the menu bar
	 * @author Christopher
	 *
	 */
	@SuppressWarnings("rawtypes")
	class actionListener implements ActionListener		//TODO should this be a subclass?
	{
		public void actionPerformed(ActionEvent e)			//Clean up this function, maybe create functions for opening files and such
		{	
			Object eventSrc = e.getSource();
			//Read menu bar inputs
			if(eventSrc == topMenu.open){		//Single File Open 
				new Thread(() -> loadFile()).start();
			}
			else if(eventSrc == topMenu.openFolder){		//Single File Open 
				new Thread(() -> loadFolder()).start();
			}
			else if(eventSrc == topMenu.run)
			{
				setStatus("Parsing");
				ImageParser w = new ImageParser(a.getQuestionBoundList(), source, num, 0);
				w.start();
			}
			else if(eventSrc == topMenu.newRun)		//Start a visual comparison read, not finished, may never be
			{
			}
			else if(eventSrc == topMenu.export)		//export the created data to a variety of formats.
			{
				Runnable export = () -> {new ExportGUI();};
				new Thread(export).start();
			}
			else if(eventSrc == topMenu.chooseQHeight)		//Choose question height 
			{
				setStatus("Choosing Option Height");
				try {
					a.reloadVis();		//This will cause the form to display, by default isVisible is false, and this will set it to true
					setStatus("Done.");
				} catch (Exception e1) {
					setStatus("Error.  See stack trace.");
					consoleLog(e1.getMessage());
				}
			}
			else if(eventSrc == topMenu.setQuestionCount)		//Set Question Count
			{
				setStatus("Getting Question Count");
				num.reloadVis();		//Causes the number chooser to display.  by default isVisible is false, this sets it to true
				setStatus("Done.");
			}
			else if(eventSrc == topMenu.showResponses)		//Show the responses
			{
				Runnable showResponses = () -> {
					for(Page p : questionAns)
						for(Question q : p.getQuestionList())
							GUI.consoleLog(q.getResponse().toString());
				};
				new Thread(showResponses).start();
			}
			else if(eventSrc == topMenu.about)
			{
				new About();
			}
			else if(eventSrc == topMenu.stats)
			{
				StatSetup.main(null);
			}
			else if(eventSrc == topMenu.github)
			{
					try {
						Desktop.getDesktop().browse(new URI("https://github.com/chrisco210/AutoEval"));
					} catch (IOException | URISyntaxException e1) {
						GUI.console.log("Failed to open github.");
					}

			}
		} 
	}
}
