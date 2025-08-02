package entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

//holds information on evidence players uncover, such as clue description, locationFound, boolean isFound, Character relatedCharacter
@Entity
@Table(name = "clue")
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "location_found")
    private String locationFound;

    @Column(name = "is_found")
    private boolean isFound;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    public Clue() {}

    public Clue(String title, String description, String imageUrl, String locationFound, boolean isFound, Story story) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.locationFound = locationFound;
        this.isFound = isFound;
        this.story = story;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getLocationFound() { return locationFound; }
    public void setLocationFound(String locationFound) { this.locationFound = locationFound; }
    public boolean isFound() { return isFound; }
    public void setFound(boolean found) { isFound = found; }
    public Story getStory() { return story; }
    public void setStory(Story story) { this.story = story; }
}
