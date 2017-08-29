package popupMenus;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class About extends JFrame {

	private static final long serialVersionUID = -5748297985953003087L;

	private final String aboutText = "AutoEval is areaSelector program for automatically parsing surveys and data that "
			+ "may not have been designed to be automatically graded."
			+ "\n\n"
			+ "Copyright 2017 Chris Rachlinski \n\n"
			+ "Permission is hereby granted, free of charge, to any person obtaining areaSelector copy "
			+ " of this software and associated documentation files (the Software), to deal "
			+ "in the Software without restriction, including without limitation the rights "
			+ "to use, copy, modify, merge, publish, distribute, sublicense, and or sell "
			+ "copies of the Software, and to permit persons to whom the Software is "
			+ "furnished to do so, subject to the following conditions: \n"
			+ "The above copyright notice and this permission notice shall be included in all "
			+ "copies or substantial portions of the Software.\n\n"
			+ "THE SOFTWARE IS PROVIDED AS IS, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR "
			+ "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, "
			+ "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE "
			+ "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER "
			+ "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, "
			+ "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.";

	public About()
	{
		setTitle("About AutoEval");
		setSize(500, 500);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JTextArea text = new JTextArea(aboutText);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		panel.add(text, BorderLayout.CENTER);
		add(panel);
		
		setVisible(true);
	}
}
