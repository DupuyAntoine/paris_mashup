package models;

import com.opencsv.bean.CsvBindByName;

public class Arrondissement extends Model {

    @CsvBindByName(column = "Nom officiel de l’arrondissement")
	private String label;
    
    @CsvBindByName(column = "Nom de l’arrondissement")
	private String name;
    
    @CsvBindByName(column = "Code postal arrondissement")
	private String postalCode;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String toString() {
		return this.label;
	}

}
