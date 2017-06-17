package export;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class ExportGUI {
	JFrame frame = new JFrame();
	
	private JFileChooser fileChooser = new JFileChooser();
	
	public ExportGUI()
	{
		//Create extension filters
		FileNameExtensionFilter csvExport = new FileNameExtensionFilter("Comma Seperated Values (*.csv)", "csv"),
				xmlExport = new FileNameExtensionFilter("eXtensible Markup Language (*.xml)", "xml"),
				textExport = new FileNameExtensionFilter("Text File (*.txt)", "txt"),
				projectExport = new FileNameExtensionFilter("AutoEval Project (*.aep)", "aep");
		fileChooser.addChoosableFileFilter(csvExport);
		fileChooser.addChoosableFileFilter(xmlExport);
		fileChooser.addChoosableFileFilter(textExport);
		fileChooser.addChoosableFileFilter(projectExport);
		
		int out = fileChooser.showSaveDialog(frame);
			if(out == JFileChooser.APPROVE_OPTION);
	}
}
