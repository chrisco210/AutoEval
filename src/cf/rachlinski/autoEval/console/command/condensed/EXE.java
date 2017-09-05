package cf.rachlinski.autoEval.console.command.condensed;

import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.console.scripting.Script;

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
		ConsolePane.dbg("Executing Script at " + path);
		
		try 
		{
			ConsolePane.dbg("Executing script.");
			ConsolePane.controller.exec(new Script(path));
			return 1;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
}
