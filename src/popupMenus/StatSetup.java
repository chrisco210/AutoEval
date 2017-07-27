package popupMenus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

public class StatSetup extends JDialog {

	private static final long serialVersionUID = 865801158619359606L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatSetup dialog = new StatSetup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatSetup() {
		setType(Type.POPUP);
		setAlwaysOnTop(true);
		setTitle("Statistics Setup");
		setModal(true);
		setBounds(100, 100, 531, 202);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//TODO implement StatSetup
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
			
			JLabel lblSelectDataset = new JLabel("Select Dataset");
			lblSelectDataset.setHorizontalAlignment(SwingConstants.LEFT);
			lblSelectDataset.setToolTipText("Select the dataset to analyze");
			panel.add(lblSelectDataset, "cell 0 0,alignx trailing");
			
			JComboBox comboBox = new JComboBox();
			panel.add(comboBox, "cell 1 0,growx");
			
			JLabel lblDisplayStyle = new JLabel("Display Style");
			lblDisplayStyle.setHorizontalAlignment(SwingConstants.LEFT);
			lblDisplayStyle.setToolTipText("How the data should be displayed.");
			panel.add(lblDisplayStyle, "cell 0 1,alignx trailing");
			
			JComboBox displayStyleComboBox = new JComboBox();
			displayStyleComboBox.setModel(new DefaultComboBoxModel(new String[] {"Dot Plot", "Histogram", "Bar Graph"}));
			panel.add(displayStyleComboBox, "cell 1 1,growx");
			
			JLabel lblDataToDisplay = new JLabel("Data to Display");
			lblDataToDisplay.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(lblDataToDisplay, "cell 0 2,alignx trailing");
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Responses", "Correct Responses (Requires response model)", "Incorrect Responses (Requires response model)", "Percent Correct (Requires response model)"}));
			panel.add(comboBox_1, "cell 1 2,growx");
			
			JCheckBox chckbxUseResponseModel = new JCheckBox("Use Response Model");
			chckbxUseResponseModel.setToolTipText("Enabling this option will allow you to compare the given responses to those provided by an answer key.");
			panel.add(chckbxUseResponseModel, "cell 0 3");
			
			JButton btnCreateResponseModel = new JButton("Create Response Model");
			btnCreateResponseModel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//TODO Response Model Maker
				}
			});
			panel.add(btnCreateResponseModel, "flowx,cell 1 3");
			
			JButton btnUseExternalResponse = new JButton("Use External Response Model");
			btnUseExternalResponse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//TODO Response model importer
				}
			});
			panel.add(btnUseExternalResponse, "cell 1 3");
		}
	}
}
