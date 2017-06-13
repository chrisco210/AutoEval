package popupMenus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class TypeSelector implements ActionListener {
	
	public TypeSelector()
	{
		JFrame frame = new JFrame("Select a Question Type...");
		
		JPanel panel = new JPanel();
		JPanel radioPanel = new JPanel();
		
		GridLayout gLay = new GridLayout(1,2);
		panel.setLayout(gLay);
		JRadioButton mc = new JRadioButton("Multiple Choice");
		mc.addActionListener(this);
		JRadioButton text = new JRadioButton("Text");
		text.addActionListener(this);
		JButton accept = new JButton("OK");
		accept.addActionListener(this);
		radioPanel.add(mc);
		radioPanel.add(text);
		panel.add(radioPanel);
		panel.add(accept);
		frame.add(panel);
		
		frame.setSize(300, 150);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
	}
	
}
