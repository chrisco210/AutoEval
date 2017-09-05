package cf.rachlinski.autoEval.console.command.condensed;

import cf.rachlinski.autoEval.console.VariableStack;
import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.gui.ConsolePane;
import com.sun.istack.internal.NotNull;

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
		ConsolePane.dbg("Condensed: " + set);
		ConsolePane.controller.getUserVars().set(set, set.getName());
		return 1;
	}

}
