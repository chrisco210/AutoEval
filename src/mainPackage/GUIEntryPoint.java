package mainPackage;

import java.io.IOException;

final class GUIEntryPoint implements Runnable {
	@Override
	public void run() 
	{
		try {
			new GUI();
		} catch (IOException e) {
			e.printStackTrace();		//TODO useless catch block
		}
	}
}
