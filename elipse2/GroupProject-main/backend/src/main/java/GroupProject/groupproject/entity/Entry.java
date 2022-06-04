package GroupProject.groupproject.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String imageUrl;

	private String title;

	private String author;
	
	private LocalDateTime date;

	@Lob
	private String content;

	public Entry() {
	}

	public Entry(String imageUrl, String title, String author, LocalDateTime date, String content) {
		this.imageUrl = imageUrl;
		this.title = title;
		this.author = author;
		this.date = date;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
