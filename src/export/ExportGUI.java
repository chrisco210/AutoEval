package export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import mainPackage.GUI;

public final class ExportGUI {
	JFrame frame = new JFrame();
	
	private JFileChooser fileChooser = new JFileChooser();
	
	public ExportGUI()
	{
		//Create extension filters
		FileNameExtensionFilter csvExport = new FileNameExtensionFilter("Comma Seperated Values (*.csv)", "csv"),
				xmlExport = new FileNameExtensionFilter("eXtensible Markup Language (*.xml)", "xml"),
				textExport = new FileNameExtensionFilter("Text File (*.txt)", "txt"),
				jsonExport = new FileNameExtensionFilter("JSON File (*.json)", "json"),
				projectExport = new FileNameExtensionFilter("AutoEval Project (*.aep)", "aep");
		fileChooser.addChoosableFileFilter(csvExport);
		fileChooser.addChoosableFileFilter(xmlExport);
		fileChooser.addChoosableFileFilter(jsonExport);
		fileChooser.addChoosableFileFilter(textExport);
		fileChooser.addChoosableFileFilter(projectExport);
		
		int out = fileChooser.showSaveDialog(frame);
		if(out == JFileChooser.APPROVE_OPTION);
		{
			File toSave = fileChooser.getSelectedFile();
			
			switch(toSave.toString().substring(toSave.toString().lastIndexOf(".") + 1))		//Check what file extension the user saved as
			{
			case "csv":
				CSVExport csvExportClass = new CSVExport(GUI.questionAns);
				try {
					csvExportClass.saveExport(toSave.getAbsolutePath());
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;
			case "json":
				JSONExport jsonExportClass = new JSONExport(GUI.questionAns);
				try {
					jsonExportClass.saveExport(toSave.getAbsolutePath());
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
