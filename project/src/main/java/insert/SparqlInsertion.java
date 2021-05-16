package insert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Arrondissement;
import models.Film;
import models.Genre;
import opencsv.OpenActivityCsv;
import opencsv.OpenArrondissementCsv;
import opencsv.OpenFilmCsv;
import sparqlclient.SparqlClient;

public class SparqlInsertion {
	
	SparqlClient client;
	private List<Film> films;
	private List<Arrondissement> arrondissements;
	private List<Activity> activities;
	
	public SparqlInsertion() throws IllegalStateException, FileNotFoundException {
		this.client = new SparqlClient("localhost:3030/FilmParis");
		this.films = new OpenFilmCsv(
				".\\src\\main\\resources\\csv\\films_paris_tries.csv").readCsvFilm();
		this.arrondissements = new OpenArrondissementCsv(
				".\\src\\main\\resources\\csv\\arrondissements.csv").readCsvArrondissement();
		this.activities = new OpenActivityCsv(
				".\\src\\main\\resources\\csv\\que-faire-a-paris-.csv").readCsvActivity();
	}

	public List<Film> getFilms() {
		return films;
	}
	
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	public List<Arrondissement> getArrondissements() {
		return arrondissements;
	}
	
	public void setArrondissements(List<Arrondissement> arrondissements) {
		this.arrondissements = arrondissements;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public List<Triplet> buildTriplets() {
		List<Triplet> triplets = new ArrayList<Triplet>();
		for (Film film : films) {
			triplets.add(new Triplet(" :" + film.format(film.getLabel()), " a", ":film."));
			triplets.add(new Triplet(" :" + film.format(film.getLabel()), " rdfs:label", "\"" + film.getLabel() + "\"."));
			triplets.add(new Triplet(" :" + film.format(film.getLabel()), " :a_pour_code_postal", "\"" + film.getPostalCode() + "\"^^xsd:string."));
			for (Genre genre : film.getGenres()) {
				triplets.add(new Triplet(" :" + film.format(film.getLabel()), " :est_classe_comme", ":" + genre.format(genre.getLabel()) + "."));
			}
			for (Arrondissement arrondissement : arrondissements) {
				if (film.getPostalCode().equals(arrondissement.getPostalCode())) {
					triplets.add(new Triplet(film.format(":" + film.getLabel()), " :a_ete_tourne_a", ":" + arrondissement.format(arrondissement.getLabel()) + "."));
				}
			}
			
		}
		int i = 0;
		for (Arrondissement arrondissement : arrondissements) {
			triplets.add(new Triplet(" :" + arrondissement.format(arrondissement.getLabel()), " a", ":arrondissement."));
			triplets.add(new Triplet(" :" + arrondissement.format(arrondissement.getLabel()), " rdfs:label", "\"" + arrondissement.getLabel() + "\"."));
			triplets.add(new Triplet(" :" + arrondissement.format(arrondissement.getLabel()), " :a_pour_nom", "\"" + arrondissement.getName() + "\"^^xsd:string."));
			triplets.add(new Triplet(" :" + arrondissement.format(arrondissement.getLabel()), " :a_pour_code_postal", "\"" + arrondissement.getPostalCode() + "\"^^xsd:string."));
			int j = 0;
			for (Activity activity : activities) {
				if (activity.getPostalCode().equals(arrondissement.getPostalCode())) {
					triplets.add(new Triplet(":activite" + i + j, " a", ":activite."));
					triplets.add(new Triplet(":activite" + i + j, " rdfs:label", "\"" + activity.getLabel().replace("\"", "") + "\"."));
					triplets.add(new Triplet(":activite" + i + j, " :a_pour_code_postal", "\"" + activity.getPostalCode() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_lieu_a_partir_du", "\"" + activity.getStartDate() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_un_accès_PMR", "\"" + activity.getAccessSrm() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_pour_catégorie", "\"" + activity.getCategory() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_un_accès_mal_voyant", "\"" + activity.getAccessMv() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_un_accès_mal_entendant", "\"" + activity.getAccessMe() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_pour_type_de_prix", "\"" + activity.getPriceType() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_pour_type_accès", "\"" + activity.getAccessType() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_pour_url", "\"" + activity.getUrl() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_pour_url_image", "\"" + activity.getImageUrl() + "\"^^xsd:string."));
					triplets.add(new Triplet(":activite" + i + j, " :a_lieu_a", ":" + arrondissement.format(arrondissement.getLabel()) + "."));
					j++;
				}
			}
			i++;
		}
		return triplets;
	}
	
	public static void main(String[] args) throws IOException {
		
		SparqlInsertion insert = new SparqlInsertion();
		List<Triplet> triplets = insert.buildTriplets();
		String query = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>  INSERT DATA { ";
		for (Triplet t : triplets) {
			query += t.getSujet() + " " + t.getPredicat() + " " + t.getObjet() + " " ;

		}
		query += "}";
		System.out.println(query);
		insert.client.update(query);
		System.out.println(triplets.size());

	}

}
