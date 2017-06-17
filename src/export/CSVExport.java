package export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import responses.answers.Page;
import responses.answers.Question;

public class CSVExport extends Export {
	String output;
	
	public CSVExport(ArrayList<Page> r) 
	{
		super(r);
	}

	public void saveExport(String path) throws FileNotFoundException, UnsupportedEncodingException 
	{
		for(Page p : super.pages)
		{
			for(Question q : p.getQuestionList())
			{
				output += q.getResponse().toString() + ",";
			}
			output += "\n";
		}
		
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.print(output);
		writer.close();
	}
}