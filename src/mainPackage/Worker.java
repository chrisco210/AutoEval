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
		responses = new int[source.size()][a.getTypes().size()];
		for(int j = 0; j < source.size(); j++)
		{
			for(int i = 0; i < a.getTypes().size(); i++)
			{
				Survey s = new Survey(source.get(j), a.getBoundList(1).get(i), a.getBoundList(2).get(i), num.getValue());
				try {
					responses[j][i] = s.getResponse();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		GUI.questionAns = responses;
		GUI.setStatus("Done.");
	}
	
	public int[][] getResponses()
	{
		return(responses);
	}
}
