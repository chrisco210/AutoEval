package cf.rachlinski.autoEval.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

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
	protected JMenu visStyle;
	protected JMenuItem osVisStyle;
	protected JMenuItem javaVisStyle;
	protected JMenu help;
	protected JMenuItem about;
	protected JMenuItem github;
	protected JMenuItem stats;
	protected JMenuItem debug;
	
	public MenuBar(ActionListener action)
	{
		open = new JMenuItem("Open");
		open.addActionListener(action);
		openFolder = new JMenuItem("Open Folder");
		openFolder.addActionListener(action);
		//Actions menu
		actions = new JMenu("Actions");
		run = new JMenuItem("Parse Form (Pixel Count)");
		run.addActionListener(action);
		showResponses = new JMenuItem("Show Responses");
		showResponses.addActionListener(action);
		newRun = new JMenuItem("Parse Form(Visual)");
		newRun.addActionListener(action);
		chooseQHeight = new JMenuItem("Question Height");
		chooseQHeight.addActionListener(action);
		displayBounds = new JMenuItem("Display Bounds");
		displayBounds.addActionListener(action);
		setQuestionCount = new JMenuItem("# of options...");
		setQuestionCount.addActionListener(action);
		export = new JMenuItem("Export...");
		export.addActionListener(action);
		importProject = new JMenuItem("Import");
		importProject.addActionListener(action);
		stats = new JMenuItem("Statistics");
		stats.addActionListener(action);
		actions.add(run);
		actions.add(newRun);
		actions.add(showResponses);
		actions.add(stats);
		//File menu
		file = new JMenu("File");
		file.add(open);
		file.add(openFolder);
		file.add(export);
		file.add(importProject);
		//View menu
		view = new JMenu("View");
		visStyle = new JMenu("Visual Style");
		osVisStyle = new JMenuItem("OS Components");
		osVisStyle.addActionListener(action);
		javaVisStyle = new JMenuItem("Java Components");
		javaVisStyle.addActionListener(action);
		if(!GUI.debug)
			debug = new JMenuItem("Enable Debug Strings");
		else
			debug = new JMenuItem("Disable Debug Strings");
		debug.addActionListener(action);
		visStyle.add(osVisStyle);
		visStyle.add(javaVisStyle);
		view.add(visStyle);
		view.add(debug);
		//Edit menu
		edit = new JMenu("Image");
		edit.add(chooseQHeight);
		edit.add(setQuestionCount);
		//Help menu
		help = new JMenu("Help");
		about = new JMenuItem("About");
		about.addActionListener(action);
		github = new JMenuItem("Github");
		github.addActionListener(action);
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
