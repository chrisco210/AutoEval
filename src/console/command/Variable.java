package console.command;

public class Variable<E> {
	public E value;		//The value of the variable
	
	private int pointer;
	
	public Variable(E val)
	{
		value = val;
	}
	
	public void setPointer(int p)
	{
		pointer = p;
	}
	
	public int getPointer()
	{
		return(pointer);
	}
}
