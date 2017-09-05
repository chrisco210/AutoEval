package cf.rachlinski.autoEval.console.command;

/**
 * Variable for the variable stack in the console
 * @param <E> the data type
 */
public class Variable<E> {
	public E value;		//The value of the variable
	/**
	 * @deprecated
	 */
	private int pointer;
	private String name;

	/**
	 * Construct a new variable
	 * @param val the value of the variable
	 * @param name the name of the variable
	 */
	public Variable(E val, String name)
	{
		value = val;
		this.name = name;
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

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "Variable{" +
				"name=" + name
				+ ",value=" + value +
				'}';
	}
}
