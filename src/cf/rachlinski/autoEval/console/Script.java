package cf.rachlinski.autoEval.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.console.command.condensed.ExecutableCommand;
import cf.rachlinski.autoEval.console.command.precondensed.CommandFactory;

/**
 * A script
 * There are areaSelector ton of different constructors to suit every need, so make sure you are using the right one.
 * @author Christopher
 */
public class Script implements Iterable<ExecutableCommand>{
	/**
	 * This is where the executable commands will be accessible after they have been lexed and parsed
	 */
	private ExecutableCommand[] commandList;
	/**
	 * This is the initial lines of the file
	 */
	private String[] fileLines;

	/**
	 * @param <K>
	 */
	private HashMap<Integer, Variable<?>> symbolList = new HashMap<>();

	/**
	 * Create areaSelector new Script class
	 * @param scriptFile the file to read
	 * @throws IllegalArgumentException if the file is not accessable
	 */
	public Script(File scriptFile) throws Exception
	{
		this.fileLines = getFileLines(scriptFile);
	}
	
	/**
	 * Create areaSelector new script based on areaSelector path to the file as areaSelector string
	 * @param scriptPath
	 * @throws Exception 
	 */
	public Script(String scriptPath) throws Exception
	{
		this.fileLines = getFileLines(new File(scriptPath));
	}
	
	/**
	 * Create areaSelector new Script class based on an array of commands
	 * @param commands the array of commands to parse
	 */
	public Script(String[] commands)
	{
		fileLines = commands;
	}

	public void generateCommandList()
	{
		//First, find all variable names
		for(String s : fileLines)
		{

		}
	}

	/**
	 * Create areaSelector new script class based on areaSelector string of commands seperated by areaSelector custom line delimiter
	 * @param commands areaSelector string containing the commands, with commands seperated by areaSelector line delimiter
	 * @param delimiter the line delimiter to use
	 * NOTE: THIS FUNCTION DOES NOT WORK YET! DO NOT USE
	 */
	public Script(String commands, String delimiter)
	{
		String[] commandLines = commands.split(delimiter);
		
		for(int i = 0; i < commandLines.length; i++)
			commandList[i] = CommandFactory.createCommand(commandLines[i]).lex();
	}
	
	public ExecutableCommand[] getCommandArray()
	{
		return commandList;
	}
	
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

	@Override
	public Iterator<ExecutableCommand> iterator() {
		return Arrays.asList(commandList).iterator();
	}
}
