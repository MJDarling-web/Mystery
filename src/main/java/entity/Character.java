package entity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "picture_url")
    //TODO pictureUrl import and hosting
    private String pictureUrl;
    //TODO should have character firstname and lastname for character clarity and better use of profile w/ no image.
    private String name;
    private String role;
    private String bio;

    @Column(name = "is_guilty")
    private boolean isGuilty;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    public Character() {}

    public Character(String name, String role, String bio, boolean isGuilty, String pictureUrl, Story story) {
        this.name = name;
        this.role = role;
        this.bio = bio;
        this.isGuilty = isGuilty;
        this.pictureUrl = pictureUrl;
        this.story = story;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPictureUrl() { return pictureUrl; }
    public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public boolean isGuilty() { return isGuilty; }
    public void setGuilty(boolean guilty) { isGuilty = guilty; }
    public Story getStory() { return story; }
    public void setStory(Story story) { this.story = story; }
}


//Holds info such as: name, alibi, motive, dialogues, isGuilty?
//Could have a substring of Suspect