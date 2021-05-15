package models;

import com.opencsv.bean.CsvBindByName;

public class Activity extends Model {

    @CsvBindByName(column = "Titre")
	private String label;
	
    @CsvBindByName(column = "Code postal")
	private String postalCode;

    @CsvBindByName(column = "Accès PMR")
	private String accessSrm;
    
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

	public String getAccessSrm() {
		return accessSrm;
	}

	public void setAccessSrm(String accessSrm) {
		this.accessSrm = accessSrm;
	}

	public String toString() {
		return accessSrm;
	}
	
}
