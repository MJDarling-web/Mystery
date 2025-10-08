package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoryDraft implements Serializable {
    private String title;
    private String description;
    private String setting;

    private List<ScenesDraft> scenes = new ArrayList<>();
    private List<ClueDraft> clues = new ArrayList<>();
    private List<CharacterDraft> characters = new ArrayList<>();

    public StoryDraft() {}
    public StoryDraft(String title, String description, String setting) {
        this.title = title; this.description = description; this.setting = setting;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSetting() { return setting; }
    public void setSetting(String setting) { this.setting = setting; }

    public List<ScenesDraft> getScenes() { return scenes; }
    public void setScenes(List<ScenesDraft> scenes) { this.scenes = scenes == null ? new ArrayList<>() : scenes; }

    public List<ClueDraft> getClues() { return clues; }
    public void setClues(List<ClueDraft> clues) { this.clues = clues == null ? new ArrayList<>() : clues; }

    public List<CharacterDraft> getCharacters() { return characters; }
    public void setCharacters(List<CharacterDraft> characters) { this.characters = characters == null ? new ArrayList<>() : characters; }

    // helpers
    public void addScene(ScenesDraft s) { if (s != null) scenes.add(s); }
    public void removeScene(int i) { if (i>=0 && i<scenes.size()) scenes.remove(i); }

    public void addClue(ClueDraft c) { if (c != null) clues.add(c); }
    public void removeClue(int i) { if (i>=0 && i<clues.size()) clues.remove(i); }

    public void addCharacter(CharacterDraft c) { if (c != null) characters.add(c); }
    public void removeCharacter(int i) { if (i>=0 && i<characters.size()) characters.remove(i); }
}
