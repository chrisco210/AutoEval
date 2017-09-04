package cf.rachlinski.autoEval.storage.export.project;

public class Headers {
	public static final Header VERSION_HEADER = new Header("VER1".toCharArray());
	public static final Header FILE_PATH = new Header("FILE".toCharArray());
	public static final Header BOUND = new Header("RECT".toCharArray());
	public static final Header END = new Header(new byte[]{0,0,0,0});
}
