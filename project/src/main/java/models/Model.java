package models;

public class Model {

	public String format(String str) {
		return str.replace(" ", "_").replace("'", "_").replace("?", "_").replace(",", "_").replace(".", "_")
				.replace("(", "_").replace(")", "_").replace("é", "_").replace("è", "_").replace("!", "_")
				.replace("/", "_").replace("%", "_").replace("°", "_");
	}

}
