package install;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class InstallerGUI {

	private JFrame frmAutoevalInstaller;
	private JTextField txtCprogramFilesautoeval;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstallerGUI window = new InstallerGUI();
					window.frmAutoevalInstaller.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InstallerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutoevalInstaller = new JFrame();
		frmAutoevalInstaller.setResizable(false);
		frmAutoevalInstaller.setTitle("AutoEval Installer");
		frmAutoevalInstaller.setBounds(100, 100, 500, 208);
		frmAutoevalInstaller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutoevalInstaller.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		JLabel lblInstallationPath = new JLabel("Installation Path");
		frmAutoevalInstaller.getContentPane().add(lblInstallationPath, "cell 0 0,alignx trailing");
		
		txtCprogramFilesautoeval = new JTextField();
		txtCprogramFilesautoeval.setText("C:\\Program Files\\AutoEval");
		frmAutoevalInstaller.getContentPane().add(txtCprogramFilesautoeval, "cell 1 0,growx");
		txtCprogramFilesautoeval.setColumns(10);
		
		JLabel lblDownloadSource = new JLabel("Download Source");
		frmAutoevalInstaller.getContentPane().add(lblDownloadSource, "cell 0 1");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		frmAutoevalInstaller.getContentPane().add(chckbxNewCheckBox, "cell 1 1");
		
		JLabel lblDownloadJavadoc = new JLabel("Download Javadoc");
		frmAutoevalInstaller.getContentPane().add(lblDownloadJavadoc, "cell 0 2");
		
		JCheckBox checkBox = new JCheckBox("");
		frmAutoevalInstaller.getContentPane().add(checkBox, "cell 1 2");
		
		JLabel lblGenerateSdb = new JLabel("Generate SDB");
		lblGenerateSdb.setToolTipText("Generate the AutoEvalScript libraries");
		frmAutoevalInstaller.getContentPane().add(lblGenerateSdb, "cell 0 3");
		
		JCheckBox checkBox_1 = new JCheckBox("");
		frmAutoevalInstaller.getContentPane().add(checkBox_1, "cell 1 3");
		
		JButton btnInstall = new JButton("Install");
		frmAutoevalInstaller.getContentPane().add(btnInstall, "cell 0 5");
	}

}
