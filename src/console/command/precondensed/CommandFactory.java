package console.command.precondensed;

import console.scripting.DefaultScripts;
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
		
		//TODO make this better
		if(text.contains("OUT"))
			return new OUT(text);
		else if(text.contains("REM"))
			return new REM();
		else if(text.contains("ALLOC"))
			return new ALLOC(text);
		else if(text.contains("EXE"))
			return new EXE(text);
		GUI.console.controller.exec(DefaultScripts.CMD_NOT_RECOGNIZED);
		return new REM();
	}
	
}
