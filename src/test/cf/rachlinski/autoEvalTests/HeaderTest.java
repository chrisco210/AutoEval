package test.cf.rachlinski.autoEvalTests;

import cf.rachlinski.autoEval.storage.export.project.Header;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class HeaderTest
{
	@Test
	public static void headerTest2()
	{
		int toConvert = 124321352;

		System.out.println(toConvert);

		short nums[] = Header.intToBytes(toConvert);
		System.out.println(nums[0] + "," + nums[1] + "," + nums[2] + "," + nums[3]);

		System.out.println(Header.bytesToInt(nums));
	}

	@Test
	void headerTest()
	{
		assertEquals(255, Header.bytesToInt(Header.intToBytes(255)));
		assertEquals(new short[] {210, 4, 0, 0}, Header.intToBytes(1234));
	}
}
