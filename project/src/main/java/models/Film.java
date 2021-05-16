package models;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class Film extends Model {

    @CsvBindByName(column = "Titre")
	private String label;
	
    @CsvBindByName(column = "Code postal")
	private String postalCode;
    
    private List<Genre> genres = new ArrayList<Genre>();

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
	
	public String toString() {
		return label;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}

}
