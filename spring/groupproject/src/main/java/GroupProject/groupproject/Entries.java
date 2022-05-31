package GroupProject.groupproject;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Entries {
	private int id;
	private String imageUrl;
	private String title;
	private String author;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
	
	private String content;
	
	//setters 
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public void setAuthor(String author) {
		this.author=author;
	}
	
	public void setDate(String date) {
		//year month day hour minute second
		String[] dateFromFile = date.split(" ");
		int[] dateInt = new int[6];
		
		for(int i=0;i<dateInt.length;i++) {
			dateInt[i]=Integer.parseInt(dateFromFile[i]);
		}
		
		this.date = LocalDateTime.of(dateInt[0], dateInt[1],dateInt[2],dateInt[3],dateInt[4], dateInt[5]);
		
	}
	
	public void setContent(String content) {
		this.content=content;
	}
	
	// getters
	
	public int getId() {
		return this.id;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public String getTitle() {
		return this.title;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	public LocalDateTime getDate() {
		return this.date;
	}
	
	public String getAuthor() {
		return this.author;
	}
	public String getContent() {
		return this.content;
	}

}
