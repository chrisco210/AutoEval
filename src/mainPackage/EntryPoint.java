package mainPackage;

public final class EntryPoint {

	private static boolean gui = true; 
	
	public static void main(String[] args) 
	{
		for(String s : args)		//TODO Argument handler, try to improve, also make a command line based version of program
			switch(s)
			{
			case "nogui":
				gui = false;
				break;
			}
		if(gui)
			new Thread(new GUIEntryPoint()).start();
	}
}
