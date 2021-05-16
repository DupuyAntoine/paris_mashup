package opencsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import models.Arrondissement;

public class OpenArrondissementCsv {

	private String filepath;
	
	public OpenArrondissementCsv(String filepath) {
		this.filepath = filepath;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public List<Arrondissement> readCsvArrondissement() throws IllegalStateException, FileNotFoundException {
		
		return new CsvToBeanBuilder<Arrondissement>(new FileReader(this.filepath)).withType(Arrondissement.class).build().parse();
		
	}

	public static void main(String[] args) throws IOException {
		
		String filename = "C:\\Users\\adupu\\Documents\\Master\\M2\\WebSemantique\\Projet\\project\\src\\main\\resources\\csv\\arrondissements.csv";
		
		List<Arrondissement> beans = new CsvToBeanBuilder<Arrondissement>(new FileReader(filename)).withType(Arrondissement.class).build().parse();

        beans.forEach(System.out::println);

	}

}
