package com.assignment.utils;

import java.io.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadCSVFile {

	private static final Logger logger = LogManager.getLogger(ReadCSVFile.class.getName());

	public int getRowCount() throws IOException, CsvValidationException {
		CSVReader reader = new CSVReader(new FileReader("./Datafiles/users.csv"));
		String[] input;
		int count = 0;
		while ((input = reader.readNext()) != null) {
			count++;
		}

		logger.info("Row count in CSV file : " + count);
		return count;
	}

	public Object[][] getCSVContent() throws IOException, CsvValidationException {
		CSVReader reader = new CSVReader(new FileReader("./Datafiles/users.csv"));
		String[] input;
		int rowCount = getRowCount();
		Object[][] credentials = new String[rowCount][3];
		int rowIndex = 0;
		while ((input = reader.readNext()) != null) {
			//logger.info("Data read : " + input[0] + ", " + input[1] + ", " + input[2]);
			credentials[rowIndex][0] = input[0];
			credentials[rowIndex][1] = input[1];
			credentials[rowIndex][2] = input[2];
			rowIndex++;
		}

		return credentials;
	}
}
