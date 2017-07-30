package console.command.precondensed;

import autoEval.gui.GUI;
import console.command.condensed.ExecutableCommand;

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
			 * 0 - User variable stack size
			 */
			if(args[i].charAt(0) == '@')		//Check if the user is trying to access an environmental variable
			{
				if(args[i].substring(1,args[i].length()).equals("USER_VAR_STACK_SIZE"))
					args[i] = ((Integer) GUI.console.controller.getEnvironmentVars().get(0).value).toString();		//Get the stack size variable, and convert it to a string
			}
			
			s += args[i] + " ";
		}
		return new console.command.condensed.OUT(s);
	}

}
