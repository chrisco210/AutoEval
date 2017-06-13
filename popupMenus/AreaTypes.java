package popupMenus;

/**
 * Enum for different types of question
 * @author Christopher
 * 
 */
enum AreaTypes {
	MultipleChoice,		//A automatic multiple choice response
	TextResponse,		//A response involving text in some way, which will be parsed by a human
	TrueFalse,		//A true or false question
	QuestionDetail		//This should be used for information under a question.  Treat in parsing as a TextResponse
}
