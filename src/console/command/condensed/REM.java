package console.command.condensed;

/**
 * Command to comment a line
 * Usage: REM this line will not be read
 * @author Christopher
 *
 */
public class REM extends ExecutableCommand {
	public String[] args;
	
	public static String desc = "Allows for the user to make remarks on a line without it being processed";
	
	public REM()
	{
		
	}
	
	protected int execute(String[] args) 
	{
		return 1;		//This command does nothing
	}

}
