package cf.rachlinski.autoEval.console.command.condensed;

import cf.rachlinski.autoEval.console.command.precondensed.PrecondensedCommand;
import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.console.scripting.Script;

import java.io.File;

/**
 * Command to execute a script
 * Usage: EXE PATH_TO_SCRIPT
 * @author Christopher
 *
 */
public class EXE implements ExecutableCommand {
	private String path;
	private boolean usePrecondensed;

	public EXE(String scriptPath, boolean usePrecondensed)
	{
		this.usePrecondensed = usePrecondensed;
		path = scriptPath;
	}
	
	@Override
	public int execute() 
	{
		try 
		{
			if(usePrecondensed)
			{
				ConsolePane.controller.exec(new Script(new File(path), PrecondensedCommand.class));
			}
			else
			{
				ConsolePane.controller.exec(new Script(new File(path), ExecutableCommand.class));
			}

			return 1;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
}
