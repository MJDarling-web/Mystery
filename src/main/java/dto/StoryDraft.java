package dto;

import java.io.Serializable;
import java.util.*;

/**
 * Data Transfer Object to hold story draft from Host New Story JSP.
 * The StoryDraft serves as a container for a story in progress.
 */
public class StoryDraft implements Serializable {
    private String title;
    private String description;
    private String setting;

    /*
     * Scenes list holds a list of ScenesDraft objects so scenes can be added,
     * reviewed, and edited prior to persisting the completed Story.
     */
    private List<ScenesDraft> scenes = new ArrayList<>();

    //no args constructor
    public StoryDraft() {}

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
    public List<ScenesDraft> getScenes() {
        return scenes;
    }
    public void setScenes(List<ScenesDraft> scenes) {
        this.scenes = scenes;
    }


}
