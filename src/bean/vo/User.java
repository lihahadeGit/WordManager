package bean.vo;

public class User {
	private String username;
	private String password;
	private String wordtable;
	private String glossaryName;
	private String glossaryNameInternal;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWordTable() {
		return wordtable;
	}
	public void setWordTable(String wordTable) {
		this.wordtable = wordTable;
	}
	public String getGlossaryName() {
		return glossaryName;
	}
	public void setGlossaryName(String glossaryName) {
		this.glossaryName = glossaryName;
	}
	public String getGlossaryNameInternal() {
		return glossaryNameInternal;
	}
	public void setGlossaryNameInternal(String glossaryNameInternal) {
		this.glossaryNameInternal = glossaryNameInternal;
	}
}
