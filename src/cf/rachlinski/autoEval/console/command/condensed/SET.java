package cf.rachlinski.autoEval.console.command.condensed;

import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.gui.ConsolePane;

/**
 * Command to set the value of a variable
 * Usage: SET VariableName value
 * @author Christopher
 *
 */
public class SET implements ExecutableCommand {
	private Variable<?> set;

	public SET(Variable<?> variable)
	{
		this.set = variable;
	}
	
	@Override
	public int execute()
	{
		return 1;
	}

}
