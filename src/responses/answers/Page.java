package responses.answers;

import java.util.ArrayList;

public class Page {
	@SuppressWarnings("rawtypes")
	ArrayList<Question> questions = new ArrayList<Question>(10);
	
	/**
	 * Get the full list of question classes
	 * @return An arraylist of question classes
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<Question> getQuestionList()
	{
		return(questions);
	}
	
	/**
	 * Add a question to the page
	 * @param q
	 */
	public void addQuestion(Question<?> q)
	{
		questions.add(q);
	}
	
	/**
	 * Gets one question class
	 * @param q the number of the quesion class
	 * @return A question class
	 */
	public Question<?> getQuestion(int q)
	{
		return questions.get(q);
	}
}
