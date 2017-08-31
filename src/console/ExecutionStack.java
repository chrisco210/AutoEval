package console;

import autoEval.gui.ConsolePane;
import autoEval.gui.GUI;
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
	
	public ExecutableCommand[] getExecutionStack()
	{
		return executionStack;
	}
	
	public int getIP()
	{
		return ip;
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
		ConsolePane.dbg("console.ExecutionStack.quickExec called");		//debug
		FLUSH();		//Flush the exection stack
		JMP(0);		//Jump to location 0
		PUSH(c);		//Push the command c onto location 0
		RUN();		//Run location 0
		
	}
	
	protected void RUN()
	{
		ConsolePane.dbg("Running instruction at " + ip);
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
		FLUSH();		//Clear the stack
		executionStack = commandList;		//Update executions tack tomatch the command list
	}
	
	protected void FLUSH()
	{
		executionStack = new ExecutableCommand[1];
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
		if(executionStack.length == 1)
			executionStack[0] = null;
		executionStack[executionStack.length - 1] = null;
		
	}
	
	/*--------- Instruction pointer methods ---------*/
	
	/**
	 * Jump to an instruction
	 * @param pointer the location in the execution stack to jump to
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
