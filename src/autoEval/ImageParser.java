package autoEval;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import autoEval.gui.GUI;
import popupMenus.NumberChooser;
import responses.Survey;
import responses.answers.Page;
import responses.answers.Question;
import util.OnComplete;
import util.QuestionBoundList;

public final class ImageParser extends Thread implements Runnable {
	QuestionBoundList qBoundList;
	ArrayList<File> source;
	NumberChooser num;
	private int[][] responses;

	private OnComplete onComplete;

	private ArrayList<Page> responsesToWrite;

	/**
	 * Worker class for processing forms
	 * @param a the ImageAreaSelector class to use
	 * @param source Source File ArrayList
	 * @param num NumberChooser class to use
	 * @param questionNum which question to use on the page
	 */
	public ImageParser(QuestionBoundList a, ArrayList<File> source, NumberChooser num, int questionNum, OnComplete onFinish)
	{
		qBoundList = a;
		this.source = source;
		this.num = num;

		this.onComplete = onFinish;
	}

	@Override
	public void run() 
	{
		responsesToWrite = new ArrayList<Page>(source.size());

		for(int j = 0; j < source.size(); j++)
		{
			parseResponse(j);
		}

		onComplete.onFinishParse(responsesToWrite);
	}
	
	private void parseResponse(int pageNumber)
	{
		Page p = new Page();
		responsesToWrite.add(p);			//Add to an arraylist of Page classes in the GUI class
		for(int i = 0; i < qBoundList.size(); i++)
		{
			Survey s = new Survey(
					source.get(pageNumber),
					qBoundList.getPointFromList(1, i),
					qBoundList.getPointFromList(2, i),
					num.getValue()
					);
			try {
				switch(qBoundList.getType(i))
				{
				case MultipleChoice:
					p.addQuestion(new Question<Integer>(s.getResponse()));		//Add the response to the page
					break;
				default:
					break;
				}
			} catch (IOException e) {
				p.addQuestion(new Question<String>("Failed to read a response"));
				e.printStackTrace();
			}
		}
	}

	/**
	* Return areaSelector two dimensional array of the multiple choice responses
	* @returns areaSelector two dimensional array of multiple choice responses
	*/
	public int[][] getResponses()
	{
		return(responses);
	}

}
