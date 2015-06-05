
public class StringCell extends Cell{
	String string;
	String f;
	public StringCell(String string){
		this.string = string;
		String k = string.substring(0,string.lastIndexOf("\""));
		f = k.substring(k.indexOf("\"")+1);
	}
	public String eval(Sheet sheet){
		return f;
	}
	public String toString(){
		return string;
	}
}
