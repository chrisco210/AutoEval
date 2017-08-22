package console.scripting;

import autoEval.gui.GUI;

public class EnvironmentConstants {
	public static String PROGRAM_ROOT = "YOU SHOULD NOT SEE THIS STRING";
	
	public static void SETUP_CONSTANTS()
	{
		PROGRAM_ROOT = GUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}
}
