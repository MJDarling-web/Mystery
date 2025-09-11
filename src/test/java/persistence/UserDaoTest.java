package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao<>(User.class);
    }

    @Test
    void insertAndGetUser() {
        String uname = "testUser_" + UUID.randomUUID();
        User user = new User(uname, "securePassword", uname + "@example.com", false);
        int userId = userDao.insert(user);

        User retrieved = userDao.getById(userId);

        assertNotNull(retrieved);
        assertEquals(uname, retrieved.getUsername());
        assertEquals("securePassword", retrieved.getPassword());
        assertEquals(uname + "@example.com", retrieved.getEmail());
        assertFalse(retrieved.isAdmin());
        assertEquals(userId, retrieved.getId());

        // Clean up
        userDao.deleteEntity(retrieved);
    }
}
