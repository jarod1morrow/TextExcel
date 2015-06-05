import java.io.PrintStream;
import java.text.ParseException;

import persistence.Savable;

public class Sheet implements Savable{
	public int rows;
	public int cols;
	Cell[][] contents;
	public Cell cell(int cols,int rows){
		Cell c = this.contents[cols][rows];
		return c;
	}
	public Sheet(int cols, int rows){
		this.rows = rows;
		this.cols = cols;
		contents = new Cell [cols][rows];
		for (int i = 0; i < cols; i++){
			for (int k = 0; k < rows; k++){
				contents[i][k] = new EmptyCell();
			}
		}
	}
	//prints Cell that is asked for
	public void lookupCell(int col, int row, PrintStream out){
		String name = toLet(col) + (row+1) + " = ";
		if(contents[col][row].toString() == ""){
			out.println(name + "<empty>");
		}
		else{ 
			out.println(name + contents[col][row].toString(this));
		}
	}
	//sets the cell based on what is inputed
	public void setCell(int col, int row, String content) throws ParseException{
		//STRING SETTING
		if (content.contains("\"")){
			StringCell content1 = new StringCell(content);
			contents[col][row] = content1;
		}
		//FORMULA SETTING
		else if(content.contains("(")){
			FormulaCell content1 = new FormulaCell(content);
			contents[col][row] = content1;
		}
		//DATE SETTING
		else if(content.contains("/") || content.contains("-")){
			DateCell content1 = new DateCell(content);
			contents[col][row] = content1;
		}
		//EMPTY CELL SETTING
		else if(content.length() == 0){
			EmptyCell content1 = new EmptyCell();
			contents[col][row] = content1;
		}
		//ELSE NUMBER CELL
		else{
			NumberCell content1 = new NumberCell(content);
			contents[col][row] = content1;
		}
	}
	public void setCell(int col, int row, Cell content){
		contents[col][row] = content;
	}
	public void clearCell(int col, int row){
		contents[col][row] = new EmptyCell();
	}
	//clears whole sheet
	public void clear(){
		for (int i = 0; i < cols; i++){
			for (int k = 0; k < rows; k++){
				contents[i][k] = new EmptyCell();
			}
		}
	}
	//sorts data smallest to largest
	public void sorta(String data){
		int rowStart;
		int rowEnd;
		int colStart;
		int colEnd;
	}
	//sorts data Largest to smallest
	public void sortd(String data){
		
	}
	//prints Excel Sheet
	public void print(PrintStream out){
		String line = "------------+";
		for (int o = 0; o < cols; o++){
			line = line + "------------+";
		}
		for (int i = -1; i < rows; i++){
			if(i == -1){
				out.print("            |");
				for (int r = 0; r < cols; r++){
					out.print("     " + toLet(r) + "      |");
				}
				out.println();
				out.println(line);
			}
			else{
				for (int k = -1; k < cols; k++){
					if(k == -1){
						if(i+1<10){
						out.print("     " + (i+1) + "      |");
						}
						else{
							out.print("     " + (i+1) + "     |");
						}
					}
					else{
						out.print(spacer(contents[k][i].eval(this))+"|");
					}
				}
				out.println();
				out.println(line);
			}
		}
	}
	public String toLet(int i){
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String c = alpha.substring(i, i+1).toUpperCase();
		return c;
	}
	public int toInt(String c){
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i = alpha.indexOf(c.toUpperCase());
		return i;
	}
	private String spacer(String eval) {
		int i = 12 - eval.length();
		String done = "";
		if(i < 0){
			String kur = eval.substring(0, 11);
			done = kur + ">";
		}
		else{
			if (i % 2 == 1){
				int k = i - 1;
				int j = k/2;
				String l = "";
				for (int d = 0; d < j; d++){
					l = l + " ";
				}
				done = (l) + eval + (" " +l);
			}
			else{
				int f = i/2;
				String k = "";
				for (int w = 0; w < f; w++){
					k = k + " ";
				}
				done = k + eval + k;
			}
		}
		return done;
	}
	@Override
	public String[] getSaveData() {
		String[] data = new String[rows * cols];
		int index = 0;
		for(int i = 0; i < contents.length; i++){
			for(int k = 0; k < contents[0].length; k++){
				System.out.println(contents[i][k].toString());
				data[index] = contents[i][k].toString();
				index++;
			}
		}
		return data;
	}
	@Override
	public void loadFrom(String[] saveData) {
		int index = 0;
		for(int i = 0; i < cols; i++){
			for (int k = 0; k < rows; k++){
				try {
					this.setCell(i, k, saveData[index]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				index++;
			}
		}
	}
}

