package export;

import java.io.File;
import java.util.ArrayList;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.answers.Page;

public class ProjectExport extends Export{
	private ArrayList<File> fileSource;
	private ImageAreaSelector areaSelector;
	private int questionCount;
	
	public ProjectExport(ArrayList<Page> r, ArrayList<File> fileSource, ImageAreaSelector areaSelector, NumberChooser numChooser) 
	{
		super(r);
		this.fileSource = fileSource;
		this.areaSelector = areaSelector;
		questionCount = numChooser.getValue();
	}

	@Override
	public void saveExport(String path)
	{
		
	}

}
