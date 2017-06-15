package export;

abstract class Export {
	private int[][] responses;		//Int[][] to store responses from the [page][question]
	
	public Export(int[][] r)
	{
		responses = r;
	}
	
	
	
	public abstract void saveExport();
}
