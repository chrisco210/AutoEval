package export;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import responses.answers.*;

public abstract class Export {
	ArrayList<Page> pages;
	
	public Export(ArrayList<Page> p)
	{
		pages = p;
	}
	
	/**
	 * This should be used to create the string to be written to the file.
	 * @return The text to be writtent to the file as a string
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public abstract void saveExport(String path) throws FileNotFoundException, UnsupportedEncodingException;
}
