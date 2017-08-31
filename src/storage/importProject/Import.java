package storage.importProject;

import org.apache.commons.io.IOUtils;
import util.QuestionBoundList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Import {
	private ArrayList<File> files;
	private QuestionBoundList boundList;
	private byte questionCount;

	private byte[] fileBytes;

	public Import(File toImport) throws IOException
	{
		fileBytes = IOUtils.toByteArray(toImport.toURI());
	}

	public abstract void loadData();
}
