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
		return  new cf.rachlinski.autoEval.console.command.condensed.EXE(args[1]);
	}

}
