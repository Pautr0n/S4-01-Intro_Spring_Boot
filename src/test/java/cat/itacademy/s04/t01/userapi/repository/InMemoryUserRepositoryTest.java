package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {
    private InMemoryUserRepository repository;
    private List<User> userList;
    @BeforeEach
    void setUp() {
        userList = new ArrayList<>(List.of(
                new User(UUID.randomUUID(),"Pau","pau@pau.com"),
                new User(UUID.randomUUID(),"Jose","jose@jose.com"),
                new User(UUID.randomUUID(),"Abel","abel@abel.com")
        ));

        repository =new InMemoryUserRepository(userList);

    }

    @Test
    void saveSuccessful() {
        User user = new User(UUID.randomUUID(),"Tron", "tron@tron.com");
        assertEquals(user, repository.save(user));
    }

    @Test
    void findAllReturnsAll() {
        assertEquals(3  ,repository.findAll().size());
    }

    @Test
    void findByIdReturnsUser() {
        UUID id = userList.getFirst().getId();
        assertSame(userList.get(1),repository.findById(id).get());

    }

    @Test
    void searchByName() {
    }

    @Test
    void existsByEmail() {
    }
}