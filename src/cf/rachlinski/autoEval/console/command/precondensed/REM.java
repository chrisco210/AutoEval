package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;

public class REM extends PrecondensedCommand {

	@Override
	public ExecutableCommand lex() {
		return new cf.rachlinski.autoEval.console.command.condensed.REM();
	}

}
