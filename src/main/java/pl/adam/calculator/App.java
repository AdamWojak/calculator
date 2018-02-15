package pl.adam.calculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to Calculator App.");

		String nameOfTheFile = "calculatorData.txt";

		mathematicalOperations(readFromFile(nameOfTheFile));

	}

	public static List<Operation> readFromFile(String fileName) throws IOException {

		List<Operation> listOfOperations = new LinkedList<>();

		Path path = Paths.get(fileName);

		if (!(Files.exists(path))) {
			System.out.println("Such file does not exist!");
			System.out.println("The end of the program.");
			Files.createFile(path);

		} else {

			System.out.println("Such file exist. Work in progress...");

			File data = new File(fileName);
			Scanner scan = new Scanner(data);

			String line = "";

			Pattern quitPattern = Pattern.compile("^apply\\s.*");
			Matcher matcher;

			do {
				line = scan.nextLine();
				String[] lineParts = line.split(" ");
				Operation operation = new Operation(lineParts[0], Double.parseDouble(lineParts[1]));

				listOfOperations.add(operation);

				matcher = quitPattern.matcher(line);
			} while (!(matcher.find() == true));

			scan.close();

		}
		return listOfOperations;
	}

	public static void mathematicalOperations(List<Operation> listOfOperations) {

		double number = listOfOperations.get(listOfOperations.size() - 1).getValue();
		// System.out.println(number);

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
