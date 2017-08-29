package storage.export;

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
		int i = 0;
		output = ",";		//empty row, improve readability
		for(int j = 1; j <= super.pages.get(0).getQuestionList().size(); j++)		//number each question column
			output += "Question " + Integer.toString(j) + ",";
		output += "\n";
		for(Page p : super.pages)
		{
			output += "Survey " + Integer.toString(i) + ",";
			for(Question<?> q : p.getQuestionList())		//Write each question
			{
				output += q.getResponse().toString() + ",";
			}
			output += "\n";
			i++;
		}
		
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.print(output);
		writer.close();
	}
}