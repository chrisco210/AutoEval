package popupMenus;

import javax.swing.JFrame;

public abstract class AbstractValueSelector extends Popup{
	enum ReturnTo{
		questionCount,
		self,
	}
	
	public void displayForm()
	{
		frame.setSize(50, 100);
		pane = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Allows for specialized rendering of the value input field
	 */
	abstract void createInputField();
	/**
	 * 
	 */
	abstract void setValue();
	/**
	 * Reload the visibility of the elements, useful for adding new components to the form.
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
	 * Hide the form from view
	 */
	public void hide()
	{
		frame.setVisible(false);
	}
}
