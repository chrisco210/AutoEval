package mainPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.Survey;
import responses.answers.Page;
import responses.answers.Question;

public final class Worker extends Thread implements Runnable {
	ImageAreaSelector a;
	ArrayList<File> source;
	NumberChooser num;
	private int[][] responses;
	
	/**
	 * Worker class for processing forms
	 * @param a the ImageAreaSelector class to use
	 * @param source Source File ArrayList
	 * @param num NumberChooser class to use
	 * @param questionNum which question to use on the page
	 */
	public Worker(ImageAreaSelector a, ArrayList<File> source, NumberChooser num, int questionNum)
	{
		this.a = a;
		this.source = source;
		this.num = num;
	}

	@Override
	public void run() 
	{
		GUI.questionAns = new ArrayList<Page>(source.size());
		
		for(int j = 0; j < source.size(); j++)
		{
			parseResponse(j);
		}
		GUI.setStatus("Done.");
	}
	
	private void parseResponse(int pageNumber)
	{
		Page p = new Page();
		GUI.questionAns.add(p);			//Add to an arraylist of Page classes in the GUI class
			for(int i = 0; i < a.getTypes().size(); i++)
			{
				Survey s = new Survey(source.get(pageNumber), a.getBoundList(1).get(i), a.getBoundList(2).get(i), num.getValue());
				try {
					switch(a.getType(i))
					{
					case MultipleChoice:
						p.addQuestion(new Question<Integer>(s.getResponse()));
						break;
					default:
						break;
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
	}

	/**
	* Return a two dimensional array of the multiple choice responses
	* @returns a two dimensional array of multiple choice responses
	*/
	public int[][] getResponses()
	{
		return(responses);
	}
}
