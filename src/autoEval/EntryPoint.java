package autoEval;	

import autoEval.gui.GUI;

import com.sanityinc.jargs.CmdLineParser;
import com.sanityinc.jargs.CmdLineParser.Option;
import com.sanityinc.jargs.CmdLineParser.OptionException;

import console.scripting.EnvironmentConstants;

public final class EntryPoint {
	private static boolean gui = true; 
	
	public static void main(String[] args) 
	{
		//Setup EnvironmentConstants
		EnvironmentConstants.SETUP_CONSTANTS();
		
		//Parse the command line options
		CmdLineParser parser = new CmdLineParser();
		
		Option<Boolean> doGui = parser.addBooleanOption('g', "do-gui");		//Create options for displaying the GUI
		
		//Parse arguments
		try {
			parser.parse(args);
		} catch(OptionException oe) {
			oe.printStackTrace();
			System.exit(0);
		}
		
		
		//Check if the user doesnt want to disply the GUI
		//if(!parser.getOptionValue(doGui))
		//	gui = false;
		
		//Start the GUI
		
		if(gui)
			new GUIEntryPoint().run();
	}
}
