package pl.adam.calculator;

public class Operation {

	private String nameOfOperation;
	private double value;

	public Operation() {

	}

	public Operation(String nameOfOperation, double value) {
		this.nameOfOperation = nameOfOperation;
		this.value = value;
	}

	public String getNameOfOperation() {
		return nameOfOperation;
	}

	public void setNameOfOperation(String nameOfOperation) {
		this.nameOfOperation = nameOfOperation;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
