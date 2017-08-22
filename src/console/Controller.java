package console;

import autoEval.gui.GUI;
import console.command.Variable;
import console.command.condensed.ExecutableCommand;
import console.command.precondensed.CommandFactory;
import console.command.precondensed.PrecondensedCommand;

public class Controller {
	private VariableStack userVar = new VariableStack();
	private VariableStack envVar = new VariableStack();
	private ExecutionStack execStack;
	
	/**
	 * Create a new command controller
	 * @param initUVarSize the intial size of the user variable stack
	 * @param envVars Environment variables to use
	 * @param initExecStackSize the intial size of the execution stack
	 */
	public Controller(int initUVarSize, Variable<?>[] envVars, int initExecStackSize)
	{
		execStack = new ExecutionStack(initExecStackSize);
		
		userVar.setStackSize(initUVarSize);
		envVar.set(envVars);
		
		execStack = new ExecutionStack(initExecStackSize);
	}
	
	/**
	 * Execute a command based on a string
	 * @param exec the command to execute as a string
	 */
	public void exec(String exec)
	{
		GUI.console.dbg("Controller.stringExec called");
		execStack.quickExec(
				CommandFactory.createCommand(exec).lex()
				);
	}
	
	/**
	 * Execute a precondensed command
	 * @param c the command to execute as a PrecondensedCommand
	 */
	public void exec(PrecondensedCommand c)
	{
		execStack.quickExec(c.lex());
	}
	
	/**
	 * Execute an ExecutableCommand
	 * @param c the command to execute as an ExecutableCommand
	 */
	public void exec(ExecutableCommand c)
	{
		execStack.quickExec(c);
	}
	
	public void exec(Script s)
	{
		ExecutableCommand[] oldExecStack = execStack.getExecutionStack();
		int oldPointer = execStack.getIP();
		
		execStack.FLUSH();
		execStack.JMP(0);
		execStack.pushAll(s.getCommandArray());
		GUI.console.dbg(s.getCommandArray());
		
		for(int i = 0; i < s.size(); i++)
		{
			execStack.RUN();
			execStack.INC();
		}
		
		execStack.FLUSH();
		execStack.pushAll(oldExecStack);
		execStack.JMP(oldPointer);
	}
	
	/**
	 * Get the user variable stack
	 * @return the user variable stack
	 */
	public VariableStack getUserVars()
	{
		return userVar;
	}
	
	/**
	 * Get the environmental variables
	 * @return the environmental variable stack
	 */
	public VariableStack getEnvironmentVars()
	{
		return envVar;
	}
}
