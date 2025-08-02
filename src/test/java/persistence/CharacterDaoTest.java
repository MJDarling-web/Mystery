package persistence;

import entity.Character;
import entity.Story;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterDaoTest {

    private GenericDao<Character> characterDao;
    private GenericDao<Story> storyDao;
    private GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        characterDao = new GenericDao<>(Character.class);
        storyDao = new GenericDao<>(Story.class);
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void insertAndGetCharacter() {
        // Insert user
        User user = new User("adminUser", "password123", "admin@example.com", true);
        int userId = userDao.insert(user);

        // Insert story
        Story story = new Story("Murder at the Mansion", "A mystery unfolds", "Victorian estate", user);
        int storyId = storyDao.insert(story);

        // Insert character tied to that story
        Character character = new Character(
                "Detective Jane",
                "detective",
                "Skilled investigator with a record of success.",
                false,
                "https://example.com/jane.png",
                story
        );
        int characterId = characterDao.insert(character);

        // Retrieve and verify
        Character retrieved = characterDao.getById(characterId);
        assertNotNull(retrieved);
        assertEquals("Detective Jane", retrieved.getName());
        assertEquals("detective", retrieved.getRole());
        assertFalse(retrieved.isGuilty());
        assertEquals("https://example.com/jane.png", retrieved.getPictureUrl());
        assertEquals(storyId, retrieved.getStory().getId());

        // Clean up
        characterDao.deleteEntity(retrieved);
        storyDao.deleteEntity(story);
        userDao.deleteEntity(user);
    }
}
