package responses.answers;

final class Question<T> {
	private T response;			//This class is intentionaly simple, responses are immutable
	
	Question(T value)
	{
		response = value;
	}
	
	T getResponse()
	{
		return(response);
	}
}
