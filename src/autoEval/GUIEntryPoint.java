package autoEval;

import autoEval.gui.GUI;

final class GUIEntryPoint implements Runnable {
	@Override
	public void run() 
	{
		//Start the GUI in a new thread
		new GUI();
	}
}
