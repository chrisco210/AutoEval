package cf.rachlinski.autoEval.storage.export.project;

import cf.rachlinski.autoEval.gui.ConsolePane;
import cf.rachlinski.autoEval.util.QuestionBoundList;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

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
		//Get the total number of bytes that will be written to the file, initialized to 1 because byte 0 is optionCount
		byteCount = 10;
		//Get size of the file names
		for(File f : fileSource)
			byteCount += f.getPath().getBytes(Charset.forName("UTF-8")).length;

		//Get the size of the question bound lists
		//How we get the size:
		//(NUM_POINTS * sizeof(int) * 2 for x and y values) * 2 for the two points + 1 byte * NUM_AREATYPES
		byteCount += (bounds.size() * 8 * 2) + bounds.size() * 4;

		//Factor in the headers
		byteCount += (fileSource.size() * 16) + (bounds.size() * 16);

		fileBytes = new byte[byteCount];

		ConsolePane.dbg("Total number of bytes: " + fileBytes.length);


		//Start writing
		writeArray(Headers.VERSION_HEADER.get());        //write the version header
		writeByte(optionCount);     //set the option count, this only takes one byte, so it doesn't need areaSelector header

		//Write the file paths
		for(File f : fileSource)
		{
			writeArray(Headers.FILE_PATH.get());
			writeArray(f.getPath().getBytes(Charset.forName("UTF-8")));
			writeArray(Headers.END.get());
		}

		//Write all points
		for(int i = 0; i < bounds.size(); i++)
		{
			writeArray(Headers.BOUND.get());
			//Write data for point 1
			writeInt(bounds.getPointFromList(1, i).x);
			writeInt(bounds.getPointFromList(1, i).y);
			//Write data for point 2
			writeInt(bounds.getPointFromList(2, i).x);
			writeInt(bounds.getPointFromList(2, i).y);
			//Write the area type
			writeInt(bounds.getType(i).ordinal());
			writeArray(Headers.END.get());
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
