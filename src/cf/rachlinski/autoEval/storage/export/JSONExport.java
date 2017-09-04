package cf.rachlinski.autoEval.storage.export;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import cf.rachlinski.autoEval.responses.answers.Page;

public class JSONExport extends Export {

	public JSONExport(ArrayList<Page> p) {
		super(p);
	}

	@Override
	public void saveExport(String path) throws FileNotFoundException,
			UnsupportedEncodingException {
		JsonObject json = new JsonObject();
		
		json.put("Name", "Test");
		json.put("Author", "test2");
		
		JsonArray company = new JsonArray();
		
		company.add("Company 1");
		company.add("Company 2");
		company.add("Company 3");
		
		json.put("Company List", company);
		
		try(FileWriter file = new FileWriter("C:\\users\\Christopher\\desktop\\test.json"))
		{
			file.write(json.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
