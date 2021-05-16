package insert;

public class Triplet {
	
	private String sujet;
	private String predicat;
	private String objet;
	
	public Triplet(String s, String p, String o) {
		this.setSujet(s);
		this.setPredicat(p);
		this.setObjet(o);
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getPredicat() {
		return predicat;
	}

	public void setPredicat(String predicat) {
		this.predicat = predicat;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	public String toString() {
		return sujet + ":" + predicat + ":" + objet;
	}

}
