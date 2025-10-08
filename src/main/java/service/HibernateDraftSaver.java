package service;

import dto.DraftSaver;
import dto.StoryDraft;
import entity.Story;
import persistence.GenericDao;

public class HibernateDraftSaver implements DraftSaver {
    private final GenericDao<Story> storyDao;

    public HibernateDraftSaver(GenericDao<Story> storyDao) {
        this.storyDao = storyDao;
    }

    @Override
    public int save(StoryDraft storyDraft) {
        Story s = new Story();
        s.setTitle(storyDraft.getTitle());
        s.setDescription(storyDraft.getDescription());
        s.setSetting(storyDraft.getSetting());

        return storyDao.insert(s);
    }
}
