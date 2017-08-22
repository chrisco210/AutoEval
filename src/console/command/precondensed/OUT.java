package console.command.precondensed;

import autoEval.gui.GUI;
import console.command.condensed.ExecutableCommand;
import console.scripting.EnvironmentConstants;

public class OUT extends PrecondensedCommand {
	public OUT(String outText)
	{
		args = outText.split(" ");
	}
	
	@Override
	public ExecutableCommand lex() {
		String s = "";
		for(int i = 1; i < args.length; i++)
		{
			/*
			 * ENVIRONMENTAL VARIABLE STACK SETUP
			 * TODO change environment variables to an Associative array
			 * 0 - User variable stack size
			 * 1 - Execution root path
			 */
			if(args[i].charAt(0) == '@')		//Check if the user is trying to access an environmental variable
			{
				if(getEnvVarSubString(args[i]).equals("USER_VAR_STACK_SIZE"))
					args[i] = ((Integer) GUI.console.controller.getEnvironmentVars().get(0).value).toString();		//Get the stack size variable, and convert it to a string
				if(getEnvVarSubString(args[i]).equals("PROGRAM_ROOT"))
					args[i] = EnvironmentConstants.PROGRAM_ROOT;		//TODO Use an actual environment variable, just doing this fornow
					
			}
			
			s += args[i] + " ";
		}
		return new console.command.condensed.OUT(s);
	}

	private static String getEnvVarSubString(String s)
	{
		return s.substring(1,s.length());
	}
}
