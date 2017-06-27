package export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import mainPackage.GUI;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.answers.Page;

/**
 * Used to export the data supplied as an AutoEval project
 * @author Christopher
 * @see Export
 */
public class ProjectExport extends Export{
	private ArrayList<File> fileSource;
	private ImageAreaSelector areaSelector;
	private int questionCount;
	
	/**
	 * Constructor including all required data for a project export
	 * @param r			The page classes containing the responses to the questions
	 * @param fileSource		An arraylist of the files used in the project
	 * @param areaSelector		The ImageAreaSelector used in the project
	 * @param numChooser		The number chooser that was used in the project
	 */
	public ProjectExport(ArrayList<Page> r, ArrayList<File> fileSource, ImageAreaSelector areaSelector, NumberChooser numChooser) 
	{
		super(r);
		this.fileSource = fileSource;
		this.areaSelector = areaSelector;
		questionCount = numChooser.getValue();
	}

	public void saveExport(String path) throws IOException
	{
		JsonObject json = new JsonObject();		//Create a new JSON object
		
		//Write metadata to the file
		json.put("question_count", questionCount);		//Write the question count
		
		JsonArray areaSelectorPoints = new JsonArray();		//Create json array for the point pairs and question types
		for(int i = 0; i < areaSelector.getTypes().size(); i++)			//Write a sub JsonArray to the point pair array, which will 
		{																//include each bound, and the question type for each question
			JsonArray pointData = new JsonArray();
			pointData.add(areaSelector.getBoundList(1).get(i));
			pointData.add(areaSelector.getBoundList(2).get(i));
			pointData.add(areaSelector.getType(i));
			areaSelectorPoints.add(pointData);
		}
		
		//Write all file names to a jsonarray
		JsonArray files = new JsonArray();
		for(File f : fileSource)					
			files.add(f.getName());		//Get the file names.  Remember the project will be zipped into a file
		json.put("files", files);
		
		File jsonFile = new File("project.json");
		FileWriter jsonFileWriter = new FileWriter(jsonFile);
		jsonFileWriter.write(json.toJson());
		jsonFileWriter.flush();
		jsonFileWriter.close();
		fileSource.add(jsonFile);
		
		//DEBUG:
		System.out.println(path.lastIndexOf(".") - 1);
		System.out.println(path.lastIndexOf("\\") + 1);
		System.out.println(path.lastIndexOf(".") - 1);
		
		System.out.println("--------------------");
		System.out.println(path);
		System.out.println(path.substring(0, path.lastIndexOf("."))
				+ path.substring(path.lastIndexOf("\\"), path.lastIndexOf(".") )
				+ ".aep");
		//Zip files into project file TODO fix file naming
		File zipOut = new File(path			//A file class for the path of the output zip file
				//path.substring(0, path.lastIndexOf("."))
				//+ path.substring(path.lastIndexOf("\\"), path.lastIndexOf("."))
				//+ ".aep"
				);

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipOut));
		
		FileInputStream fileInputStream = null;
		for(File f : fileSource)								//Put all files into the zip entry
		{
			out.putNextEntry(new ZipEntry(f.getName()));
			/* 
			 * Read the input file by chucks of 1024 bytes and 
			 * write the read bytes to the zip stream. 
			 * Thanks to JavaCodeGeeks for most of this code
			*/
			fileInputStream = new FileInputStream(f);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(buf)) > 0) 
			{
				out.write(buf, 0, bytesRead);
			}
			out.closeEntry();
		}
		out.close();
		fileInputStream.close();
	}
}
