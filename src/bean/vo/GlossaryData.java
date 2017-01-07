package bean.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class GlossaryData {
	private int wordId;
	private String text_Chinese;
	private String text_English;
	private String alphabetUK;
	private String alphabetUS;
	private int next;
	private int type;
	private Date addTime;
	
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	public String getText_Chinese() {
		return text_Chinese;
	}
	public void setText_Chinese(String text_Chinese) {
		this.text_Chinese = text_Chinese;
	}
	public String getText_English() {
		return text_English;
	}
	public void setText_English(String text_English) {
		this.text_English = text_English;
	}
	public String getAlphabetUK() {
		return alphabetUK;
	}
	public void setAlphabetUK(String alphabetUK) {
		this.alphabetUK = alphabetUK;
	}
	public String getAlphabetUS() {
		return alphabetUS;
	}
	public void setAlphabetUS(String alphabetUS) {
		this.alphabetUS = alphabetUS;
	}
	
	public String toJsonString(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateStr = df.format(this.addTime);
		String wordId = String.valueOf(this.wordId);
		String next = String.valueOf(this.next);
		String type = String.valueOf(this.type);
		String returnStr = "wordid:"+wordId+","+"next:"+next+","+"type:"+type+","+"text_Chinese:"+text_Chinese+","+"text_English:"+text_English+","+"alphabetUK:"+alphabetUK+","
				+"alphabetUS:"+alphabetUS+","+"dateStr:"+dateStr;
		return returnStr;
		
	}

}
