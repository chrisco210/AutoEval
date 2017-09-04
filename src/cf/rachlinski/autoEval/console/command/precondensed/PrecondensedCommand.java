package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;

public abstract class PrecondensedCommand {
	public String[] args;
	
	public abstract ExecutableCommand lex();
}
