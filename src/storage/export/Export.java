package storage.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import responses.answers.Page;

/**
 * Superclass for all storage.export operations
 */
public abstract class Export {
	ArrayList<Page> pages;
	
	/**
	 * Constructor including all the results from the pages
	 * @param p The ArrayList of Pages to use
	 */
	public Export(ArrayList<Page> p)
	{
		pages = p;
	}
	
	/**
	 * Save the results of the surveys to a file of type specified by subclass
	 * @param path The path to save the file at
	 * @return The text to be writtent to the file as a string
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public abstract void saveExport(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException;
}
