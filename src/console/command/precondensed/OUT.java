package console.command.precondensed;

import console.command.condensed.ExecutableCommand;

public class OUT extends PrecondensedCommand {
	private String[] args;
	
	public OUT(String outText)
	{
		args = outText.split(" ");
	}
	
	@Override
	public ExecutableCommand lex() {
		String s = "";
		for(int i = 1; i < args.length; i++)
			s += args[i] + " ";
		return new console.command.condensed.OUT(s);
	}

}
