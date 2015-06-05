import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateCell extends Cell{
	 Date date;
	 SimpleDateFormat sdf;
	public DateCell(String date) throws ParseException{
		this.sdf = new SimpleDateFormat(date);
		this.date = sdf.parse(date);
	}
	public String eval(Sheet sheet){
		return sdf.format(date);
	}
	public String toString(Sheet sheet){
		return eval(sheet);
	}
}

