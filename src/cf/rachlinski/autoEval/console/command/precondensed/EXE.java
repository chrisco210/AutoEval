package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;

public class EXE extends PrecondensedCommand {

	public EXE(String path)
	{
		args = path.split(" ");
	}
	
	@Override
	public ExecutableCommand lex() 
	{
		boolean usePrecondensed = Boolean.parseBoolean(args[2]);

		return  new cf.rachlinski.autoEval.console.command.condensed.EXE(args[3], usePrecondensed);
	}

}
