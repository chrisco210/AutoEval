package cf.rachlinski.autoEval;

import cf.rachlinski.autoEval.gui.GUI;

final class GUIEntryPoint implements Runnable {
	@Override
	public void run() 
	{
		new GUI();
	}
}
