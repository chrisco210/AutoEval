package mainPackage;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import popupMenus.ImageAreaSelector;
import responses.Survey;

public class GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public File source;
	
	/*		--------GUI ITEMS--------		*/
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
	JTree survey;
	DefaultMutableTreeNode question;
	
	public static void main(String[] args) throws IOException
	{
		new GUI();     
	}
	public GUI() throws IOException
	{
		setTitle("AutoEval");
        setSize(1000, 750);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		//Menu Bar stuff
		topMenu = new JMenuBar();
		open = new JMenuItem("Open");
		open.addActionListener(this);
		actions = new JMenu("Actions");
		run = new JMenuItem("Parse Evaluation (Pixel Count)");
		run.addActionListener(this);
		newRun = new JMenuItem("Parse Evaluations (Visual)");
		newRun.addActionListener(this);
		chooseQHeight = new JMenuItem("Question Height");
		chooseQHeight.addActionListener(this);
		chooseAHeight = new JMenuItem("Answer Height");
		chooseAHeight.addActionListener(this);
		export = new JMenuItem("Export Responses");
		export.addActionListener(this);
		actions.add(run);
		actions.add(newRun);
		file = new JMenu("File");
		file.add(open);
		file.add(export);
		view = new JMenu("View");
		edit = new JMenu("Image");
		edit.add(chooseQHeight);
		edit.add(chooseAHeight);
		topMenu.add(file);
		topMenu.add(edit);
		topMenu.add(actions);
		topMenu.add(view);
		pane.add(topMenu, BorderLayout.NORTH);
		
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
			pane.add(imageLabel, BorderLayout.CENTER);
			} catch (IOException ex) {		//Handle IOException
				ex.printStackTrace();
				setStatus("Caught " + ex.getCause().toString());
			}
			setVisible(false);
			setVisible(true);
			setStatus("Done.");
		}
		else if(e.getSource() == run)		//Start a pixel count read, not finished
		{
			System.out.println("Started pixel count parse");
			Survey s = new Survey(source, 0,0);
			
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
				ImageAreaSelector a = new ImageAreaSelector(source);
				a.displayForm();
				a.displaySelector();
				a.reloadVis();
				
			} catch (Exception e1) {
				setStatus("Error.  See stack trace.");
				e1.printStackTrace();
			}
			
		}
	}
	
	public void setStatus(String s)		//update the status bar
	{
		System.out.println(s);
		
		statusLabel.setText(s);
		statusLabel.setVisible(false);
		statusLabel.setVisible(true);
	}
	
}