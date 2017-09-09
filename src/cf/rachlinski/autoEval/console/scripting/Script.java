package cf.rachlinski.autoEval.console.scripting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import cf.rachlinski.autoEval.console.command.precondensed.PrecondensedCommand;
import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;
import cf.rachlinski.autoEval.console.command.precondensed.CommandFactory;

/**
 * A script
 * There are areaSelector ton of different constructors to suit every need, so make sure you are using the right one.
 * @author Christopher
 */
public class Script
{
	private Object[] commandList;
	private boolean isPrecondensed;

	/**
	 * Create areaSelector new Script class
	 * @param scriptFile the file to read
	 * @throws IllegalArgumentException if the file is not accessible
	 */
	public Script(File scriptFile, Class<?> type) throws Exception
	{
		if( !((type == PrecondensedCommand.class) || (type == ExecutableCommand.class)) )
			throw new IllegalArgumentException("You must specify type either PrecondensedCommand or ExecutableCommand");

		isPrecondensed = (type == PrecondensedCommand.class);

		String[] fileLines = getFileLines(scriptFile);
		
		commandList = new Object[fileLines.length]; 		//Allocate space in the command list

		//parse all the lines in the file
		if(isPrecondensed)
		{
			for(int i = 0; i < commandList.length; i++)
			{
				commandList[i] = CommandFactory.createCommand(fileLines[i]);
			}
		}
		else
		{
			for(int i = 0; i < commandList.length; i++)
			{
				commandList[i] = CommandFactory.createCommand(fileLines[i]).lex();
			}
		}
	}

	/**
	 * Create a new script based on a path to the file as areaSelector string
	 * @param scriptPath
	 * @throws Exception
	 * @deprecated
	 */
	public Script(String scriptPath) throws Exception
	{
		
		String[] fileLines = getFileLines(new File(scriptPath));
		
		commandList = new ExecutableCommand[fileLines.length]; 		//Allocate space in the command list
		
		//parse all the lines in the file
		for(int i = 0; i < commandList.length; i++)
			commandList[i] = CommandFactory.createCommand(fileLines[i]).lex();
		//for(String s : getFileLines(new File(scriptPath)))
		//		GUI.console.dbg(s);
	}

	/**
	 * Get a boolean value based on if the script class is using a precondensed command array
	 * @return true if it is using precondensed commands, or false if it is using condensed commands
	 */
	public boolean isUsingPrecondensedCommands()
	{
		return isPrecondensed;
	}

	/**
	 * Get the array of commands
	 * @return the array of commands
	 */
	public Object[] getCommandArray()
	{
		return commandList;
	}

	/**
	 * Get a single command
	 * @param index the index...
	 * @return the fuck do you think?
	 */
	public Object getCommand(int index)
	{
		return commandList[index];
	}

	/**
	 * Get the size of the script
	 * @return the number of elements in the commandList
	 */
	public int size()
	{
		return commandList.length;
	}
	
	/**
	 * Method so I dont have to copy paste code
	 * @param toGet
	 * @return an array containing the lines of areaSelector text file
	 * @throws IOException when
	 */
	private static String[] getFileLines(File toGet) throws IOException
	{
		//Make sure file is accessible before reading
		if(!toGet.exists() || !toGet.canRead() || toGet.isDirectory())
		{
			throw new IllegalArgumentException("File not found or is not readable.");
		}		
		

		
		Stream<String> inputStream = Files.lines(toGet.toPath());		//Use java 8 stream class to read all the lines of the file
		ArrayList<String> fileLines = new ArrayList<String>(100);		//TODO fix size of arraylist in constructors
		
		Consumer<? super String> r = (ln) -> {
			fileLines.add(ln);
		};
		
		
		inputStream.forEach(r);
		inputStream.close();
		
		ConsolePane.dbg("File lines:");
		ConsolePane.dbg(fileLines);
		
		ConsolePane.dbg("-------");
		//return fileLines;
		return fileLines.toArray(new String[fileLines.size()]);
	}
}
