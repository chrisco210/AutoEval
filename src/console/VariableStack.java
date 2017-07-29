package console;

import console.command.Variable;

public class VariableStack {
	private Variable<?>[] variableStack = new Variable<?>[0];
	private int esp = 0;
	
	
	/**
	 * Updates the size of the variable stack.  If decreasing the stack size, variables that exceed the new 
	 * size of the stack will be removed
	 * @param s The new size of the stack
	 */
	public void setStackSize(int s)
	{
		Variable<?>[] temp = variableStack;		//Store the variable stack temporarily so they are the same when done.
		esp = s;		//Update the stack size
		
		variableStack = new Variable<?>[esp];		//This is where the stack size is increased
		
		/*
		for(int i = 0; i < variableStack.length; i++)		//Update all elements, deleting ones that exceed the new size of the stack, in the case of a decrement
			variableStack[i] = temp[i];
		*/
	}
	
	/**
	 * Set the variable stack array to the one provided
	 * @param vS the array of variables to use
	 */
	public void set(Variable<?>[] vS)
	{
		variableStack = vS;
	}
	
	/**
	 * Removes all elements from the variable stack, and sets it to null
	 */
	public void flushStack()
	{
		esp = 0;
		
		variableStack = null;
	}
	
	
	/**
	 * Adds a variable to the variable stack
	 * @param v The variable to add to the stack
	 * @param pointer The stack location to add the variable to
	 * @throws StackOverflowException if the pointer is not in bounds
	 */
	public void set(Variable<?> v, int pointer)
	{
		if(pointer > esp)
			throw new StackOverflowError();			//Write this exception in AutoEvalScript
		
		variableStack[pointer] = v;
	}
	
	/**
	 * Gets a variable from the stack
	 * @param pointer The stack location of the requested variable
	 * @return The variable class at the specified pointer
	 */
	public Variable<?> get(int pointer)
	{
		if(pointer > esp)
			return(null);
		return(variableStack[pointer]);
	}
	
	/**
	 * 
	 * @param v The variable to find
	 * @return The memory location of the first occurence of the element.  Returns -1 if none are found
	 */
	public int GETLOC(Variable<?> v)
	{
		for(int i = 0; i < variableStack.length; i++)
			if(variableStack[i].value == v.value)
				return(i);
		return -1;
	}
	
	/**
	 * Switches the location of two elements in the variable stack
	 * @param pointer1 A pointer to one location to swap
	 * @param pointer2 A pointer to the second location to swap
	 */
	public void MEMSWAP(int pointer1, int pointer2)
	{
		if(pointer1 > esp || pointer2 > esp)
			return;		//Write an exception in AutoEvalScript
		
		Variable<?> var1Temp = get(pointer1);
		Variable<?> var2Temp = get(pointer2);
		
		set(var2Temp, pointer1);
		set(var1Temp, pointer2);
	}
	
	/**
	 * Moves the value of pointer1 to pointer2, and sets pointer1 to null.  Note: The value at location pointer2 will be overwritten
	 * @param pointer1 A pointer to the variable to move
	 * @param pointer2 A pointer to the new memory location
	 */
	public void MOV(int pointer1, int pointer2)
	{
		if(pointer1 > esp || pointer2 > esp)
			return;		//Write an exception in AutoEvalScript
		set(get(pointer1), pointer2);
		set(null, pointer1);
	}
}
