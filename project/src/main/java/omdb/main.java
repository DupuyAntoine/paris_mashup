package omdb;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class main {
	
	public static void main(String args[])
   {
     OMDBProxy omdbProxy = new OMDBProxy();
     System.out.println(omdbProxy.getMoviePreciseInfos("titanic").get("Title"));
     System.out.println(omdbProxy.getMoviePreciseInfos("titanic").get("Genre"));
     System.out.println(omdbProxy.getMoviePreciseInfos("titanic").get("Director"));
	 
	 //omdbProxy.getMovieInfos(/*name of the movie get by sparql*/).get("Genre")
   
   }

}
