package storage.export;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import responses.answers.Page;
import responses.answers.Question;

public class TextExport extends Export {

	public TextExport(ArrayList<Page> r)

	{
		super(r);
	}

	@Override
	public void saveExport(String path) throws FileNotFoundException, UnsupportedEncodingException
	{
		String out = "";

		int i = 1, j = 1;

		for(Page p : pages)
		{
			out += "Survey " + i + ": \n";
			for (Question<?> q : p.getQuestionList())
			{
				out += "Question "+ j + q.getResponse().toString() + "\n";
				j++;
			}
		}

		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.print(out);
		writer.close();

	}

}
