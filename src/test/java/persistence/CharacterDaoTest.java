package persistence;

import entity.Character;
import entity.Story;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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
        // Unique user
        String uname = "adminUser_" + UUID.randomUUID();
        User user = new User(uname, "password123", uname + "@example.com", true);
        int userId = userDao.insert(user);

        // Story
        Story story = new Story("Murder at the Mansion", "A mystery unfolds", "Victorian estate", user);
        int storyId = storyDao.insert(story);

        // Character (attach to story to satisfy NOT NULL story_id)
        Character character = new Character(
                "Detective Jane",
                "detective",
                "Skilled investigator with a record of success.",
                false,
                "https://example.com/jane.png",
                story
        );
        int characterId = characterDao.insert(character);

        // Verify
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
