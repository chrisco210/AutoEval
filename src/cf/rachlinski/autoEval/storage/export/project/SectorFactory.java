package cf.rachlinski.autoEval.storage.export.project;

import java.io.UnsupportedEncodingException;

public final class SectorFactory
{
	/**
	 * TODO
	 * @param id
	 * @param bytes
	 * @return
	 */
	public static Sector createSector(char id, byte[] bytes)
	{
		return new Sector(new Header(id, bytes.length), bytes);
	}

	/**
	 * TODO
	 * @param id
	 * @param string
	 * @return
	 */
	public static Sector createSector(char id, String string)
	{
		try
		{
			return new Sector(new Header(id, string.getBytes("UTF-8").length), string.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e)
		{
			//TODO handle exception
			e.printStackTrace();
		}
	}
}
