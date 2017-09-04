package cf.rachlinski.autoEval.util;

import cf.rachlinski.autoEval.responses.answers.Page;

import java.util.ArrayList;

@FunctionalInterface
public interface OnComplete {
	void onFinishParse(ArrayList<Page> toWrite);
}
