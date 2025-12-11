package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {
    private static List<User> userList;
    private InMemoryUserRepository repository = new InMemoryUserRepository();

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>(List.of(
                new User(UUID.randomUUID(),"Pau","pau@pau.com"),
                new User(UUID.randomUUID(),"Jose","jose@jose.com"),
                new User(UUID.randomUUID(),"Abel","abel@abel.com")
        ));
    }

    @Test
    void saveSuccessful() {
        User user = new User(UUID.randomUUID(),"Tron", "tron@tron.com");
        assertEquals(user, repository.save(user));
    }

    @Test
    void findAllReturnsAll() {
        assertEquals(3  ,repository.findAll());
    }

    @Test
    void findById() {
    }

    @Test
    void searchByName() {
    }

    @Test
    void existsByEmail() {
    }
}