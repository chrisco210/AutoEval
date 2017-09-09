package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;
import cf.rachlinski.autoEval.gui.ConsolePane;

public class OUT extends PrecondensedCommand {
	;

	public OUT(String outText)
	{
		args = outText.split(" ");
	}
	
	@Override
	public ExecutableCommand lex() {
		String s = "";

		for(int i = 1; i < args.length; i++)
		{
			if(args[i].charAt(0) == '$')
			{
				String variableName = args[i].substring(1);

				try
				{
					args[i] = ConsolePane.controller.getUserVars().get(variableName).getValue().toString();
				}
				catch (NullPointerException e)
				{
					args[i] = "null";
				}
			}

			s += args[i] + " ";
		}

		return new cf.rachlinski.autoEval.console.command.condensed.OUT(s);
	}
}
