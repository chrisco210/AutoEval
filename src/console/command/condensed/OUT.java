package console.command.condensed;

import autoEval.gui.GUI;

/**
 * Command to write text to the console
 * Usage: OUT Text to display on screen
 * @author Christopher
 *
 */
public class OUT implements ExecutableCommand {

	private String outText;
	
	public OUT(String text)
	{
		outText = text;
	}
	
	@Override
	public int execute() {
		GUI.console.log(outText);
		return 1;
	}


}
