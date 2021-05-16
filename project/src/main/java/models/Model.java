package models;

import org.apache.commons.lang3.StringUtils;

public class Model {

	public String format(String str) {
		return StringUtils.stripAccents(str.replace(" ", "_").replace("'", "_").replace("?", "_").replace(",", "_").replace(".", "_")
				.replace("(", "_").replace(")", "_").replace("é", "_").replace("è", "_").replace("!", "_")
				.replace("/", "_").replace("%", "_").replace("°", "_").replace("-", "").replace("'", "_").replace("\"", "")).toLowerCase();
	}

}
