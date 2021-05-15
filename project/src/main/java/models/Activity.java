package models;

import com.opencsv.bean.CsvBindByName;

public class Activity extends Model {

    @CsvBindByName(column = "Titre")
	private String label;
	
    @CsvBindByName(column = "Code postal")
	private String postalCode;

    @CsvBindByName(column = "Prix")
	private String price;
    
    @CsvBindByName(column = "Accès PMR")
	private String access_srm;
    
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
