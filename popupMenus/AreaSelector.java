package popupMenus;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public abstract class AreaSelector {
	public Container pane;
	private JFrame frame = new JFrame();
	
	//Display the form
	public void displayForm()
	{
		frame.setSize(400, 400);
		pane = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//Reloads the visibility of the form
	public void reloadVis()
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
	}
	//Allows the class this is implemented to display whatever they want
	abstract void displaySelector();
}
