package dto;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a draft version of a scene within a story.
 * Used by StoryDraft to collect scenes during the story creation process
 * so they can be reviewed, edited, and later persisted with the final Story.
 */

public class ScenesDraft implements Serializable {
    private String title;
    private String description;
    private int displayOrder;

    //no args constructor
    public ScenesDraft() {}

    public ScenesDraft(String title, String description, int displayOrder) {
        this.title = title;
        this.description = description;
        this.displayOrder = displayOrder;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

}
