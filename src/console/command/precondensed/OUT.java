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
			
			s += args[i] + " ";
		}
		return new console.command.condensed.OUT(s);
	}

	private static String getEnvVarSubString(String s)
	{
		return s.substring(1,s.length());
	}
}
