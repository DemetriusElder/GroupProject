package GroupProject.groupproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String imageUrl;

	private String title;
	
	private LocalDateTime date;

	private String author;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private AuthUsers user;

	@Lob
	private String content;

	public Entry() {
	}

	public Entry(String imageUrl, String title, LocalDateTime date, AuthUsers user, String content) {
		this.imageUrl = imageUrl;
		this.title = title;
		this.date = date;
		this.user = user;
		this.content = content;
	}

	public Entry(String imageUrl, String title, LocalDateTime date, String author, AuthUsers user, String content) {
		this.imageUrl = imageUrl;
		this.title = title;
		this.date = date;
		this.author = author;
		this.user = user;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public AuthUsers getUser() {
		return user;
	}

	public void setUser(AuthUsers user) {
		this.user = user;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
