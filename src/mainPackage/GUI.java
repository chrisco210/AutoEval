package mainPackage;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.answers.Page;
import responses.answers.Question;
import export.ExportGUI;

/**
 * The main GUI
 * @author Christopher
 *
 */
public final class GUI extends JFrame implements ActionListener, KeyListener {		//Only create one GUI.
	/*		--------VARIABLES--------		*/
	private static final long serialVersionUID = 1L;
	public ArrayList<File> source;
	public static ArrayList<Page> questionAns;	//Store the responses to the questions.  TODO fix the number of pages in the constructor
	public static int questionCount;
	ImageAreaSelector a = null;		//Define ImageAreaSelector early so its scope reaches all functions, same for num
	NumberChooser num = new NumberChooser();
	
	/*		--------GUI ITEMS--------		*/
	private static JPanel consoleDisplayPane;
	private static JMenuBar topMenu;
	private static JMenu file;
	private static JMenu edit;
	private static JMenu view;
	private static JMenu actions;
	private static JMenuItem open;
	private static JMenuItem run;
	private static JMenuItem newRun;
	private static JMenuItem export;
	private static JLabel statusLabel;
	private static ImageIcon surveyImage;
	private static JLabel imageLabel;
	private static Container pane;
	private static JMenuItem chooseQHeight;
	private static JMenuItem displayBounds;
	private static JTree survey;
	private static DefaultMutableTreeNode question;
	private static JMenuItem setQuestionCount;
	private static JTabbedPane centerPane;
	private static JTextArea consoleTextBox;
	private static JTextField consoleInput;
	private static JMenuItem openFolder;
	private static JMenuItem showResponses;
	private static JMenuItem importProject;
	
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
		
		consoleDisplayPane = new JPanel();
		centerPane = new JTabbedPane();
		
		//Menu Bar stuff
		topMenu = new JMenuBar();		//Main menu bar
		//Open menu
		open = new JMenuItem("Open");
		open.addActionListener(this);
		openFolder = new JMenuItem("Open Folder");
		openFolder.addActionListener(this);
		//Actions menu
		actions = new JMenu("Actions");
		run = new JMenuItem("Parse Form (Pixel Count)");
		run.addActionListener(this);
		showResponses = new JMenuItem("Show Responses");
		showResponses.addActionListener(this);
		newRun = new JMenuItem("Parse Form(Visual)");
		newRun.addActionListener(this);
		chooseQHeight = new JMenuItem("Question Height");
		chooseQHeight.addActionListener(this);
		displayBounds = new JMenuItem("Display Bounds");
		displayBounds.addActionListener(this);
		setQuestionCount = new JMenuItem("# of options...");
		setQuestionCount.addActionListener(this);
		export = new JMenuItem("Export...");
		export.addActionListener(this);
		importProject = new JMenuItem("Import");
		importProject.addActionListener(this);
		actions.add(run);
		actions.add(newRun);
		actions.add(showResponses);
		//File menu
		file = new JMenu("File");
		file.add(open);
		file.add(openFolder);
		file.add(export);
		file.add(importProject);
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
		consoleTextBox.setFont(new Font("Consolas", Font.PLAIN, 14));
		consoleTextBox.setEditable(false);
		consoleDisplayPane.setLayout(new BorderLayout());
		consoleInput = new JTextField();
		consoleInput.addKeyListener(this);
		consoleDisplayPane.add(consoleInput, BorderLayout.SOUTH);
		consoleDisplayPane.add(consoleTextBox, BorderLayout.CENTER);
		
		//Tabbed pane stuff
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
		setStatus("Done.");
	}
	
	//Handles 
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		Object eventSrc = e.getSource();
		//Read menu bar inputs
		if(eventSrc == open){		//Single File Open 
			setStatus("Opening File");
			
			try{
				source.clear();
			} catch(Exception e1) {		}
			
			centerPane.removeAll();
			centerPane.add("Console", consoleDisplayPane);
			
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
					consoleLog(e1.getLocalizedMessage());
				}
			}
			
			try{	//Remove any existing images from the display
				remove(imageLabel);
			} catch(Exception ex) { }
			
			//Image display
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
					consoleLog(ex.getMessage());
					setStatus("Error. See stack trace.");
				}
			}
			setVisible(false);
			setVisible(true);
			setStatus("Done.");
		}
		else if(eventSrc == openFolder){		//Single File Open 
			setStatus("Opening File");
			try{
			source.clear();
			} catch (Exception e2) {
				
			}
			centerPane.removeAll();		//Clear all the tabs from the center pane
			centerPane.add("Console", consoleDisplayPane);		//re add the console
			
			File path = null;		//Prevent java from being stupid
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int i = fc.showOpenDialog(this);
			if(i == JFileChooser.APPROVE_OPTION)
			{
				path = fc.getSelectedFile();
				
			}
			source = new ArrayList<File>(path.listFiles().length);
			source.addAll(Arrays.asList(path.listFiles()));
			try {
				a = new ImageAreaSelector(source.get(0));		//A is the ImageAreaSelector class, sets the selected file
			} catch (IOException e1) {
				consoleLog(e1.getLocalizedMessage());
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
					consoleLog(ex.getMessage());
					setStatus("Error. See stack trace.");
				}
			}
			setVisible(false);
			setVisible(true);
			setStatus("Done.");
		}
		else if(eventSrc == run)
		{
			setStatus("Parsing");
			System.out.println("Parsing.");
			Worker w = new Worker(a, source, num, 0);
			w.start();
		}
		else if(eventSrc == newRun)		//Start a visual comparison read, not finished, may never be
		{
		}
		else if(eventSrc == export)		//export the created data to a variety of formats.
		{
			Runnable export = () -> {new ExportGUI();};
			new Thread(export).start();
		}
		else if(eventSrc == chooseQHeight)		//Choose question height 
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
		else if(eventSrc == setQuestionCount)		//Set Question Count
		{
			setStatus("Getting Question Count");
			num.reloadVis();		//Causes the number chooser to display.  by default isVisible is false, this sets it to true
			setStatus("Done.");
		}
		else if(eventSrc == showResponses)		//Show the responses
		{
			Runnable showResponses = () -> {
				for(Page p : questionAns)
					for(Question q : p.getQuestionList())
						GUI.consoleLog(q.getResponse().toString());
			};
			new Thread(showResponses).start();
		}
	} 
	
	/**
	 * Set the status bar, and logs it to the console
	 * @param s the string to display
	 */
	public static void setStatus(String s)		//update the status bar
	{
		consoleLog(s);
		statusLabel.setText(s);
		statusLabel.setVisible(false);
		statusLabel.setVisible(true);
	}
	
	/**
	 * Log a message to the console
	 * @param s the string to display
	 */
	public static void consoleLog(String s)
	{
		System.out.println(s);
		consoleTextBox.append("\n" + s);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
