package console.command.precondensed;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;







import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import autoEval.gui.ConsolePane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import console.command.condensed.ExecutableCommand;
import console.scripting.DefaultScripts;
import autoEval.gui.GUI;


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
		if(text.contains("OUT"))
			return new OUT(text);
		else if(text.contains("REM"))
			return new REM();
		else if(text.contains("EXE"))
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