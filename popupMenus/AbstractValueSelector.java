package popupMenus;

import java.awt.Container;

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
	
	abstract void createInputField();
	abstract Object getValue();
	abstract void setValue();
}
