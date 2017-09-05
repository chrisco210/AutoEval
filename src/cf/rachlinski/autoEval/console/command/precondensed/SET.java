package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;
import cf.rachlinski.autoEval.gui.ConsolePane;

import javax.lang.model.type.NullType;

public class SET extends PrecondensedCommand
{
	public SET(String text)
	{
		args = text.split(" ");
	}


	@Override
	public ExecutableCommand lex()
	{
		ConsolePane.dbg("Precondensed: " + args[1] + "," + args[2] + "," + args[3]);

		switch(args[1].trim())
		{
		case "int":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<Integer>(Integer.parseInt(args[3].trim()), args[2]));
		case "string":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<String>(args[3], args[2]));
		case "float":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<Float>(Float.parseFloat(args[3]), args[2]));
		case "char":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<Character>(args[3].charAt(0), args[2]));
		default:
			ConsolePane.dbg("Creating null object.");
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<NullType>(null, args[2]));
		}
	}
}
