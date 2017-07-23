package console;

import console.command.condensed.*;

public class ExecutionStack {
	private ExecutableCommand[] executionStack;		//execution stack
	private int ip = 0;		//Instruction pointer
	
	/**
	 * Constructor to set size of execution stack
	 * @param size the size of the execution stack
	 */
	public ExecutionStack(int size)
	{
		executionStack = new ExecutableCommand[size];
	}
	
	
	
	
	
	/*------------- Execution --------------*/
	
	/**
	 * This method is designed to be able to execute a single command
	 * This works by pushing an executablecommand onto the stack, executing it,
	 * and then popping it off of the stack.  Calling this method will unload ANY
	 * script that has been loaded into the execution stack.
	 * 
	 * @param c The command to execute
	 */
	public void quickExec(ExecutableCommand c)
	{
		FLUSH();		//Flush the exection stack
		JMP(0);		//Jump to location 0
		PUSH(c);		//Push the command c onto location 0
		RUN();		//Run location 0
		POP();		//Pop location 0 off the stack
		
	}
	
	protected void RUN()
	{
		executionStack[ip].execute();
	}
	
	/*----------- Stack control --------------*/
	
	/**
	 * Add a list of commands to the execution stack.  This method overwrites the current executionstack
	 * Use this to load scripts
	 * @param commandList The commands to add
	 */
	public void pushAll(ExecutableCommand[] commandList)
	{
		executionStack = null;		//TODO use the flush method instead of this
		executionStack = commandList;
	}
	
	private void FLUSH()
	{
		executionStack = new ExecutableCommand[0];
	}
	
	/**
	 * Push a command onto the stack at the location of the isntruction pointer
	 * @param c the command to push onto the stack
	 */
	private void PUSH(ExecutableCommand c)
	{
		executionStack[ip] = c;
	}
	
	/**
	 * Pop the top of the stack 
	 */
	private void POP()
	{
		executionStack[executionStack.length - 1] = null;
		
	}
	/*--------- Instruction pointer methods ---------*/
	
	/**
	 * Jump to an instruction
	 * @param pointer
	 */
	protected void JMP(int pointer)
	{
		ip = pointer;
	}
	
	/**
	 * Increment the isntruction pointer
	 */
	protected void INC()
	{
		ip++;
	}
	
	
}