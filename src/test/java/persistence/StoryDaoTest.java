package persistence;

import entity.Story;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StoryDaoTest {

    private GenericDao<Story> storyDao;
    private GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        storyDao = new GenericDao<>(Story.class);
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void insertAndGetStory() {
        // Unique user
        String uname = "adminUser_" + UUID.randomUUID();
        User user = new User(uname, "password123", uname + "@example.com", true);
        int userId = userDao.insert(user);

        // Story
        Story story = new Story(
                "Murder at a Mansion",
                "A ghostly haunted mansion in Salsburg, Virginia two days before Christmas.",
                "Winter",
                user
        );

        int storyId = storyDao.insert(story);
        Story retrieved = storyDao.getById(storyId);

        assertNotNull(retrieved);
        assertEquals("Murder at a Mansion", retrieved.getTitle());
        assertEquals("A ghostly haunted mansion in Salsburg, Virginia two days before Christmas.", retrieved.getDescription());
        assertEquals("Winter", retrieved.getSetting());
        assertEquals(userId, retrieved.getCreator().getId());

        // Clean up
        storyDao.deleteEntity(retrieved);
        userDao.deleteEntity(user);
    }
}
