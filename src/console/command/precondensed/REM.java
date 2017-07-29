package console.command.precondensed;

import console.command.condensed.ExecutableCommand;

public class REM extends PrecondensedCommand {

	@Override
	public ExecutableCommand lex() {
		return new console.command.condensed.REM();
	}

}
