package cf.rachlinski.autoEval.gui;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

public class LayoutMap
{
	private Document doc;

	public LayoutMap(InputStream resourceXML) throws ParserConfigurationException, IOException, SAXException
	{
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resourceXML);
	}

	/**
	 * Get a component location from the supplied layout.xml
	 * @param componentName The name of the component to get the location of
	 * @return a string containing the location constant for BorderLayouts
	 */
	public String getComponentLocation(String componentName)
	{
		try
		{
			return (String) XPathFactory.newInstance()
					.newXPath()
					.compile("/layout/mapping[@component='" + componentName + "']/position")
					.evaluate(doc, XPathConstants.STRING);
		}
		catch (XPathExpressionException e)
		{
			return null;
		}
	}
}
