package install;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public final class Installer 
{
	public static void downloadComponents(String installPath, boolean doSource, boolean doJavadoc, boolean genSDB) throws Exception
	{
		File tempFolder = File.createTempFile("AutoEval", "");
		tempFolder.delete();
		boolean success = tempFolder.mkdir();
		if(!success)
			throw new IOException();
	}
	
	/**
	 * Download a file
	 * @param url the url to get the file from
	 * @param dest the destination to put the file in
	 */
	private void download(String url, File dest) throws Exception
	{
		FileUtils.copyURLToFile(new URL(url), dest, 10000, 10000);
	}
}
