package cf.rachlinski.autoEval.popupMenus;

import java.awt.Container;

import javax.swing.JFrame;

public abstract class AbstractAreaSelector extends Popup{
	public Container pane;
	protected JFrame frame = new JFrame();
	
	/**
	 * Display the base form
	 */
	public void displayForm()
	{
		frame.setSize(400, 400);
		pane = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	/**
	 * Reload the visibility of the form.  Useful for adding new components
	 */
	public void reloadVis()
	{
		frame.setVisible(false);
		frame.setVisible(true);
	}
	
	/**
	 * Remove the form from memory
	 */
	public void destroy()
	{
		frame.dispose();
	}
	
	/**
	 * Hide the form
	 */
	public void hide()
	{
		frame.setVisible(false);
	}
	
	//Allows the class this is implemented to display whatever they want
	abstract void displaySelector();
}
