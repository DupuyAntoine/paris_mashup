package opencsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import models.Activity;

public class OpenActivityCsv {
	
	private String filepath;
	
	public OpenActivityCsv(String filepath) {
		this.filepath = filepath;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public List<Activity> readCsvActivity() throws IllegalStateException, FileNotFoundException {
		
		return new CsvToBeanBuilder<Activity>(new FileReader(this.filepath)).withSeparator(';').withType(Activity.class).build().parse();
		
	}

	public static void main(String[] args) throws IOException {
		
		String filename = "src\\main\\resources\\csv\\que-faire-a-paris-.csv";
		
		List<Activity> beans = new CsvToBeanBuilder<Activity>(new FileReader(filename)).withSeparator(';').withType(Activity.class).build().parse();

        beans.forEach(System.out::println);

	}

}
