package autoEval;

import autoEval.gui.GUI;

final class GUIEntryPoint implements Runnable {
	@Override
	public void run() 
	{
		new GUI();
	}
}
