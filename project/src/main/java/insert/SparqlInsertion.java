package insert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

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
		this.client = new SparqlClient("localhost:3030/FilmParis/update?update");
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
			triplets.add(new Triplet(":" + film.format(film.getLabel()), "rdfs:label", film.getLabel()));
			triplets.add(new Triplet(":" + film.format(film.getLabel()), ":a_pour_code_postal", film.getPostalCode()));
			for (Genre genre : film.getGenres()) {
				triplets.add(new Triplet(":" + film.format(film.getLabel()), ":est_classe_comme", genre.getLabel()));
			}
			for (Arrondissement arrondissement : arrondissements) {
				if (film.getPostalCode().equals(arrondissement.getPostalCode())) {
					triplets.add(new Triplet(film.format(":" + film.getLabel()), ":a_ete_tourne_a", ":" + arrondissement.format(arrondissement.getLabel())));
				}
			}
			
		}
		for (Arrondissement arrondissement : arrondissements) {
			triplets.add(new Triplet(arrondissement.format(":" + arrondissement.getLabel()), "rdfs:label", arrondissement.getLabel()));
			triplets.add(new Triplet(arrondissement.format(":" + arrondissement.getLabel()), ":a_pour_nom", arrondissement.getName()));
			triplets.add(new Triplet(arrondissement.format(":" + arrondissement.getLabel()), ":a_pour_code_postal", arrondissement.getPostalCode()));
			triplets.add(new Triplet(arrondissement.format(":" + arrondissement.getLabel()), ":se_situe_a", ":paris"));
			for (Activity activity : activities) {
				if (activity.getPostalCode().equals(arrondissement.getPostalCode())) {
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), "rdfs:label", activity.getLabel() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_code_postal", activity.getPostalCode() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_lieu_a_partir_du", activity.getStartDate() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_un_accès_PMR", activity.getAccessSrm() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_catégorie", activity.getCategory() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_un_accès_mal_voyant", activity.getAccessMv() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_un_accès_mal_entendant", activity.getAccessMe() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_type_de_prix", activity.getPriceType() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_type_accès", activity.getAccessType() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_url", activity.getUrl() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_url_image", activity.getImageUrl() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_url_image", activity.getImageUrl() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_pour_url_image", activity.getImageUrl() + "."));
					triplets.add(new Triplet(activity.format(":" + activity.getLabel()), ":a_lieu_a", ":" + arrondissement.getLabel() + "."));
				}
			}
		}
		return triplets;
	}
	
	public static void main(String[] args) throws IOException {
		
		SparqlInsertion insert = new SparqlInsertion();
		List<Triplet> triplets = insert.buildTriplets();
		for (Triplet t : triplets) {
			System.out.println(t);
		}
				
		System.out.println(triplets.size());

	}

}
