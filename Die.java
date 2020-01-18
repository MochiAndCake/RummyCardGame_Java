/**
* This class simulates a six-sided die.
*
* @author Faye Lin
* @author Ann Soong
*/

public class Die{

	private static final int MAXVALUE = 6;
	private int value;

	java.util.Random generator = new java.util.Random();

	//Constructor
	public Die(){
		value = generator.nextInt(MAXVALUE) + 1;
	}

	//Methods
	//Access method; returns the current value of the die
	public int getValue(){
		return value;
	}

	//Rolls the die for a new value
	public void roll(){
		this.value = generator.nextInt(MAXVALUE) + 1;
	}

	//Returns a string that describes the information in a Die object
	public String toString(){
		return ("Die {value:"+ this.value +"}");
	}

}
