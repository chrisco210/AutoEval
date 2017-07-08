package console.command.precondensed;

import console.command.condensed.ExecutableCommand;

public abstract class PrecondensedCommand {
	protected String commandText;
	
	protected abstract ExecutableCommand compile();
}
