package autoEval;

import java.io.IOException;

import autoEval.gui.GUI;

final class GUIEntryPoint implements Runnable {
	@Override
	public void run() 
	{
		try {
			new GUI();
		} catch (IOException e) {
			System.out.println("Error construction GUI.");
		}
	}
}
