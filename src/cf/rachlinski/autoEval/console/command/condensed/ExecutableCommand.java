package cf.rachlinski.autoEval.console.command.condensed;

public interface ExecutableCommand {
	/**
	 * Function to execute the command specified
	 * @return the success value of the operation
	 */
	public abstract int execute();
}
