package console.command.condensed;

import console.Script;
import autoEval.gui.GUI;

/**
 * Command to execute a script
 * Usage: EXE PATH_TO_SCRIPT
 * @author Christopher
 *
 */
public class EXE implements ExecutableCommand {
	private String path;
	
	public EXE(String scriptPath)
	{
		path = scriptPath;
	}
	
	@Override
	public int execute() 
	{
		GUI.console.dbg("Executing Script at " + path);
		
		try 
		{
			GUI.console.dbg("Executing script.");
			GUI.console.controller.exec(new Script(path));
			return 1;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
}
