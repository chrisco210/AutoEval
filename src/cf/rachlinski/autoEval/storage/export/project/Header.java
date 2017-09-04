package cf.rachlinski.autoEval.storage.export.project;

/**
 * A header to include in an AutoEvalProject
 */
class Header {
	/**
	 * The bytes in the header
	 */
	private byte[] header;

	/**
	 * Create a new header.  Note the bytes in the header cannot be changed
	 *
	 * @param headerBytes the bytes to include in the header. This must be less than 4 bytes
	 */
	Header(byte[] headerBytes) {
		//Check to make sure the header isn't more than 4 bytes
		if (headerBytes.length > 4)
			throw new IllegalArgumentException();

		byte[] tempBytes = new byte[8];

		for (int i = 0; i < 4; i++)
			tempBytes[i] = headerBytes[i];
		for (int i = 4; i < 8; i++)
			tempBytes[i] = 0;

		this.header = tempBytes;
	}

	/**
	 * Create a new header.  Note that the bytes in the header cannot be changed
	 * @param headerBytes the characters to include in teh header.  This must be less than 4
	 */
	Header(char[] headerBytes)
	{
		//Check to make sure the header isn't more than 4 bytes
		if (headerBytes.length > 4)
			throw new IllegalArgumentException();

		byte[] tempBytes = new byte[8];

		for (int i = 0; i < 4; i++)
			tempBytes[i] = (byte) headerBytes[i];
		for (int i = 4; i < 8; i++)
			tempBytes[i] = 0;

		this.header = tempBytes;
	}

	/**
	 * Get the header content
	 * @return an array of 8 bytes
	 */
	public byte[] get()
	{
		return header;
	}
}
