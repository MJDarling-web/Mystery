package dto;

import entity.User;

public interface DraftSaver {

    int save(StoryDraft storyDraft, User creator);
}
