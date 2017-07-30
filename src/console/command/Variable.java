package console.command;

public class Variable<E> {
	public E value;		//The value of the variable
	private int pointer;
	
	
	public Variable(E val)
	{
		value = val;
	}
	
	/**
	 * Get the value of the variable
	 * @return the value of the variable
	 */
	public E getValue()
	{
		return value;
	}
	
	/**
	 * Set the pointer to the variable. This method does not change the location of it in the variable stack, only the reference in the instance.
	 * @param p the new pointer
	 */
	public void setPointer(int p)
	{
		pointer = p;
	}
	
	/**
	 * Get the pointer to the variable
	 * @return the memory location as an integer
	 */
	public int getPointer()
	{
		return(pointer);
	}
}
