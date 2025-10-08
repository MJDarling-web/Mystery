package dto;

import java.io.Serializable;

public class CharacterDraft implements Serializable {
    private String name;
    private String role;
    private String bio;
    private boolean guilty;
    private boolean murdered;
    private String imageUrl;

    public CharacterDraft() {}
    public CharacterDraft(String name, String role, String bio, boolean guilty, boolean murdered, String imageUrl) {
        this.name = name; this.role = role; this.bio = bio;
        this.guilty = guilty; this.murdered = murdered; this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public boolean isGuilty() { return guilty; }
    public void setGuilty(boolean guilty) { this.guilty = guilty; }

    public boolean isMurdered() { return murdered; }
    public void setMurdered(boolean murdered) { this.murdered = murdered; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
