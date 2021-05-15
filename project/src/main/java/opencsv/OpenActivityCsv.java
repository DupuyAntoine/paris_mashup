package opencsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import models.Activity;

public class OpenActivityCsv {

	public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException {
		
		String filename = "C:\\Users\\adupu\\Documents\\Master\\M2\\WebSemantique\\Projet\\project\\src\\main\\resources\\csv\\que-faire-a-paris-.csv";
		
		List<Activity> beans = new CsvToBeanBuilder<Activity>(new FileReader(filename)).withSeparator(';').withType(Activity.class).build().parse();

        beans.forEach(System.out::println);

	}

}
