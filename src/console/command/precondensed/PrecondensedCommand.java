package console.command.precondensed;

import console.command.condensed.ExecutableCommand;

public abstract class PrecondensedCommand {
	public String[] args;
	
	public abstract ExecutableCommand lex();
}
