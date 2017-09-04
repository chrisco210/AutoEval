package cf.rachlinski.autoEval.popupMenus;

import java.awt.Container;

import javax.swing.JFrame;

public abstract class Popup {
	protected Container pane;
	protected JFrame frame = new JFrame();
	
	/**
	 * Dislays the form
	 */
	abstract void displayForm();
	
	/*public void reloadVis()
	{
		frame.setVisible(false);
		frame.setVisible(true);
	}
	
	public void destroy()
	{
		frame.dispose();
	}
	
	public void hide()
	{
		frame.setVisible(false);
	}*/
}
