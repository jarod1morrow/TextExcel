import java.util.ArrayList;


public class FormulaCell extends Cell{
	
	//the version of the notAnswered with spaces
	String notAnsweredS;
	
	//when asked by the USER what the cell value is
	String notAnsweredP;
	
	//the answer as a double
	double answer;
	
	//when asked by the SHEET what the cell value is
	String answereS;
	
	public FormulaCell(String content){
		
		//sets notAnsweredP (answer with parenthesis)
		this.notAnsweredP = content;
		
		//creates string without parenthesis for answering
		this.notAnsweredS = content.substring(2,content.length()-2);
		
	}
	public String eval(Sheet sheet){
		
		//checks if reference to another cell
		if(!notAnsweredP.contains("+") && !notAnsweredP.contains("-") && !notAnsweredP.contains("/") && !notAnsweredP.contains("*")){
			int row = sheet.toInt((notAnsweredP.charAt(2) + "").toUpperCase());
			int col = Integer.valueOf(notAnsweredP.charAt(3)+ "");
			return sheet.contents[col-1][row].toString();
		}
		
		//tests to see if avg or sum and does calculations
		if(notAnsweredP.contains("avg") || notAnsweredP.contains("sum")){
			int startCol = sheet.toInt(notAnsweredP.substring(6,7));
			int startRow = -1 + Integer.valueOf(notAnsweredP.substring(7,8));
			int endCol = sheet.toInt(notAnsweredP.substring(11,12));
			int endRow = -1 + Integer.valueOf(notAnsweredP.substring(12,13));
			double sum = 0.0;
			double counter = 0.0;
			for (int col = startCol; col <= endCol; col++){
				for (int row = startRow; row <= endRow; row++){
					sum = sum + Double.valueOf(sheet.contents[col][row].toString());
					counter += 1.0;
				}
			}
			if(notAnsweredP.contains("avg")){
				sum = sum/counter;
			}
			this.answer = sum;
			return Double.toString(sum);
		}
		//creates notAnswered with no spaces
		String noSpaces = notAnsweredS.replace(" ", "");
				
		//creates two arrayLists with number then operation
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> operations = new ArrayList<String>();
			
		//creates counter for multiple operations
		int index = -1;
				
		//puts values into arrays
		for(int i = 0; i < noSpaces.length(); i++){
			if((noSpaces.charAt(i)+"").equals("+") || (noSpaces.charAt(i)+"").equals("-") || (noSpaces.charAt(i)+"").equals("*") || (noSpaces.charAt(i)+"").equals("/")){
				data.add(noSpaces.substring(index+1,i));
				operations.add(noSpaces.charAt(i) + "");
				index = i;
			}
		}
				
		//adds last value
		data.add(noSpaces.substring(index+1));
		
		//initializes answer
		double a = calculate(data.get(0),sheet);
				
		//counter for operations
		int l = 0;
				
		//goes through both array lists and does calculations
		for(int j = 1; j < data.size(); j++){
			String e = operations.get(l);
			double o = calculate(data.get(j),sheet);/*Double.valueOf(data.get(j));*/
			if(e.equals("+")){
				a += o;
			}
			if(e.equals("-")){
				a -= o;
			}
			if(e.equals("/")){
				a /= o;
			}
			if(e.equals("*")){
				a *= o;
			}
			l++;
		}
				
		//sets answers as double and string
		this.answer = a;
		return Double.toString(a);
	}
	//checks if data is a cell or not then changes it to double
	public Double calculate(String s, Sheet sheet){
		double d;
		if(Character.isAlphabetic(s.charAt(0))){
			int col = sheet.toInt(s.charAt(0)+"");
			int row = Integer.valueOf(s.charAt(1)+"");
			String string = sheet.contents[col][row-1].toString();
			d = Double.valueOf(string);
		}
		else{
			d = Double.valueOf(s);
		}
	return d;
	}
	public String toString(Sheet sheet){
		return notAnsweredP;
	}
	public String toString(){
		return notAnsweredP;
	}
	public Double toDouble(String s){
		return Double.valueOf(s);
	}
}
