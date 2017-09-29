package test.cf.rachlinski.autoEvalTests;

import cf.rachlinski.autoEval.storage.export.project.Header;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class HeaderTest
{
	@Test
	void headerTest()
	{
		assertEquals(255, Header.bytesToInt(Header.intToBytes(255)));
		assertEquals(new short[] {210, 4, 0, 0}, Header.intToBytes(1234));
	}
}
