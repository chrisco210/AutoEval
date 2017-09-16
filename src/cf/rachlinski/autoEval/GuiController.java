package cf.rachlinski.autoEval;

import cf.rachlinski.autoEval.gui.GUI;

import java.awt.event.ActionListener;

public class GuiController
{
	private GUI window;

	private ActionListener listener;

	public GuiController()
	{

	}

	/**
	 * Set which GUI class to use as a window
	 * @param window the GUI class to use
	 */
	public void setWindow(GUI window)
	{
		this.window = window;
	}

	/**
	 * Get the GUI class from the controller
	 * @return the GUI specified
	 */
	public GUI getWindow()
	{
		return window;
	}

	/**
	 * Set the action listener for the GUI
	 * @param listener the listener to use
	 */
	public void setActionListener(ActionListener listener)
	{
		this.listener = listener;
	}

	/**
	 * Set the visibility of the window
	 * @param setVis whether the visibility is enabled or disabled
	 */
	public void setVisible(boolean setVis)
	{
		window.setVisible(setVis);
	}
}
