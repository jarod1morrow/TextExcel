
public class NumberCell extends Cell{
	String number;
	public NumberCell(String number){
		this.number = number;
	}
	public String eval(Sheet sheet){
		return this.toDouble().toString();
	}
	public String toString(){
		return this.toDouble().toString();
	}
	public Double toDouble(){
		return Double.valueOf(number);
	}
}
