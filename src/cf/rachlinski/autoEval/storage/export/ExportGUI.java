package cf.rachlinski.autoEval.storage.export;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import cf.rachlinski.autoEval.gui.GUI;

public final class ExportGUI {
	JFrame frame = new JFrame();
	
	private JFileChooser fileChooser = new JFileChooser();

	private GUI callback;

	public ExportGUI(GUI callback)
	{
		this.callback = callback;

		//Create extension filters
		FileNameExtensionFilter csvExport = new FileNameExtensionFilter("Comma Separated Values (*.csv)", "csv"),
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
				Export csvExportClass = new CSVExport(callback.getPages());
				try {
					csvExportClass.saveExport(toSave.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "json":
				Export jsonExportClass = new JSONExport(callback.getPages());
				try {
					jsonExportClass.saveExport(toSave.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "aep":
				Export projectExportClass = new ProjectExport(callback.getPages(), callback.source, callback.getQuestionBounds(), callback.getNumberChooser());
				try {
					projectExportClass.saveExport(toSave.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "txt":
				Export textExportClass = new TextExport(callback.getPages());
				try {
					textExportClass.saveExport(toSave.getAbsolutePath());
				} catch(IOException e) {
					//TODO handle exceptions in storage.export, or at least make an error message
					e.printStackTrace();
				}
			}
		}
	}
}
