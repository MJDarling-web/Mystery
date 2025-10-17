package service;

import dto.DraftSaver;
import dto.StoryDraft;
import entity.Story;
import entity.User;
import persistence.GenericDao;

public class HibernateDraftSaver implements DraftSaver {
    private final GenericDao<Story> storyDao;
    public HibernateDraftSaver(GenericDao<Story> storyDao) {this.storyDao = storyDao;}

    public HibernateDraftSaver() {
        this(new GenericDao<>(Story.class));
    }

    @Override
    public int save(StoryDraft storyDraft, User creator) {
        Story s = new Story();
        s.setTitle(storyDraft.getTitle());
        s.setDescription(storyDraft.getDescription());
        s.setSetting(storyDraft.getSetting());
        s.setCreator(creator);
        return storyDao.insert(s);
    }
}
