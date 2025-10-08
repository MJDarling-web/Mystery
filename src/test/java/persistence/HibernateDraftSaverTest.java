package persistence;

import dto.DraftSaver;
import dto.StoryDraft;
import entity.Story;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GenericDao;
import service.HibernateDraftSaver;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateDraftSaverTest {
    private GenericDao<Story> storyDao;
    private GenericDao<User> userDao;
    private DraftSaver saver;

    @BeforeEach void setUp() {
        storyDao = new GenericDao<>(Story.class);
        saver = new HibernateDraftSaver(storyDao);
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void save_insertsStoryFromDraft_withCreator_andReturnsId() {
        String uname = "adminUser_" + UUID.randomUUID();
        User user = new User(uname, "password123", uname + "@example.com", true);
        int userId = userDao.insert(user);
        User persistentUser = userDao.getById(userId);

        StoryDraft storyDraft = new StoryDraft("The final hour", "Cinema moments", "202 Sassy st" );

        int newId = saver.save(storyDraft, persistentUser);
        assertTrue(newId > 0);

        Story saved = storyDao.getById(newId);
        assertNotNull(saved);
        assertEquals(userId, saved.getCreator().getId());

        //clean
        storyDao.deleteEntity(saved);
        userDao.deleteEntity(persistentUser);
    }
}
