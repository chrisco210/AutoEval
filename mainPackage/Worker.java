package mainPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import popupMenus.ImageAreaSelector;
import popupMenus.NumberChooser;
import responses.Survey;

public class Worker extends Thread implements Runnable {
	ImageAreaSelector a;
	ArrayList<File> source;
	NumberChooser num;
	int[] responses;
	/**
	 * Worker class for processing forms
	 * @param a the ImageAreaSelector class to use
	 * @param source Source File ArrayList
	 * @param num NumberChooser class to use
	 */
	public Worker(ImageAreaSelector a, ArrayList<File> source, NumberChooser num)
	{
		this.a = a;
		this.source = source;
		this.num = num;
	}

	@Override
	public void run() 
	{
		responses = new int[source.size() + 1];
		// TODO Auto-generated method stub
		int i = 1;
		for(File f : source)
		{
			Survey s = new Survey(f, a.getBound1().x, 	//Construct a new survey
					a.getBound1().y, 
					a.getBound2().y - a.getBound1().y, 
					a.getBound2().x - a.getBound1().x,
					num.getValue()
			);
			
			//Get the response to the question
			try {
				responses[i] = s.getResponse(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			GUI.questionAns = new int[responses.length];
			GUI.questionAns = responses;
		}
	}
	
	public int[] getResponses()
	{
		return(responses);
	}
}
