package GroupProject.groupproject.dto;

public class PostEntryDto {

    private final String author;
    private final String content;
    private final String imageUrl;
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
