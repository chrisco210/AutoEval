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
			s += args[i] + " ";
		}

		return new cf.rachlinski.autoEval.console.command.condensed.OUT(s);
	}
}
