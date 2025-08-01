package entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

//holds information on evidence players uncover, such as clue description, locationFound, boolean isFound, Character relatedCharacter
@Entity
@Table(name ="clue")

public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String image; //url
    private String locationFound;
    private boolean isFound;

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }
}
