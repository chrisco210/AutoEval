package autoEval.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

import autoEval.ImageParser;
import util.OnComplete;
import popupMenus.About;
import popupMenus.ImageAreaSelector;
import popupMenus.InformMissingInfo;
import popupMenus.NumberChooser;
import responses.answers.Page;
import responses.answers.Question;
import storage.export.ExportGUI;
import util.QuestionBoundList;

/**
 * The main GUI
 * @author Christopher
 *
 */
public final class GUI extends JFrame {
	/**
	 * Not sure what this is
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ArrayList of source files
	 */
	public ArrayList<File> source;
	/**
	 * The ArrayList of responses
	 */
	private ArrayList<Page> questionAns;	//Store the responses to the questions.  TODO fix the number of pages in the constructor

	/**
	 * File Controller
	 */
	private FileController fileController;

	/*			Flags		*/

	/**
	 * Is true when user has set an image area
	 * @flag
	 */
	public static boolean userHasSetImageArea = false;
	/**
	 * Is true when user has set the option count in the number chooser
	 * @flag
	 */
	public static boolean userHasSetQuestionCount  = false;
	/**
	 * Is true when user has run a parse
	 * @flag
	 */
	public static boolean userHasRunParse = false;
	/**
	 * Is true when debug mode is enabled.  This enables all debug strings from ConsolePane.dbg(Object s) as well as
	 * any special methods that are used when debug is enabled
	 * @flag
	 */
	public static boolean debug = true;
	
	/*		GUI Elements 		*/
	private MenuBar topMenu;		//Menu bar displayed on top of screen
	private StatusBar statusLabel;		//Where the program status is displayed, bottom of screen
	private CenterTabPane centerPane;		//Central tab pane
	private final Container pane = getContentPane();		//Main content pane
	private ActionListener action;		//Action listener class
	public ConsolePane console;		//Console
	private ImageIcon surveyImage;      //Image display
	private JLabel imageLabel;		//More image display
	private ImageAreaSelector areaSelector = null;		//Define ImageAreaSelector early so its scope reaches all functions, same for num
	private final NumberChooser num = new NumberChooser();
	private JTree survey;			//TODO
	private DefaultMutableTreeNode question;
	
	/**
	 * The main GUI of the program
	 */
	public GUI() 
	{
		//Setup frame properties
		setTitle("AutoEval");
       	setSize(1000, 750);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        action = new topMenuBarEventListener(this);
        
		//Pane stuff
		pane.setLayout(new BorderLayout());
		
		//Create center tab pane
		centerPane = new CenterTabPane(this);
		
		//Create menu bar and add it to top of screen
		topMenu = new MenuBar(action);
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

		//File Controller
		fileController = new FileController(this);
		
		setVisible(true);		//Display the form
		getStatusBar().setStatus("Done.");
	}


	/**
	 * Get the number chooser
	 * @deprecated use getOptionCount() method instead of getting the entire number chooser
	 * @return the number chooser
	 */
	public NumberChooser getNumberChooser() {
		return num;
	}

	/**
	 * Get the responses
	 * @return the arraylist of pages
	 */
	public ArrayList<Page> getPages()
	{
		return questionAns;
	}

	/**
	 * Get the console pane
	 * @return the console pane
	 */
	public ConsolePane getConsolePane()
	{
		return console;
	}

	/**
	 * Get the status bar
	 * @return the status bar
	 */
	public StatusBar getStatusBar()
	{
		return statusLabel;
	}

	/**
	 * Get the center tab pane
	 * @return the CenterTabPane
	 */
	public CenterTabPane getTabPane()
	{
		return centerPane;
	}

	/**
	 * Get the image area selector
	 * @return
	 */
	public QuestionBoundList getQuestionBounds()
	{
		return areaSelector.getQuestionBoundList();
	}

	/**
	 * Get the number of options as selected by the image area selector
	 * @return the number of options
	 */
	public byte getOptionCount()
	{
		return num.getValue();
	}

	/**
	 * Instantiate the image area selector
	 */
	public void instantiateImageAreaSelector(File f) throws IOException
	{
		areaSelector = new ImageAreaSelector(f);
	}
	
	/**
	 * Subclass to handle Action Events from the menu bar
	 * @author Christopher
	 *
	 */
	class topMenuBarEventListener implements ActionListener
	{
		private GUI actionSender;
		topMenuBarEventListener(GUI actionSender)
		{
			this.actionSender = actionSender;
		}

