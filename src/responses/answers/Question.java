package responses.answers;

public final class Question<T> {
	private T response;
	
	/**
	 * Question constructor
	 * @param value
	 */
	public Question(T value)
	{
		response = value;
	}
	
	/**
	 * Get the resposne to the question
	 * @return The response to the question
	 */
	public T getResponse()
	{
		return(response);
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [response=" + response + "]";
	}
}
