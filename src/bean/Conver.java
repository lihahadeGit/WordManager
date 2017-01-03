package bean;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;



public class Conver {

	public String ConverToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	} 
	public Date ConverToDate(String strDate) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
	    Date sqlDate = new Date(df.parse(strDate).getTime());
		return sqlDate;
	}
	public Date ConverToDateGeneral(String strDate) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date sqlDate = new Date(df.parse(strDate).getTime());
		return sqlDate;
	}

}
