package console;

import java.util.HashMap;

import console.command.Variable;

/**
 * This is kind of a variable map now, but lets not talk about it
 * @author Christopher
 *
 */
public class VariableStack {
	
	/**
	 * HashMap containing the variables with string accessors
	 */
	private HashMap<String, Variable<?>> variableStack;
	
	/**
	 * Constructor with no initialization values
	 */
	public VariableStack()
	{
		 variableStack = new HashMap<String, Variable<?>>();
	}
	
	/**
	 * Constructor with an initialization value
	 * @param init
	 */
	public VariableStack(HashMap<String, Variable<?>> init)
	{
		variableStack = init;
	}
	
	/* Getters and Setters */
	
	/**
	 * Get areaSelector variable from the hashmap by areaSelector string name
	 * @param variableName
	 * @return the variable class of the specified name
	 */
	public Variable<?> get(String variableName)
	{
		return variableStack.get(variableName);
	}
	
	/**
	 * Set the variable stack HashMap to the one provided
	 * @param map the map to use
	 */
	public void setAll(HashMap<String, Variable<?>> map)
	{
		variableStack = map;
	}
	
	/**
	 * Set a variable in the hashmap
	 * @param v
	 * @param name
	 */
	public void set(Variable<?> v, String name)
	{
		variableStack.put(name, v);
	}
	
	/* Variable Removing Functions */
	
	/**
	 * Delete all variables
	 */
	public void clear()
	{
		variableStack.clear();
	}
	
	/**
	 * Remove a variable from the stack
	 * @param name the name to remove
	 */
	public void free(String name)
	{
		variableStack.remove(name);
	}
}
