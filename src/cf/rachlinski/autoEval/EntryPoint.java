package cf.rachlinski.autoEval;

import cf.rachlinski.autoEval.console.scripting.EnvironmentConstants;
import com.sanityinc.jargs.CmdLineParser;
import com.sanityinc.jargs.CmdLineParser.Option;
import com.sanityinc.jargs.CmdLineParser.OptionException;

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
		
		//This is annoying.  I don't really think I need command line arguments, but they will be implemented later TODO
		//Check if the user doesnt want to disply the GUI
		//if(!parser.getOptionValue(doGui))
		//	gui = false;
		
		//Start the GUI
		
		if(gui)
			new Thread(new GuiLoader()).start();
	}
}
