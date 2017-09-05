package cf.rachlinski.autoEval.console.scripting;

import java.io.FileWriter;
import java.io.IOException;

public class DefaultScripts {
	public static Script CMD_NOT_RECOGNIZED = null;

	static
	{
		try
		{
			CMD_NOT_RECOGNIZED = new Script(DefaultScripts.class.getResource("/resources/scripts/cmdnotfound.aesc").getFile());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Warning! Failed to initialize DefaultScripts class.  \n" +
					"If you have modified files inside the jar, please undo these changes and restart the program.  \n" +
					"Dumping this log to AutoEval_Error_Log in the user profile.");
			try
			{
				FileWriter f = new FileWriter(System.getProperty("user.home") + "\\AutoEval_Error_Log_" + System.currentTimeMillis() + ".txt");
				f.write(e.getLocalizedMessage());
				f.write(e.getMessage());
				f.close();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
				System.err.println("Failed to dump error log");
			}
			System.exit(0);
		}
	}
}
