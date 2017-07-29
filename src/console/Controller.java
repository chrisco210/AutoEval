package console;

import autoEval.gui.GUI;
import console.command.Variable;
import console.command.precondensed.CommandFactory;

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
	
	public void stringExec(String exec)
	{
		GUI.console.dbg("Controller.stringExec called");
		execStack.quickExec(
				CommandFactory.createCommand(exec).lex()
				);
	}
}
