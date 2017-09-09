package cf.rachlinski.autoEval.console.command.condensed;

/**
 * Command to write text to the console
 * Usage: OUT Text to display on screen
 * @author Christopher
 *
 */
public class OUT implements ExecutableCommand {
	private int[] variableLocations;

	private String outText;
	
	public OUT(String text)
	{
		outText = text;
	}
	
	@Override
	public int execute() {
		System.out.println(outText);
		return 1;
	}
}
