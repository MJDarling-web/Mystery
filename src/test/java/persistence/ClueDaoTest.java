package persistence;

import entity.Clue;
import org.junit.jupiter.api.*;
import persistence.GenericDao;

import static org.junit.jupiter.api.Assertions.*;

class ClueDaoTest {

    private GenericDao<Clue> clueDao;

    @BeforeEach
    void setUp() {
        clueDao = new GenericDao<>(Clue.class);
    }

    @Test
    void insertAndGetClue() {
        Clue clue = new Clue(
                "dull knife",
                "www.image.com",
                false,
                "Living room"
        );

        int id = clueDao.insert(clue);
        Clue retrieved = clueDao.getById(id);

        assertNotNull(retrieved);
        assertEquals("dull knife", retrieved.getDescription());
        assertEquals("www.image.com", retrieved.getImage());
        assertFalse(retrieved.isFound());
        assertEquals("Living room", retrieved.getLocationFound());

        //cleaning up the test after each test:
        //clueDao.deleteEntity(retrieved);
    }

}
