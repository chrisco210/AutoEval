package cf.rachlinski.autoEval.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileController {
	public ArrayList<File> source;
	private GUI superGUI;
	
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
	protected void loadSingleFile(File file)
	{
		//Clear all images from the list, and remove them from the tab pane
		clearSource();
		superGUI.getTabPane().clearAllImages();

		source = new ArrayList<>(1);

		source.add(file);

		//Attempt to instantiate an image area selector
		try
		{
			superGUI.instantiateImageAreaSelector(source.get(0));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		for(File f : source)
			superGUI.getTabPane().addImage(f);
	}

	/**
	 * Load a file.  This method is intended to be used with the load folder function, as it does
	 * not clear the source and clear all images before loading
	 * @param file
	 */
	private void loadFile(File file)
	{
		source.add(file);
	}

	/**
	 * Load a folder
	 * @param folder the folder to load
	 * @throws IOException
	 */
	protected void loadFolder(File folder)
	{
		//Clear all images from the list, and remove them from the tab pane
		clearSource();
		superGUI.getTabPane().clearAllImages();

		source = new ArrayList<>(folder.listFiles().length);

		source.addAll(Arrays.asList(folder.listFiles()));

		//Attempt to instantiate an image area selector
		try
		{
			superGUI.instantiateImageAreaSelector(source.get(0));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		for(File f : source)
			superGUI.getTabPane().addImage(f);
	}

	/**
	 * Add a file
	 * @param file the file to load
	 * @throws IOException
	 */
	protected void addFile(File file) throws IOException
	{

	}

	/**
	 * Display a file on the center tab pane.
	 * @param f
	 */
	private void addToCenterPane(File f)
	{
		superGUI.getTabPane().addImage(f);
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
	 * Get areaSelector single file
	 * @param i the file to get
	 * @return areaSelector file class of the specified index
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
		try{
			source.clear();
		} catch(Exception e) {
			System.err.println("Failed to clear source.  Could it already be empty?");
		}
	}
}
