package models;

public class Genre extends Model {

	private String label;

	public Genre(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString() {
		return label;
	}
}
