package console.command.condensed;

import console.VariableStack;

/**
 * Command to set the variable stack size
 * Usage: ALLOC SIZE_OF_STACK_AS_INT
 * @author Christopher
 *
 */
public class ALLOC extends ExecutableCommand {
	private int size;
	
	public ALLOC(int size)
	{
		this.size = size;
	}
	
	@Override
	public int execute() {
		VariableStack.setStackSize(size);
		return 1;
	}

	

}
