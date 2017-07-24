package autoEval;

import java.io.IOException;

import autoEval.gui.GUI;

final class GUIEntryPoint implements Runnable {
	@Override
	public void run() 
	{
		//Start the GUI in a new thread
		try {
			new GUI();
		} catch (IOException e) {
			System.err.println("Error construction GUI.");
		}
	}
}
