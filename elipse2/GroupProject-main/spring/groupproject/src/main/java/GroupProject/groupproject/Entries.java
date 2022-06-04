package GroupProject.groupproject;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Entries {
	private int id;
	private String imageUrl;
	private String title;
	private String author;
	
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
	
	public void setDate(LocalDateTime date) {
		this.date = date;
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
