package opencsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import models.Film;
import models.Genre;
import omdb.OMDBProxy;

public class OpenFilmCsv {
	
	private String filepath;
	
	public OpenFilmCsv(String filepath) {
		this.filepath = filepath;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public List<Film> readCsvFilm() throws IllegalStateException, FileNotFoundException {
		
		OMDBProxy omdbProxy = new OMDBProxy();
		
		List<Film> films = new CsvToBeanBuilder<Film>(new FileReader(this.filepath)).withSeparator(';').withType(Film.class).build().parse();
		for (Film f : films) {
			HashMap<String, String> film_info = omdbProxy.getMoviePreciseInfos(f.getLabel().toLowerCase());
			if (!film_info.isEmpty()) {
				for (String s : film_info.get("Genre").split(", ")) {
					System.out.println(s);
					f.addGenre(new Genre(s.toLowerCase()));
				}
			}

		}
		return films;
		
	}

	public static void main(String[] args) throws IOException {
		
		String filename = "src\\main\\resources\\csv\\films_paris_tries.csv";
		
		List<Film> beans = new CsvToBeanBuilder<Film>(new FileReader(filename)).withSeparator(';').withType(Film.class).build().parse();

        beans.forEach(System.out::println);

	}
	
}
