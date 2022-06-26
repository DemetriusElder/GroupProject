package GroupProject.groupproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostEntryDto {

    @NotBlank(message = "Author cannot be empty")
    private final String author;

    @Size(min = 200, message = "Content must be at least 200 characters")
    private final String content;

    @NotBlank(message = "Author cannot be empty")
    private final String imageUrl;

    @Size(min = 15, message = "Title must be at least 15 characters")
    private final String title;

    public PostEntryDto(String author, String content, String imageUrl, String title) {
        this.author = author;
        this.content = content;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
