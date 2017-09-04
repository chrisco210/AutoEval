package cf.rachlinski.autoEval.gui;

import javax.swing.JLabel;

public class StatusBar extends JLabel{
	private static final long serialVersionUID = -7113134025735225689L;

	public StatusBar()
	{
		
	}
	
	/**
	 * Update the text on the status bar.  The function accepts an object as the argument, and executes toString on the object.
	 * @param status The text to display on the status bar
	 */
	public void setStatus(Object status)
	{
		setText(status.toString());
	}
	
	/**
	 * Update the text on the status bar and the tooltip
	 * @param status The text to change to 
	 * @param tooltip The tooltip to set
	 */
	public void setStatus(Object status, Object tooltip)
	{
		setText(status.toString());
		setToolTipText(tooltip.toString());
	}
}
