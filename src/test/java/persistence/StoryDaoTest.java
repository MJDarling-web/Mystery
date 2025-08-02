package persistence;

import entity.Story;
import org.junit.jupiter.api.*;
import persistence.GenericDao;

import static org.junit.jupiter.api.Assertions.*;

class StoryDaoTest {

    private GenericDao<Story> storyDao;

    @BeforeEach
    void setUp() {
        storyDao = new GenericDao<>(Story.class);
    }

    @Test
    void insertAndGetClue() {
        Story story = new Story(
                "Murder at a Mansion",
                "A ghostly haunted mansion in salsburg Virgina two days before christmas",
                101,
                100,
                "Winter"
        );

        int id = storyDao.insert(story);
        Story retrieved = storyDao.getById(id);

        assertNotNull(retrieved);
        assertEquals("Murder at a Mansion", retrieved.getTitle());
        assertEquals("A ghostly haunted mansion in salsburg Virgina two days before christmas", retrieved.getDescription());
        assertEquals(101, retrieved.getCreatedByUserId());
        assertEquals(100, retrieved.getCreatedAt());
        assertEquals("Winter", retrieved.getSetting());

        //cleaning up the test after each test:
        storyDao.deleteEntity(retrieved);
    }

}
