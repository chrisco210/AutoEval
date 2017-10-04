package test.cf.rachlinski.autoEvalTests;

import cf.rachlinski.autoEval.popupMenus.ImageAreaSelector;

import java.io.File;
import java.io.IOException;

public class ImageAreaSelectorTest
{
	public static void main(String[] args)
	{
		try
		{
			ImageAreaSelector sel = new ImageAreaSelector(new File("C:\\Users\\Christopher\\Desktop\\Stuff\\bigsurvey\\se.png"));
			sel.displaySelector();
			sel.reloadVis();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}



	}
}
