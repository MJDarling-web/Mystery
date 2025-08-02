package persistence;

import entity.Clue;
import entity.Story;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClueDaoTest {

    private GenericDao<Clue> clueDao;
    private GenericDao<Story> storyDao;
    private GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        clueDao = new GenericDao<>(Clue.class);
        storyDao = new GenericDao<>(Story.class);
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void insertAndGetClue() {
        // Set up user
        User user = new User("adminUser", "securePassword", "admin@example.com", true);
        int userId = userDao.insert(user);

        // Set up story
        Story story = new Story("Clue in the Kitchen", "A suspicious case near the holidays", "Kitchen", user);
        int storyId = storyDao.insert(story);

        // Set up clue
        Clue clue = new Clue(
                "Dull Knife",
                "A knife with a slight red tint near the handle.",
                "https://images.example.com/dull-knife.png",
                "Under the sink",
                false,
                story
        );
        int clueId = clueDao.insert(clue);

        // Retrieve and validate
        Clue retrieved = clueDao.getById(clueId);
        assertNotNull(retrieved);
        assertEquals("Dull Knife", retrieved.getTitle());
        assertEquals("A knife with a slight red tint near the handle.", retrieved.getDescription());
        assertEquals("https://images.example.com/dull-knife.png", retrieved.getImageUrl());
        assertEquals("Under the sink", retrieved.getLocationFound());
        assertFalse(retrieved.isFound());
        assertEquals(storyId, retrieved.getStory().getId());

        // Cleanup
        clueDao.deleteEntity(retrieved);
        storyDao.deleteEntity(story);
        userDao.deleteEntity(user);
    }
}
