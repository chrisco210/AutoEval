package cf.rachlinski.autoEval.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import cf.rachlinski.autoEval.console.Controller;
import cf.rachlinski.autoEval.console.command.Variable;
import cf.rachlinski.autoEval.util.ConsoleOutputStream;

public class ConsolePane extends JPanel
{

	private static final long serialVersionUID = 5381690376796191552L;

	private JTextArea consoleTextBox;
	private JTextField consoleInput;
	private JScrollPane textBoxContainer;

	public static Controller controller;

	public ConsolePane()
	{
		//Setup text box and area
		consoleTextBox = new JTextArea();
		consoleTextBox.setFont(new Font("Consolas", Font.PLAIN, 14));
		consoleTextBox.setEditable(false);

		//Set stderr and stdout
		System.setErr(new PrintStream(new ConsoleOutputStream(this.consoleTextBox)));
		System.setOut(new PrintStream(new ConsoleOutputStream(this.consoleTextBox)));

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

		controller = new Controller(new Variable<?>[] {new Variable<Integer>(1, "stackSize")}, new String[] {"stackSize"}, 1);

	}

	/**
	 * Log a message to the console
	 *
	 * @param s The text to log
	 * @deprecated use System.out.println instead
	 */
	public static void log(Object s)
	{
		System.out.println(s.toString());
		System.out.println("[Warning] ConsolePane.log(Object s) is deprecated.  Use System.err.println() instead.");
	}

	/**
	 * Log a message to err
	 *
	 * @author Christopher
	 * @deprecated use System.err.println instead
	 */
	public static void err(Object s)
	{
		System.err.println(s.toString());
		System.out.println("[Warning] ConsolePane.err(Object s) is deprecated.  Use System.err.println() instead.");
	}

	/**
	 * Log a message to debug
	 *
	 * @author Christopher
	 */
	public static void dbg(Object s)
	{
		if(!GUI.debug)
			return;
		System.out.println("[Debug]" + s.toString());
	}

	private class ConsoleInputHandler implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0)
		{
			((Runnable) () ->
			{
				if(arg0.getKeyCode() == 10)
				{
					JTextComponent eventSrc = (JTextComponent) arg0.getComponent();        //Get the JTextComponent that sent the event
					String text = eventSrc.getText();        //Get the text that was in the text box and store it in string for later use

					eventSrc.setText(null);        //Clear the text box

					controller.exec(text);
				}
			}).run();
		}

		@Override
		public void keyReleased(KeyEvent arg0)
		{
		}

		@Override
		public void keyTyped(KeyEvent arg0)
		{
		}
	}
}
