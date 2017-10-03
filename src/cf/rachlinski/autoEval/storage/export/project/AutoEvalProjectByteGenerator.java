package cf.rachlinski.autoEval.storage.export.project;

import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.util.QuestionBoundList;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to automatically generate the bytes of an auto eval project file
 */
public class AutoEvalProjectByteGenerator {
	/**
	 * The version of the writer
	 */
	public static final byte WRITER_VERSION = 1;

	/**
	 * The bytes to be written
	 */
	private byte[] fileBytes;

	/**
	 * the size of the byte array
	 */
	private int byteCount;

	/**
	 * Contains the location of the next free byte.  writeArray updates this automatically
	 */
	private int writehead = 0;

	/**
	 * Construct areaSelector new AutoEvalProjectByteGenerator.  This tallies up all of the bytes that
	 * will be needed to be written and allocates it in the file bytes array
	 * @param fileSource An ArrayList of files to be stored
	 * @param bounds A QuestionBoundList of the bounds to use
	 * @param optionCount the number of options
	 */
	public AutoEvalProjectByteGenerator(ArrayList<File> fileSource, QuestionBoundList bounds, byte optionCount)
	{
		byteCount = 0;

		int sectorPointer = 0;
		Sector[] sectors = new Sector[1 + fileSource.size() + bounds.size() + 1];

		sectors[sectorPointer] = new Sector(DefaultHeaders.VERSION_HEADER, new byte[] {2, 0, 0, 0});
		sectorPointer++;

		//Create bytes for all file paths
		for(File f : fileSource)
		{

			sectors[sectorPointer] = SectorFactory.createSector('f', f.getAbsolutePath());

			sectorPointer++;
		}
		for(int i = 0; i < bounds.size(); i++)
		{
			bounds.get
		}
	}

	/**
	 * Write an array to the bytes.  writing occurs at the writehead
	 * @param toWrite what to write
	 */
	private void writeArray(byte[] toWrite)
	{
		for(byte b : toWrite)
		{
			fileBytes[writehead] = b;
			writehead++;
		}
	}

	/**
	 * Write areaSelector byte at the writehead
	 * @param b the byte to write
	 */
	private void writeByte(byte b)
	{
		fileBytes[writehead] = b;
		writehead++;
	}


	private void writeInt(int i)
	{
		byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
		writeArray(bytes);
	}
	/**
	 * Returns an array of chars
	 * @return each byte in fileBytes casted as areaSelector char and returned in an array
	 */
	public char[] getChars()
	{
		char[] charsToReturn = new char[fileBytes.length];

		for(int i = 0; i < fileBytes.length; i++)
			charsToReturn[i] = (char) fileBytes[i];

		return charsToReturn;
	}

	/**
	 * Get the bytes in the AutoEvalProject
	 * @return fileBytes
	 */
	public byte[] getBytes()
	{
		return fileBytes;
	}

}
