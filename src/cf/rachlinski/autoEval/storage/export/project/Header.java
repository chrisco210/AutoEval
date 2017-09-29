package cf.rachlinski.autoEval.storage.export.project;

/**
 * A header to include in an AutoEvalProject
 */
public class Header
{
	/**
	 * The bytes in the header
	 */
	private byte[] header;

	/**
	 * Size of {@code int} in java
	 */
	private static final int SIZEOF_INT = 4;

	/**
	 * Create a new header.  Note that the bytes in the header cannot be changed
	 * @param identifier the characters to include in teh header.  This must be less than 4
	 * @param length the length of the header
	 */
	Header(char identifier, int length)
	{

	}

	/**
	 * Get the header content
	 * @return an array of 8 bytes
	 */
	public byte[] get()
	{
		return header;
	}

	/**
	 * Convert an integer into an array of 4 bytes
	 * Note that this function will ignore the sign bit
	 * The array of bytes is encoded in Little Endian IE The least significant byte is first
	 * @param n the integer to convert
	 * @return an array of 4 bytes containing the value of the integer
	 */
	public static short[] intToBytes(int n)
	{
		short[] bytes = new short[4];

		for(int i = 3; i >= 0; i--)
		{
			bytes[i] = (short) (n / (int) Math.pow(256, i));
		}

		return bytes;
	}

	/**
	 * Convert an array of bytes to a corresponding {@code int}
	 * This function expects the bytes to be arranged in Little Endian IE The least significant byte should be in {@code n[0]}
	 * Currently, this function does not check if you put a value too large in, so do not do that
	 * @param n the {@code byte} array to convert
	 * @return an {@code int} of the same value as 4 bytes
	 */
	public static int bytesToInt(short[] n)
	{
		if(n.length > Header.SIZEOF_INT)
			throw new IllegalArgumentException("Cannot store more than 4 bytes in integer");
		int total = 0;

		for(int i = 0; i < 4; i++)
		{
			total += ((int) n[i]) * Math.pow(256, i);
		}

		return total;
	}

}
