package cf.rachlinski.autoEval.console.command.precondensed;

import java.io.IOException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import cf.rachlinski.autoEval.gui.ConsolePane;
import org.w3c.dom.Document;
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
		
		//TODO make this better
		if(isCommand("OUT", text))
			return new OUT(text);
		else if(isCommand("REM", text))
			return new REM();
		else if(isCommand("EXE", text))
			return new EXE(text);
		
		Document doc = null;
		
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(DBGXML.XML);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeList n = doc.getElementsByTagName("command");
		
		for(int i = 0; i < n.getLength(); i++)
		{
			//if( ((Element) n.item(i).getChildNodes()). )
		}
		
		ConsolePane.controller.exec(DefaultScripts.CMD_NOT_RECOGNIZED);
		return new REM();
	}

	private static boolean isCommand(String commandName, String commandText)
	{
		return commandText.substring(0, commandName.length()).equals(commandName);
	}

	static class DBGXML {
		public static String XML = ""
				+ "<commands>"
				+ "<command>"
				+ "<name>out</name>"
				+ "<class>console.command.precondensed.OUT</class>"
				+ "</command>"
				+ "</commands>";
	}
}