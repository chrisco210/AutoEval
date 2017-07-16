package autoEval.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsolePane extends JPanel{

	private static final long serialVersionUID = 5381690376796191552L;
	
	private JTextArea consoleTextBox;
	private JTextField consoleInput;
	
	public ConsolePane()
	{
		consoleTextBox = new JTextArea();
		consoleTextBox.setFont(new Font("Consolas", Font.PLAIN, 14));
		consoleTextBox.setEditable(false);
		
		setLayout(new BorderLayout());
		
		consoleInput = new JTextField();
		//TODO Make the input do something
		
		add(consoleInput, BorderLayout.SOUTH);
		add(consoleTextBox, BorderLayout.CENTER);
	}
	
	/**
	 * Log a message to the console
	 * @param s The text to log
	 */
	public void log(String s)
	{
		System.out.println(s);
		consoleTextBox.append("\n" + s);
	}
}
