package sparqlclient;

import java.io.FileNotFoundException;

public class SparqlFilmParis {

	public static void main(String[] args) throws IllegalStateException, FileNotFoundException {
        SparqlClient sparqlClient = new SparqlClient("localhost:3030/FilmParis");

        String query = "ASK WHERE { ?s ?p ?o }";
        boolean serverIsUp = sparqlClient.ask(query);
        
        if (serverIsUp) {
            System.out.println("service is UP");
        } else {
            System.out.println("service is DOWN");
        }
	}

}
