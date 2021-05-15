package sparqlclient;

public class SparqlFilmParis {

	public static void main(String[] args) {
        SparqlClient sparqlClient = new SparqlClient("localhost:3030");

        String query = "ASK WHERE { ?s ?p ?o }";
        boolean serverIsUp = sparqlClient.ask(query);
        
        if (serverIsUp) {
            System.out.println("service is UP");
        } else {
            System.out.println("service is DOWN");
        }
	}

}
