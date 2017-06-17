package responses.answers;

public final class Question<T> {
	private T response;			//This class is intentionaly simple, responses are immutable
	
	public Question(T value)
	{
		response = value;
	}
	
	public T getResponse()
	{
		return(response);
	}
}
