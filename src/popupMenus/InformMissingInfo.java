package popupMenus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Window.Type;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

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
			txtrErrorYouHave.setText("Error.  You have not supplied enough information to parse responses.  Ensure you have selected areaSelector question area, and chosen areaSelector number of responses.");
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
