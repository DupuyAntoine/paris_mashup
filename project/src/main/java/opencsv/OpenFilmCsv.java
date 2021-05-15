package opencsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import models.Film;

public class OpenFilmCsv {
	
	public static void main(String[] args) throws IOException {
		
		String filename = "C:\\Users\\adupu\\Documents\\Master\\M2\\WebSemantique\\Projet\\project\\src\\main\\resources\\csv\\films_paris_tries.csv";
		
		List<Film> beans = new CsvToBeanBuilder<Film>(new FileReader(filename)).withType(Film.class).build().parse();

        beans.forEach(System.out::println);

	}
	
}
