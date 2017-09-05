package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;

public class REM extends PrecondensedCommand {
	public REM(String s)
	{
	}
	public REM()
	{

	}
	@Override
	public ExecutableCommand lex() {
		return new cf.rachlinski.autoEval.console.command.condensed.REM();
	}

}
