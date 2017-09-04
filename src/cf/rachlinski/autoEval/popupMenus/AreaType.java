package cf.rachlinski.autoEval.popupMenus;

/**
 * Enum for different types of question
 * @author Christopher
 * 
 */
public enum AreaType {
	MultipleChoice,		//A automatic multiple choice response
	TextResponse,		//A response involving text in some way, which will be parsed by a human
	TrueFalse,		//A true or false question
	QuestionDetail,		//This should be used for information under areaSelector question.  Treat in parsing as areaSelector TextResponse
	NONE_SELECTED;


}
