package cf.rachlinski.autoEval.console;

import java.util.HashMap;

import cf.rachlinski.autoEval.console.scripting.Script;
import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;
import cf.rachlinski.autoEval.console.command.precondensed.CommandFactory;
import cf.rachlinski.autoEval.console.command.precondensed.PrecondensedCommand;

public class Controller {
	private VariableStack userVar;
	private VariableStack envVar;
	private ExecutionStack execStack;
	
	/**
	 * Create a new command controller
	 * @param envVars an array of environment variables to add
	 * @param envVarNames the names of the environment variables to add.  Each index of envVars should have an entry in envVarNames
	 * @param initExecStackSize the intial size of the execution stack
	 *
	 * @throws IllegalArgumentException if each environmental variable does not have a corresponding name
	 */
	public Controller(Variable<?>[] envVars, String[] envVarNames, int initExecStackSize)
	{
		if(envVars.length != envVarNames.length)
			throw new IllegalArgumentException();

		execStack = new ExecutionStack(initExecStackSize);
		
		userVar = new VariableStack();
		
		HashMap<String, Variable<?>> environmentHashMap = new HashMap<String, Variable<?>>();
		for(int i = 0; i < envVars.length; i++)
		{
			environmentHashMap.put(envVarNames[i], envVars[i]);
		}
	}
	
	/**
	 * Execute areaSelector command based on areaSelector string
	 * @param exec the command to execute as a string
	 */
	public void exec(String exec)
	{
		ConsolePane.dbg("Controller.stringExec called");
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
		ConsolePane.dbg(s.getCommandArray());
		
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
