package autoEval.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import console.Controller;
import console.command.Variable;
import console.scripting.EnvironmentConstants;

public class ConsolePane extends JPanel{

	private static final long serialVersionUID = 5381690376796191552L;
	
	private JTextArea consoleTextBox;
	private JTextField consoleInput;
	private JScrollPane textBoxContainer;
	
	public Controller controller;
	
	public ConsolePane()
	{
		consoleTextBox = new JTextArea();
		consoleTextBox.setFont(new Font("Consolas", Font.PLAIN, 14));
		consoleTextBox.setEditable(false);
		
		textBoxContainer = new JScrollPane(consoleTextBox);
		
		setLayout(new BorderLayout());

		consoleInput = new JTextField();
		
		//if(GUI.debug)
		//	consoleInput.addKeyListener(new DebugConsoleInputHandler());
		//else
			consoleInput.addKeyListener(new ConsoleInputHandler());
		
		consoleInput.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		add(consoleInput, BorderLayout.SOUTH);
		add(textBoxContainer, BorderLayout.CENTER);
		
		//Setup teh controller
		Variable<?>[] envVar = new Variable<?>[2];
		envVar[0] = new Variable<Integer>(1);
		envVar[1] = new Variable<String>(EnvironmentConstants.PROGRAM_ROOT);
		
		controller = new Controller(1, envVar, 1);
	}
	
	/**
	 * Log a message to the console
	 * @param s The text to log
	 */
	public void log(Object s)
	{
		System.out.println(s.toString());
		consoleTextBox.append("\n" + s.toString());
	}
	
	/**
	 * Log a message to err
	 * @author Christopher
	 *
	 */
	public void err(Object s)
	{
		System.err.println(s.toString());
		consoleTextBox.append("\n[Error] " + s.toString());
		
	}
	
	/**
	 * Log a message to debug
	 * @author Christopher
	 *
	 */
	public void dbg(Object s)
	{
		if(!GUI.debug)
			return;
		System.out.println(s.toString());
		consoleTextBox.append("\n[Debug] " + s.toString());
	}
	
	private class ConsoleInputHandler implements KeyListener
	{
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			new Runnable(){
				public void run()
				{
					if(arg0.getKeyCode() == 10)
					{
						JTextComponent eventSrc = (JTextComponent) arg0.getComponent();		//Get the JTextComponent that sent the event
						String text = eventSrc.getText();		//Get the text that was in the text box and store it in string for later use
						
						eventSrc.setText(null);		//Clear the text box
						
						controller.exec(text);
					}
				}
			}.run();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {	}

		@Override
		public void keyTyped(KeyEvent arg0) {	}

	}
	
	private class DebugConsoleInputHandler implements KeyListener
	{
		@Override
		public void keyPressed(KeyEvent arg0) {
			//GUI.console.log("keyPress event recieved with keycode " + arg0.getKeyCode());		//Debug
			
			if(arg0.getKeyCode() == 10)
			{
				JTextComponent eventSrc = (JTextComponent) arg0.getComponent();		//Get the JTextComponent that sent the event
				String text = eventSrc.getText();		//Get the text that was in the text box and store it in string for later use
				GUI.console.log("Text Entered: " + text);		//debug
				
				eventSrc.setText(null);		//Clear the text box
				
				controller.exec(text);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {	}

		@Override
		public void keyTyped(KeyEvent arg0) {	}

	}
}
