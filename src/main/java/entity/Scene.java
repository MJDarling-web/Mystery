package entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

/**
 * Entity to CRUD scene for the story
 */
@Entity
@Table(name = "scene")
public class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private int storyId;
    private int adminUserId;
    private int currentClueIndex;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public int getCurrentClueIndex() {
        return currentClueIndex;
    }

    public void setCurrentClueIndex(int currentClueIndex) {
        this.currentClueIndex = currentClueIndex;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}

