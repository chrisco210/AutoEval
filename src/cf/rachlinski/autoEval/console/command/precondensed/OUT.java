package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;

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
		return new cf.rachlinski.autoEval.console.command.condensed.OUT(s);
	}

	private static String getEnvVarSubString(String s)
	{
		return s.substring(1,s.length());
	}
}
