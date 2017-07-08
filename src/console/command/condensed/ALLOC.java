package console.command.condensed;

import console.VariableStack;

/**
 * Command to set the variable stack size
 * Usage: ALLOC SIZE_OF_STACK_AS_INT
 * @author Christopher
 *
 */
public class ALLOC extends ExecutableCommand {

	@Override
	protected int execute(String[] args) {
		if(args.length > 1)		//Make sure there is only one argument
			return(0);
		for(Character c : args[0].toCharArray())		//Make sure all charachters are digits
			if(Character.isDigit(c))
				return(0);
		int toSize = Integer.parseInt(args[0]);		//Convert string 
		VariableStack.setStackSize(toSize);
		return 1;
	}

}
