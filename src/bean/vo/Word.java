package bean.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

import bean.Conver;

public class Word {
	private String text_Chinese;
	private String text_English;
	private String alphabetUK;
	private String alphabetUS;
	private int reciteFlag;
	private int reciteNum;
	private Date addTime;
	private String resourceUrl;
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
	public int getReciteFlag() {
		return reciteFlag;
	}
	public void setReciteFlag(int reciteFlag) {
		this.reciteFlag = reciteFlag;
	}
	public int getReciteNum() {
		return reciteNum;
	}
	public void setReciteNum(int reciteNum) {
		this.reciteNum = reciteNum;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String toJsonString(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateStr = df.format(this.addTime);
		String reciteFlag = String.valueOf(this.reciteFlag);
		String reciteNum = String.valueOf(this.reciteNum);
		String returnStr = "text_Chinese:"+text_Chinese+","+"text_English:"+text_English+","+"alphabetUK:"+alphabetUK+","
				+"alphabetUS:"+alphabetUS+","+"reciteFlag:"+reciteFlag+","+"reciteNum:"+reciteNum+","+"dateStr:"+dateStr+","+"resourceUrl:"+resourceUrl;
		return returnStr;
		
	}
	
	public void setWordProperty(String text_Chinese,String text_English,
			String alphabetUK,String alphabetUS,int reciteFlag,int reciteNum,Date addTime,
			String resourceUrl) throws Exception{
		this.setText_Chinese(text_Chinese);
		this.setText_English(text_English);
		this.setAlphabetUK(alphabetUK);
		this.setAlphabetUS(alphabetUS);
		this.setReciteFlag(reciteFlag);
		this.setReciteNum(reciteNum);
		this.setAddTime(addTime);
		this.setResourceUrl(resourceUrl);
	}
	

}
