package console.command.precondensed;

import console.command.condensed.ExecutableCommand;

public class EXE extends PrecondensedCommand {

	public EXE(String path)
	{
		args = path.split(" ");
	}
	
	@Override
	public ExecutableCommand lex() 
	{
		return  new console.command.condensed.EXE(args[1]);
	}

}
