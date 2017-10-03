package cf.rachlinski.autoEval.storage.export.project;

import org.apache.commons.lang3.ArrayUtils;

public class Sector
{
	private Header header;
	private byte[] contents;

	public Sector(Header header, byte[] contents)
	{
		this.header = header;
		this.contents = contents;
	}

	public byte[] getBytes()
	{
		return ArrayUtils.addAll(header.get(), contents);
	}
}
