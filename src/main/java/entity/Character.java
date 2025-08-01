package entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name ="game_character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String role; // e.g. "suspect", "detective", "witness"
    private String alibi;
    private String bio;
    private boolean isGuilty;

    public Character() {}

    public Character(String name, String role, String alibi, String bio, boolean isGuilty) {
        this.name = name;
        this.role = role;
        this.alibi = alibi;
        this.bio = bio;
        this.isGuilty = isGuilty;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAlibi() {
        return alibi;
    }

    public void setAlibi(String alibi) {
        this.alibi = alibi;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isGuilty() {
        return isGuilty;
    }

    public void setGuilty(boolean guilty) {
        isGuilty = guilty;
    }

}

//Holds info such as: name, alibi, motive, dialogues, isGuilty?
//Could have a substring of Suspect