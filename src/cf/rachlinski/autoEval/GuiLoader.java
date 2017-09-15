package cf.rachlinski.autoEval;

import cf.rachlinski.autoEval.gui.GUI;

public final class GuiLoader implements Runnable {
	@Override
	public void run() 
	{
		new GUI();
	}
}
