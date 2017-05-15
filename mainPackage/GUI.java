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
	JPopupMenu chooseQHeightMenu;
	JPopupMenu chooseAHeightMenu;
	JSpinner chooseHeightSpinnerAX;
	JSpinner chooseHeightSpinnerAY;
	JSpinner chooseHeightSpinnerQX;
	JSpinner chooseHeightSpinnerQY;
	SpinnerModel chooseCoordsSpinnerQX;
	SpinnerModel chooseCoordsSpinnerQY;
	
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
		
		//Popup menu for choosing question height
		chooseQHeightMenu = new JPopupMenu("Choose Question Size");
		chooseCoordsSpinnerQX = new SpinnerNumberModel(0,0,Integer.MAX_VALUE, 1);
		chooseCoordsSpinnerQY = new SpinnerNumberModel(0,0,Integer.MAX_VALUE, 1);
		chooseHeightSpinnerQX = new JSpinner(chooseCoordsSpinnerQX);
		chooseHeightSpinnerQY = new JSpinner(chooseCoordsSpinnerQY);
		chooseHeightSpinnerQX.setBounds(100, 100, 50, 30);
		chooseQHeightMenu.add(chooseHeightSpinnerQX);
		chooseQHeightMenu.add(chooseHeightSpinnerQY);
		chooseQHeightMenu.setSize(100,50);
		
		setVisible(true);		//Display the form
	}
	
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

			setStatus("Failed to display " + source.toString());
			BufferedImage img;
			
			//Attempt to display the image, catches IO exception
			try{
			img = ImageIO.read(source);
			surveyImage = new ImageIcon(img);
			imageLabel = new JLabel(surveyImage);
			imageLabel.setSize(100, 100);
			pane.add(imageLabel, BorderLayout.CENTER);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			setVisible(false);
			setVisible(true);
			setStatus("Done.");
		}
		else if(e.getSource() == run)
		{
			System.out.println("Started pixel count parse");
			Survey s = new Survey(source, 0,0);
		}
		else if(e.getSource() == newRun)
		{
			System.out.println("Started visual parse");
		}
		else if(e.getSource() == export)
		{
			System.out.println("Export menu selected");
		}
		else if(e.getSource() == chooseQHeight)
		{
			chooseQHeightMenu.show(this, this.getX(), this.getY());
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
