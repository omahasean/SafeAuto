package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportWriter implements DataWriter{

	private File file;
	private String fileName;
	//@AutoWire
	public ReportWriter(String fileName) {
		this.file = new File(fileName);
	}
	
	@Override
	public void addDriverToFile(String driverName) throws IOException {
		try(FileWriter writer = new FileWriter(file, true)){
			try(BufferedWriter bw = new BufferedWriter(writer)){
			bw.write("Driver,"+driverName +"\n");
			}
		}
	}

	@Override
	public void addTripToFile(Trip trip) throws IOException {
		try(FileWriter writer = new FileWriter(file, true)){
			try(BufferedWriter bw = new BufferedWriter(writer)){
			bw.write("Trip,"+trip.getId()+","+trip.getDriverName()+","+convertLocalDateTimeToString(trip.getStartTime())+","+convertLocalDateTimeToString(trip.getEndTime())+","+trip.getMilesDriven()+"\n");
			}
		}	
	}

	@Override
	public void removeTripFromFile(int tripId) throws IOException {
		File newFile = File.createTempFile("temp", ".csv");
		try(Scanner fileScanner = new Scanner(file)){
			try(FileWriter writer = new FileWriter(newFile)){
			while(fileScanner.hasNextLine()) {
				String nextLine = fileScanner.nextLine();
				String[] tripFields = nextLine.split(",");
				String type = tripFields[0];
				if(type.equals("Trip")) {
					if(Integer.parseInt(tripFields[1])==tripId) {
						//will not write line if it matches the tripID
					}
					else {
						writer.append(nextLine +"\n");
					}
				}
				else {
					writer.append(nextLine+"\n");
				}

			}
		}
		}
		newFile.renameTo(file);
	}

	@Override
	public void removeDriverFromFile(String driverName) throws IOException {
		File newFile = File.createTempFile("temp", ".csv");
		try(Scanner fileScanner = new Scanner(file)){
			try(FileWriter writer = new FileWriter(newFile)){
				try(BufferedWriter bw = new BufferedWriter(writer)) {
					while(fileScanner.hasNextLine()) {
						String nextLine = fileScanner.nextLine();
						String[] tripFields = nextLine.split(",");
						String type = tripFields[0];
						if(type.equals("Driver")) {
							if(tripFields[1].equals(driverName)) {
								//will not write line if it matches the driverName
							}
							else {
								bw.write(nextLine +"\n");
							}
						}
						else {
							bw.write(nextLine+"\n");
						}

					}
				}
				}

		}
		newFile.renameTo(file);
	}
	
	private String convertLocalDateTimeToString(LocalDateTime date) {
		String day = convertIntegerToString(date.getDayOfMonth());
		String month = convertIntegerToString(date.getMonthValue());
		Integer year = date.getYear();
		String hour = convertIntegerToString(date.getHour());
		String minute = convertIntegerToString(date.getMinute());
		String result = "";
		
		return result.concat(month +"-"+day+"-"+year.toString()+"-"+hour+":"+minute);
		
	}
	
	private String convertIntegerToString(int i) {
		Integer e = i;
		if(i<10) {
			String zero = "0";
			return zero.concat(e.toString());
		}
		else {
			return e.toString();
		}
	}

}
