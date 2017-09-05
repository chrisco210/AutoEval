package cf.rachlinski.autoEval.console.command.precondensed;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.gui.GUI;
import cf.rachlinski.autoEval.util.MalformedCommandsXml;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cf.rachlinski.autoEval.console.scripting.DefaultScripts;


public class CommandFactory {

	/**
	 * Factory to create a command based on user input
	 * @param text The text to decide what command to create
	 * @return A PrecondensedCommand of the desired type, based on command precedence.  If an unrecognized command is entered, a REM command will be returned
	 */
	public static PrecondensedCommand createCommand(String text)
	{
		ConsolePane.dbg("CommandFactory.createCommand called");	//Debug

		//Check for any debug commands
		if(GUI.debug)
		{
			if(text.equals("DMPUVARSTACK"))
				System.out.println(ConsolePane.controller.getUserVars().getVarStack().toString());

		}

		//The name of the command requested (Which should be the first thing they typed
		String nameCommandRequested = text.split(" ")[0];

		//There should be no exception thrown here, as the file we are reading is a resource.
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(CommandFactory.class.getResourceAsStream("/resources/commands.xml"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Warning! Failed to read commands.xml.  \n" +
					"If you have modified files inside the jar, please undo these changes and restart the program.  \n");
			throw new MalformedCommandsXml();
		}

		//Get a list of all the command nodes
		NodeList n = doc.getElementsByTagName("command");

		//This is where the desired node will be stored
		String className;

		//Use XPath to get the contents of the class node
		try
		{
			ConsolePane.dbg(nameCommandRequested);
			className =  (String) XPathFactory.newInstance().newXPath().compile(
					"/commands/command[@name='" + nameCommandRequested + "']/class").evaluate(doc, XPathConstants.STRING
			);
		}
		catch (XPathExpressionException e)
		{
			e.printStackTrace();
			throw new MalformedCommandsXml();
		}

		ConsolePane.dbg("Class name: " + className);

		try		//Attempt to return the precondensed command
		{
			return (PrecondensedCommand) Class.forName(className).getConstructor(String.class).newInstance(text);
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
			System.err.println("Failed to instantiate the PrecondensedCommand. You should have a constructor that accepts the arguments String.");
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
			System.err.println("Error in creating PrecondensedCommand. Your constructor signature should not throw any exceptions.");
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
			System.err.println("Your PrecondensedCommand is missing the correct constructor.  You should have constructor ClassName(String s)");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			System.err.println("Error parsing commands.xml.  Incorrect class name.  Ensure you have typed the fully qualified name of a precondensed command");
		}

		throw new MalformedCommandsXml();

	}
}