		public void actionPerformed(ActionEvent e)			//Clean up this function, maybe create functions for opening files and such
		{	
			Object eventSrc = e.getSource();
			//Read menu bar inputs
			if(eventSrc == topMenu.open){		//Single File Open
				new Thread(() -> {
					getStatusBar().setStatus("Opening File");
					JFileChooser chooser = new JFileChooser();

					if(chooser.showOpenDialog(actionSender) == JFileChooser.APPROVE_OPTION)
						fileController.loadSingleFile(chooser.getSelectedFile());
					getStatusBar().setStatus("Done.");
				}).start();
			}
			else if(eventSrc == topMenu.openFolder){		//Single File Open
				new Thread(() ->
				{
				getStatusBar().setStatus("Opening File");

				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if(chooser.showOpenDialog(actionSender) == JFileChooser.APPROVE_OPTION)
					fileController.loadFolder(chooser.getSelectedFile());

				getStatusBar().setStatus("Done.");
				}).start();
			}
			else if(eventSrc == topMenu.run)
			{
				//Check if user has set an image area
				if(!userHasSetImageArea || !userHasSetQuestionCount)
				{
					InformMissingInfo.main(null);
					return;
				}
				
				getStatusBar().setStatus("Parsing");
				OnComplete onFinish = toWrite -> {questionAns = toWrite; getStatusBar().setStatus("Done.");};
				ImageParser w = new ImageParser(areaSelector.getQuestionBoundList(), source, num, 0, onFinish);
				w.start();

			}
			else if(eventSrc == topMenu.newRun)		//Start areaSelector visual comparison read, not finished, may never be
			{
			}
			else if(eventSrc == topMenu.export)		//storage.export the created data to areaSelector variety of formats.
			{
				Runnable export = () -> new ExportGUI(actionSender);
				new Thread(export).start();
			}
			else if(eventSrc == topMenu.chooseQHeight)		//Choose question height 
			{
				getStatusBar().setStatus("Choosing Option Height");
				try {
					areaSelector.reloadVis();		//This will cause the form to display, by default isVisible is false, and this will set it to true
					getStatusBar().setStatus("Done.");
				} catch (Exception e1) {
					getStatusBar().setStatus("Error.  See stack trace.");
					System.out.println(e1.getMessage());
				}
			}
			else if(eventSrc == topMenu.setQuestionCount)		//Set Question Count
			{
				getStatusBar().setStatus("Getting Question Count");
				num.reloadVis();		//Causes the number chooser to display.  by default isVisible is false, this sets it to true
				getStatusBar().setStatus("Done.");
			}
			else if(eventSrc == topMenu.showResponses)		//Show the responses
			{
				Runnable showResponses = () -> {
					for(Page p : questionAns)
						for(Question<?> q : p.getQuestionList())
							System.out.println(q.getResponse().toString());
				};
				new Thread(showResponses).start();
			}
			else if(eventSrc == topMenu.about)		//Display about menu
			{
				new About();
			}
			else if(eventSrc == topMenu.stats)		//Display statistics setup menu
			{
				System.out.println("Statistics is not implemented yet.");
			}
			else if(eventSrc == topMenu.github)		//Open github page in browser
			{
					try {
						Desktop.getDesktop().browse(new URI("https://github.com/chrisco210/AutoEval"));
					} catch (IOException | URISyntaxException e1) {
						System.out.println("Failed to open github.");
					}
			}
			else if(eventSrc == topMenu.osVisStyle)		//Change visual style to os style
			{
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(pane);
					System.out.println("Updated look and feel.");
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException ex) {
					System.out.println("Failed to change visual style.");
				}
			}
			else if(eventSrc == topMenu.javaVisStyle)		//Change visual style to java default
			{
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(pane);
					System.out.println("Updated look and feel.");
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException ex) {
					System.out.println("Failed to change visual style.");
				}
			}
			else if(eventSrc == topMenu.debug)
			{
				debug = !debug;
				System.out.println("Debug strings: " + Boolean.toString(debug));
				if(debug)
					topMenu.debug.setText("Disable Debug Strings");
				else
					topMenu.debug.setText("Enable Debug Strings");
			}
		} 
	}
}
