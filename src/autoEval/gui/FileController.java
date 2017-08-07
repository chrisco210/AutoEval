package autoEval.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import popupMenus.ImageAreaSelector;
import responses.answers.Page;

public class FileController {
	public ArrayList<File> source;
	public ArrayList<Page> questionAns;	//Store the responses to the questions.  TODO fix the number of pages in the constructor
	public GUI superGUI;
	
	/**
	 * Create a new file controller
	 * @param g the GUI to use
	 */
	public FileController(GUI g)
	{
		superGUI = g;
	}
	
	/**
	 * Load a single file
	 * @param file the file to load
	 * @throws IOException
	 */
	protected void loadFile(File file) throws IOException
	{
		
	}
	
	/**
	 * Load a folder 
	 * @param folder the folder to load
	 * @throws IOException
	 */
	protected void loadFolder(File folder) throws IOException
	{
		
	}
	
	/**
	 * Returns an ArrayList of the loaded files
	 * @return an ArrayList of the files loaded
	 */
	public ArrayList<File> getFiles()
	{
		return source;
	}
	
	/**
	 * Get a single file
	 * @param i the file to get
	 * @return a file class of the specified index
	 */
	public File getFile(int i)
	{
		return source.get(i);
	}
	
	/**
	 * Clear the source ArrayList
	 */
	public void clearSource()
	{
		source.clear();
	}
}
