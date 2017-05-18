package popupMenus;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public abstract class AbstractAreaSelector extends Popup{
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
	
	//Allows the class this is implemented to display whatever they want
	abstract void displaySelector();
}
