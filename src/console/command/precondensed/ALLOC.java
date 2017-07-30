package console.command.precondensed;

import console.command.condensed.ExecutableCommand;

public final class ALLOC extends PrecondensedCommand {
	public ALLOC(String text)
	{
		args = text.split(" ");
	}
	
	@Override
	public ExecutableCommand lex() {
		return new console.command.condensed.ALLOC(Integer.parseInt(args[1]));
	}

}
