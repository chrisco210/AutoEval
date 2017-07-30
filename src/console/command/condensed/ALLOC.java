package console.command.condensed;

import console.command.Variable;
import autoEval.gui.GUI;

/**
 * Command to set the user variable stack size
 * Usage: ALLOC SIZE_OF_STACK_AS_INT
 * @author Christopher
 *
 */
public class ALLOC implements ExecutableCommand {
	private int size;
	
	public ALLOC(int size)
	{
		this.size = size;
	}
	
	@Override
	public int execute() 
	{
		GUI.console.controller.getEnvironmentVars().set(new Variable<Integer>(size), 0);	
		GUI.console.controller.getUserVars().setStackSize(size);
		return 1;
	}


}
