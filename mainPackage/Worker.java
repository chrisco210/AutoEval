package mainPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.Survey;

public final class Worker extends Thread implements Runnable {
	ImageAreaSelector a;
	ArrayList<File> source;
	NumberChooser num;
	int[] responses;
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
		responses = new int[source.size() + 1];
		int i = 1;
		for(File f : source)
		{
			Survey s = new Survey(f, a.getBound(1).x, 	//Construct a new survey
					a.getBound(1).y, 
					a.getBound(2).y - a.getBound(1).y, 
					a.getBound(2).x - a.getBound(1).x,
					num.getValue()
			);
			
			//Get the response to the question
			try {
				responses[i] = s.getResponse(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
			GUI.questionAns = new int[responses.length];
			GUI.questionAns = responses;
		}
		GUI.setStatus("Done.");
	}
	
	public int[] getResponses()
	{
		return(responses);
	}
}
