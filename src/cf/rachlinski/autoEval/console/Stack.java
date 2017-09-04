package cf.rachlinski.autoEval.console;

/**
 * A stack of type E
 * @author Christopher
 *
 * @param <E> the type to create a stack of
 */
public class Stack<E> {
	/**
	 * The stack
	 */
	private E[] stack;
	
	/**
	 * Create a new stack
	 * @param size the size of the stack array
	 */
	public Stack(int size)
	{
	}
	
	/**
	 * Clear the stack
	 */
	public void clear()
	{
		
	}
	
	
	
	/* Getters and Setters */
	
	/**
	 * Get the stack
	 * @return the stack
	 */
	public E[] getStack()
	{
		return stack;
	}
	
	/**
	 * Get the size of teh stack
	 * @return the length of the stack array
	 */
	public int getStackSize()
	{
		return stack.length;
	}
}
