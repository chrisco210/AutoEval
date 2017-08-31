package util;

import responses.answers.Page;

import java.util.ArrayList;

@FunctionalInterface
public interface OnComplete {
	void onFinishParse(ArrayList<Page> toWrite);
}
