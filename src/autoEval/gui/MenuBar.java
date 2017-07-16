package autoEval.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -290214953363786855L;
	protected  JMenu file;
	protected  JMenu edit;
	protected  JMenu view;
	protected  JMenu actions;
	protected  JMenuItem open;
	protected  JMenuItem run;
	protected  JMenuItem newRun;
	protected  JMenuItem export;
	protected  JMenuItem openFolder;
	protected  JMenuItem showResponses;
	protected  JMenuItem importProject;
	protected  JMenuItem setQuestionCount;
	protected  JMenuItem chooseQHeight;
	protected  JMenuItem displayBounds;
	protected JMenu help;
	protected JMenuItem about;
	protected JMenuItem github;
	
	public MenuBar()
	{
		open = new JMenuItem("Open");
		open.addActionListener(GUI.action);
		openFolder = new JMenuItem("Open Folder");
		openFolder.addActionListener(GUI.action);
		//Actions menu
		actions = new JMenu("Actions");
		run = new JMenuItem("Parse Form (Pixel Count)");
		run.addActionListener(GUI.action);
		showResponses = new JMenuItem("Show Responses");
		showResponses.addActionListener(GUI.action);
		newRun = new JMenuItem("Parse Form(Visual)");
		newRun.addActionListener(GUI.action);
		chooseQHeight = new JMenuItem("Question Height");
		chooseQHeight.addActionListener(GUI.action);
		displayBounds = new JMenuItem("Display Bounds");
		displayBounds.addActionListener(GUI.action);
		setQuestionCount = new JMenuItem("# of options...");
		setQuestionCount.addActionListener(GUI.action);
		export = new JMenuItem("Export...");
		export.addActionListener(GUI.action);
		importProject = new JMenuItem("Import");
		importProject.addActionListener(GUI.action);
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
		//Help menu
		help = new JMenu("Help");
		about = new JMenuItem("About");
		about.addActionListener(GUI.action);
		github = new JMenuItem("Github");
		github.addActionListener(GUI.action);
		help.add(about);
		help.add(github);
		
		//Add menu items
		add(file);
		add(edit);
		add(actions);
		add(view);
		add(help);
	}
}
