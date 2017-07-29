package console.command.condensed;

/**
 * Command to comment a line
 * Usage: REM this line will not be read
 * @author Christopher
 *
 */
public class REM implements ExecutableCommand {

	public REM()
	{
		
	}
	
	@Override
	public int execute() {
		return 1;
	}

}
