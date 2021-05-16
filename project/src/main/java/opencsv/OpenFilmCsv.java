package opencsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import models.Film;

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
		
		return new CsvToBeanBuilder<Film>(new FileReader(this.filepath)).withType(Film.class).build().parse();
		
	}

	public static void main(String[] args) throws IOException {
		
		String filename = "src\\main\\resources\\csv\\films_paris_tries.csv";
		
		List<Film> beans = new CsvToBeanBuilder<Film>(new FileReader(filename)).withSeparator(';').withType(Film.class).build().parse();

        beans.forEach(System.out::println);

	}
	
}
