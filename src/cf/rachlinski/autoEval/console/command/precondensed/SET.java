package cf.rachlinski.autoEval.console.command.precondensed;

import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;

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
		switch(args[0])
		{
		case "int":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<Integer>(Integer.parseInt(args[2]), args[1]));
		case "string":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<String>(args[2], args[1]));
		case "float":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<Float>(Float.parseFloat(args[2]), args[1]));
		case "char":
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<Character>(args[2].charAt(0), args[1]));
		default:
			return new cf.rachlinski.autoEval.console.command.condensed.SET(new Variable<NullType>(null, args[1]));
		}
	}
}
