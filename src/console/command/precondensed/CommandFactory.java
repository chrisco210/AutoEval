package console.command.precondensed;

import autoEval.gui.GUI;


public class CommandFactory {

	/**
	 * Factory to create a command based on user input
	 * @param text The text to decide what command to create
	 * @return A PrecondensedCommand of the desired type, based on command precedence.  If an unrecognized command is entered, a REM command will be returned
	 */
	public static PrecondensedCommand createCommand(String text)
	{
		GUI.console.dbg("CommandFactory.createCommand called");	//Debug
		
		if(text.contains("OUT"))
			return new OUT(text);
		else if(text.contains("REM"))
			return new REM();
		
		GUI.console.err("Unrecognized Command");
		return new REM();
	}
	
}
