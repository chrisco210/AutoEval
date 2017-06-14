package popupMenus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;


public class TypeSelector implements ActionListener {
	private JRadioButton mc;
	private JRadioButton text;
	private JButton accept;
	
	/**
	 * Displays the form to choose what type of selection the user has made
	 */
	public TypeSelector()
	{
		
	}
	
	/**
	 * Creates and displays the form
	 */
	public void createWindow()
	{
		JFrame frame = new JFrame("Select a Question Type...");
		
		JPanel panel = new JPanel();
		JPanel radioPanel = new JPanel();
		
		GridLayout gLay = new GridLayout(1,2);
		panel.setLayout(gLay);
		ButtonGroup b = new ButtonGroup();
		mc = new JRadioButton("Multiple Choice");
		mc.addActionListener(this);
		text = new JRadioButton("Text");
		text.addActionListener(this);
		accept = new JButton("OK");
		accept.addActionListener(this);
		b.add(mc);
		b.add(text);
		radioPanel.add(mc);
		radioPanel.add(text);
		panel.add(radioPanel);
		panel.add(accept);
		frame.add(panel);
		
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}
	
	/**
	 * Check which radiobutton the user has selected
	 * @return The selection as an AreaType enum
	 */
	private AreaType getAreaTypeSelection()
	{
		if(mc.isSelected())
			return(AreaType.MultipleChoice);
		else if(text.isSelected())
			return(AreaType.TextResponse);
		return(AreaType.NONE_SELECTED);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == accept)
		{
			
		}
	}
	
}
