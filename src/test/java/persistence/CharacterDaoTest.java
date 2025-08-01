package persistence;

import entity.Character;
import org.junit.jupiter.api.*;
import persistence.GenericDao;

import static org.junit.jupiter.api.Assertions.*;

class CharacterDaoTest {

    private GenericDao<Character> characterDao;

    @BeforeEach
    void setUp() {
        characterDao = new GenericDao<>(Character.class);
    }

    @Test
    void insertAndGetCharacter() {
        Character character = new Character(
                "Detective Jane",
                "detective",
                "At the station",
                "A veteran with a sharp eye for detail.",
                false
        );

        int id = characterDao.insert(character);
        Character retrieved = characterDao.getById(id);

        assertNotNull(retrieved);
        assertEquals("Detective Jane", retrieved.getName());
        assertEquals("detective", retrieved.getRole());
        assertFalse(retrieved.isGuilty());

        //cleaning up the test after each test:
        characterDao.deleteEntity(retrieved);
    }

}
