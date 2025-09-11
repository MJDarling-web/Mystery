package persistence;

import entity.Scene;
import entity.Story;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SceneDaoTest {

    private GenericDao<Scene> sceneDao;
    private GenericDao<Story> storyDao;
    private GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        sceneDao = new GenericDao<>(Scene.class);
        storyDao = new GenericDao<>(Story.class);
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void insertAndGetScene() {
        // Unique user
        String uname = "adminUser_" + UUID.randomUUID();
        User user = new User(uname, "password123", uname + "@example.com", true);
        int userId = userDao.insert(user);

        // Story
        Story story = new Story("The Final Hour", "Climactic confrontation", "Rooftop", user);
        int storyId = storyDao.insert(story);

        // Scene
        Scene scene = new Scene();
        scene.setTitle("Showdown");
        scene.setDescription("The detective confronts the suspect.");
        scene.setImageUrl("https://example.com/showdown.jpg");
        scene.setDisplayOrder(1);
        scene.setActive(true);
        scene.setStory(story);

        int sceneId = sceneDao.insert(scene);
        Scene retrieved = sceneDao.getById(sceneId);

        assertNotNull(retrieved);
        assertEquals("Showdown", retrieved.getTitle());
        assertEquals("The detective confronts the suspect.", retrieved.getDescription());
        assertEquals("https://example.com/showdown.jpg", retrieved.getImageUrl());
        assertEquals(1, retrieved.getDisplayOrder());
        assertTrue(retrieved.isActive());
        assertEquals(storyId, retrieved.getStory().getId());

        // Clean up
        sceneDao.deleteEntity(retrieved);
        storyDao.deleteEntity(story);
        userDao.deleteEntity(user);
    }
}
