package mainPackage;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public final class Export extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Container pane;
	
	public Export()
	{
		setTitle("Export...");
		setSize(750, 750);
		
		pane = getContentPane();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
