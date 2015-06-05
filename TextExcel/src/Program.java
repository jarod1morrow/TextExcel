import java.text.ParseException;
import java.util.Scanner;
import java.io.PrintStream;

import persistence.PersistenceHelper;

public class Program {
	public static void main(String[] args){
		//greets user and prompts command
		System.out.println("Welcome to TextExcel!\nType in a command");
		//gets input from user
		Scanner scan = new Scanner(System.in);
		//initializes sheet
		Program program = new Program(10,7,System.out);
		//sorts input to commands
		while(program.processLine(scan) == true){	
		}
	}
	
	//initializes variables
	public int rows;
	public int cols;
	PrintStream printstream;
	Sheet sheet;
	
	//creates sheet with rows, cols, and printstream
	public Program(int rows, int cols, PrintStream printstream){
		this.rows = rows;
		this.cols = cols;
		this.printstream = printstream;
		this.sheet = new Sheet(cols, rows);
	}
	
	//gets sheet
	public Sheet getSheet(){
		return this.sheet;
	}
	
	//calls classes based on user input
	public boolean processLine(Scanner scan){
		String line = scan.nextLine();
		//exits program
		if (line.equals("exit")){
			return false;
		}
		//prints sheet
		else if(line.equals("print")){
			sheet.print(printstream);
			return true;
		}
		//removes all entries in sheet
		else if(line.equals("clear")){	
			sheet.clear();
			return true;
		}
		//clears cell specified by user input
		else if(line.contains("clear ")){
			String line2 = line.substring(6);
			int colsLook = sheet.toInt(line2.substring(0,1).toUpperCase());
			int rowsLook = Integer.parseInt(line2.substring(1))-1;
			sheet.clearCell(colsLook, rowsLook);
			return true;
		}
		//saves all data into a file
		else if(line.contains("save")){
			String filename = line.substring(5);
			try {
				PersistenceHelper.save(filename, sheet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		//sorts data from smallest to largest
		else if(line.contains("sorta")){
			sheet.sorta(line);
			return true;
		}
		//sorts data from largest to smallest by calling sheet.sortd
		else if(line.contains("sortd")){
			sheet.sortd(line);
			return true;
		}
		//loads data from file into sheet now
		else if(line.contains("load")){
			String filename = line.substring(5);
			try {
				PersistenceHelper.load(filename, sheet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		//looks up specific cell in the sheet
		else if(line.length() == 2){
			int colsLook = sheet.toInt(line.substring(0,1).toUpperCase());
			int rowsLook = Integer.parseInt(line.substring(1))-1;
			sheet.lookupCell(colsLook, rowsLook, printstream);
			return true;
		}
		//sets cell to the cell specified by user input
		else if(line.contains("=")){
			String part1 = line.substring(0,line.indexOf("=")-1);
			String row2 = part1.substring(0, 1);
			int col = sheet.toInt(row2.toUpperCase());
			int row =  Integer.parseInt(part1.substring(1))-1;
			String content = line.substring(line.indexOf("=")+2);
			try {
				sheet.setCell(col, row, content);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return true;
		}
		//outputs idk when an unknown command is entered
		else{
			System.out.println("I'm sorry I don't know that imput.\nPlease try again.");
			return true;
		}
	}
}