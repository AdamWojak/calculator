package pl.adam.calculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

	public static void main(String[] args) throws IOException, NoSuchFileException, NoSuchElementException {

		System.out.println("Welcome to Calculator App.");

		String nameOfTheFile = "calculatorData.txt";

		mathematicalOperations(readFromFile(nameOfTheFile));

	}

	public static List<Operation> readFromFile(String fileName)
			throws IOException, NoSuchFileException, NoSuchElementException {

		Path path = Paths.get(fileName);

		if (!(Files.exists(path))) {
			// Files.createFile(path);
			throw new NoSuchFileException("Such file does not exist! The end of the program.");

		} else {
			List<Operation> listOfOperations = new LinkedList<>();

			System.out.println("Such file exist. Work in progress...");

			File data = new File(fileName);
			Scanner scan = new Scanner(data);

			String line = "";

			Pattern quitPattern = Pattern.compile("^apply\\s.*");
			Matcher matcher;

			if (scan.hasNextLine() == false) {
				scan.close();
				throw new NoSuchElementException("There is no text inside the file.");
			}
			do {
				line = scan.nextLine();
				String[] lineParts = line.split(" ");
				Operation operation = new Operation(lineParts[0], Double.parseDouble(lineParts[1]));

				listOfOperations.add(operation);

				matcher = quitPattern.matcher(line);
			} while (!(matcher.find() == true));

			scan.close();
			return listOfOperations;
		}

	}

	public static void mathematicalOperations(List<Operation> listOfOperations) {

		double number = listOfOperations.get(listOfOperations.size() - 1).getValue();

		for (int i = 0; i < listOfOperations.size(); i++) {
			String actualOperation = listOfOperations.get(i).getNameOfOperation();

			switch (actualOperation) {
			case "add":
				number = Calculator.add(number, listOfOperations.get(i).getValue());
				break;
			case "subtract":
				number = Calculator.subtract(number, listOfOperations.get(i).getValue());
				break;
			case "multiply":
				number = Calculator.multiply(number, listOfOperations.get(i).getValue());
				break;
			case "divide":
				number = Calculator.divide(number, listOfOperations.get(i).getValue());
				break;
			case "apply":
				break;
			default:
				throw new IllegalArgumentException("Invalid type of operation. Line: " + (i + 1));

			}

		}
		System.out.println("The result is: " + Calculator.round(number, Calculator.PLACES));
	}

}
