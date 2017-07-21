package autoEval.gui;

import java.io.File;
import java.util.ArrayList;

public class FileController {
	private ArrayList<File> source;
	
	
	
	protected void loadFile(File file)
	{
		
	}
	
	protected void loadFolder(File folder)
	{
		
	}
	
	public ArrayList<File> getFiles()
	{
		return source;
	}
	
	public File getFile(int i)
	{
		return source.get(i);
	}
	
	public void clearSource()
	{
		source.clear();
	}
}
