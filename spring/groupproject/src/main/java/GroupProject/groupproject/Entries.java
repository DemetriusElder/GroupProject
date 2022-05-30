package GroupProject.groupproject;


public class Entries {
	private String title;
	private String author;
	private String date;
	private String content;
	
	//setters 
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public void setAuthor(String author) {
		this.author=author;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setContent(String content) {
		this.content=content;
	}
	
	// getters
	
	public String getTitle() {
		return this.title;
	}
	public String getDate() {
		return this.date;
	}
	public String getAuthor() {
		return this.author;
	}
	public String getContent() {
		return this.content;
	}

}
