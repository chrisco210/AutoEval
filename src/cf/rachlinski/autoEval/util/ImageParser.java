package cf.rachlinski.autoEval.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cf.rachlinski.autoEval.popupMenus.NumberChooser;
import cf.rachlinski.autoEval.responses.Survey;
import cf.rachlinski.autoEval.responses.answers.Page;
import cf.rachlinski.autoEval.responses.answers.Question;
import cf.rachlinski.autoEval.util.OnComplete;
import cf.rachlinski.autoEval.util.QuestionBoundList;

public final class ImageParser extends Thread implements Runnable {
	QuestionBoundList qBoundList;
	ArrayList<File> source;
	NumberChooser num;

	private OnComplete onComplete;

	private ArrayList<Page> responsesToWrite;

	/**
	 * Worker class for processing forms
	 * @param a the ImageAreaSelector class to use
	 * @param source Source File ArrayList
	 * @param num NumberChooser class to use
	 */
	public ImageParser(QuestionBoundList a, ArrayList<File> source, NumberChooser num, OnComplete onFinish)
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
}
