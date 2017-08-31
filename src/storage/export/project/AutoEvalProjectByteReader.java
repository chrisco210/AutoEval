package storage.export.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoEvalProjectByteReader
{
	private byte[] toRead;
	private int version;

	public AutoEvalProjectByteReader(byte[] toRead)
	{
		this.toRead = toRead;
	}

	public byte getOptionCount()
	{
		return toRead[8];
	}

	public ArrayList<File> getFiles()
	{
		return new ArrayList<File>();       //TODO make this method work, this is just temp
	}

	/**
	 * Get the contents behind a header
	 * @param h the header to look for
	 * @return a hashmap
	 */
	private HashMap<Integer, byte[]> getHeaderContents(Header h)
	{
		HashMap<Integer, byte[]> contents = new HashMap<Integer, byte[]>();


		//Iterate through all bytes of the file
		for(int i = 9; i < toRead.length; i++)
		{
			//Compare the bytes in the location of a possible header with the specified header.
			//It looks dumb, but it works
			if(new byte[] {
					toRead[i],
					toRead[i + 1],
					toRead[i + 2],
					toRead[i + 3],
					toRead[i + 4],
					toRead[i + 5],
					toRead[i + 6],
					toRead[i + 7]
			} == h.get())
			{
				boolean finished = false;       //For the while loop
				int j = i + 8;      //J for iterator
				int byteCount = 0;      //Byte count

				//While loop to count the number of bytes in the
				while(!finished)
				{
					//Check if an end header has been reached
					if(new byte[] {
							toRead[j],
							toRead[j + 1],
							toRead[j + 2],
							toRead[j + 3],
							toRead[j + 4],
							toRead[j + 5],
							toRead[j + 6],
							toRead[j + 7]
					} == Headers.END.get())
					{
						finished = true;
					}
					else        //If it has not, increase the byte count
					{
						byteCount++;
						j++;
					}
				}
				i += 16 + byteCount;
			}

		}

		return contents;
	}
}
