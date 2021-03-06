package models;

import com.opencsv.bean.CsvBindByName;

public class Activity extends Model {

    @CsvBindByName(column = "Titre")
	private String label;
	
    @CsvBindByName(column = "Code postal")
	private String postalCode;

    @CsvBindByName(column = "Date de début")
	private String startDate;

    @CsvBindByName(column = "Accès PMR")
	private String accessSrm;
    
    @CsvBindByName(column = "Catégorie")
	private String category;
    
    @CsvBindByName(column = "Accès mal voyant")
	private String accessMv;
    
    @CsvBindByName(column = "Accès mal entendant")
	private String accessMe;

    @CsvBindByName(column = "Type de prix")
	private String priceType;

    @CsvBindByName(column = "Type d'accès")
	private String accessType;
    
    @CsvBindByName(column = "URL")
	private String url;
    
    @CsvBindByName(column = "URL de l'image")
    private String imageUrl;

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
	
	public String getAccessMv() {
		return accessMv;
	}
	
	public void setAccessMv(String accessMv) {
		this.accessMv = accessMv;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String toString() {
		return label + " " + url + " --- " + priceType + " --- " + category;
	}

	public String getAccessMe() {
		return accessMe;
	}

	public void setAccessMe(String accessMe) {
		this.accessMe = accessMe;
	}

}
