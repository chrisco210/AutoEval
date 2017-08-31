package util;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutputStream extends OutputStream
{
	private JTextArea writeTo;

	public ConsoleOutputStream(JTextArea writeTo)
	{
		this.writeTo = writeTo;
	}

	@Override
	public void write(int b) throws IOException
	{
		writeTo.append( String.valueOf((char) b));

		writeTo.setCaretPosition(writeTo.getDocument().getLength());
	}
}
