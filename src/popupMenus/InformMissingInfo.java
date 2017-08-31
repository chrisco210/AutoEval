package popupMenus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformMissingInfo extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformMissingInfo dialog = new InformMissingInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformMissingInfo() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setTitle("Error.");
		setBounds(100, 100, 291, 151);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JTextArea txtrErrorYouHave = new JTextArea();
			txtrErrorYouHave.setFont(UIManager.getFont("TextPane.font"));
			txtrErrorYouHave.setBackground(UIManager.getColor("InternalFrame.borderLight"));
			txtrErrorYouHave.setEditable(false);
			txtrErrorYouHave.setWrapStyleWord(true);
			txtrErrorYouHave.setLineWrap(true);
			txtrErrorYouHave.setText("Error. You have not supplied enough information to parse the responses.  Make sure that you have selected all questions in the image area selector, and " +
					"you have selected the number of options in the questions. (See Help->Github to view full instructions)");
			contentPanel.add(txtrErrorYouHave);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
		
	}
}
