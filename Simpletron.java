// Simpletron.java
import java.util.Scanner;
public class Simpletron {
	private static final int READ = 10;
	private static final int WRITE = 11;
	private static final int LOAD = 20;
	private static final int STORE = 21;
	private static final int ADD = 30;
	private static final int SUBTRACT = 31;
	private static final int DIVIDE = 32;
	private static final int MULTIPLY = 33;
	private static final int BRANCH = 40;
	private static final int BRANCHNEG = 41;
	private static final int BRANCHZERO = 42;
	private static final int HALT = 43;
	private static int accumulator = 0;
	private static int instructionRegister = 0;

	public static void main(String[] args) {
		System.out.println("*** Welcome to Simpletron! ***");
		System.out.println("*** Please enter your program one instruction ***");
		System.out.println("*** (or data word) at a time. I will display ***");
		System.out.println("*** the location number and a question mark (?). ***");
		System.out.println("*** You then type the word for that location. ***");
		System.out.println("*** Type -99999 to stop entering your program. ***");
		
		Scanner enter = new Scanner(System.in);
		
		int[] memory = new int[100];
		int entered = 0;
		for(int loop = 0; loop < memory.length; loop++) {
			System.out.printf("%d ? ", loop);
			entered = enter.nextInt();
			if(entered == -99999) {
				break;
			}
			else {
				memory[loop] = entered;
			}
		}
		
		System.out.println("*** Program loading completed ***");
		System.out.println("*** Program execution begins ***");
		int instructionCounter = 0;
		int operand = 0, operationCode = 0;
		
		int exit = 0;
		for(int loop = 0; loop < memory.length; loop++) {
			instructionRegister = memory[instructionCounter];
			instructionCounter++;
			operationCode = instructionRegister / 100;
			operand = instructionRegister % 100;
			if(operationCode == HALT) {
				exit++;
				if(exit > 1) {
					break;
				}
			}
			switch(operationCode) {
				case READ:
					System.out.println("Enter an integer:-");
					memory[operand] = enter.nextInt();
					break;
				case WRITE:
					System.out.print(memory[operand]);
					System.out.println();
					break;
				case LOAD:
					accumulator = memory[operand];
					break;
				case STORE:
					memory[operand] = accumulator;
					break;
				case ADD:
					accumulator += memory[operand];
					break;
				case SUBTRACT:
					accumulator -= memory[operand];
					break;
				case DIVIDE:
					accumulator /= memory[operand];
					break;
				case MULTIPLY:
					accumulator *= memory[operand];
					break;
				case BRANCH:
					instructionCounter = operand;
					break;
				case BRANCHNEG:
					if(accumulator < 0) {
						instructionCounter = operand;
					}
					break;
				case BRANCHZERO:
					if(accumulator == 0) {
						instructionCounter = operand;
					}
					break;
				case HALT:
					System.out.println(" *** Simpletron execution terminated ***");
					break;
			}
			if(operationCode == HALT) {
				break;
			}
		}
		
		System.out.println("REGISTERS:");
		System.out.printf("accumulator		+%d%n", accumulator);
		System.out.printf("instructionCounter	 %d%n", instructionCounter);
		System.out.printf("instructionRegister	+%d%n", instructionRegister);
		System.out.printf("operationCode	 	 %d%n", operationCode);
		System.out.printf("operand	 		 %d%n", operand);
		System.out.println("	0	1	2	3	4	5	6	7	8");
		
		int done = 0;
		int[][] output = new int[10][9];
		for(int loop = 0; loop < output.length; loop++) {
			for(int loop1 = 0; loop1 < output[loop].length; loop1++) {
				output[loop][loop1] = memory[done];
				done++;
			}
		}
		int tens = 10;
		System.out.print("0	");
		for(int loop = 0; loop < output.length; loop++) {
			for(int loop1 = 0; loop1 < output[loop].length; loop1++) {
				System.out.printf("%d	", output[loop][loop1]);
			}
			System.out.println();
			if(tens < 100) {
				System.out.printf("%d	", tens);
			}
			tens += 10;
		}
	}
}