package dto;

import java.io.Serializable;

/**
 * Data Transfer Object to hold story draft from Host New Story JSP
 */
public class StoryDraft implements Serializable {
    private String title;
    private String description;
    private String setting;

    public StoryDraft(String title, String description, String setting) {
        this.title = title;
        this.description = description;
        this.setting = setting;
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
    public String getSetting() {
        return setting;
    }
    public void setSetting(String setting) {
        this.setting = setting;
    }


}
