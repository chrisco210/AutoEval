package console.command.condensed;

public abstract class ExecutableCommand {
	public String[] args;
	
	/**
	 * Function to execute the command specified
	 * @return the success value of the operation
	 */
	protected abstract int execute(String[] args);
}
