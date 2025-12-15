package cat.itacademy.s04.t01.userapi.service;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private List<User> userList;

    @BeforeEach
    void setUp(){
        userList = new ArrayList<>(List.of(
                new User(UUID.randomUUID(),"Pau","pau@pau.com"),
                new User(UUID.randomUUID(),"Jose","jose@jose.com"),
                new User(UUID.randomUUID(),"Abel","abel@abel.com")
        ));

        UserRepository userRepository = new InMemoryUserRepository(userList);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUserSuccessful(){
        User user = new User(null, "Tron", "tron@tron.com");
        assertInstanceOf(user.getClass(), userService.createUser(user));
    }

    @Test
    void findAllUsersReturnsThree(){
        assertEquals(3,userService.findAll().size());
        assertArrayEquals(userList.toArray(),userService.findAll().toArray());
    }

    @Test
    void findUserByNameSuccessful(){
        assertEquals(1, userService.searchByName("pau").size());
        assertEquals(2, userService.searchByName("a").size());
    }

    @Test
    void findUserByEmptyNameThrowsException(){
        assertThrows(NullPointerException.class, ()-> userService.searchByName(""));
    }

    @Test
    void findUserByNullNameThrowsException(){
        assertThrows(NullPointerException.class, ()->userService.searchByName(null));
    }

    @Test
    void findUserByIdFindsUser(){
        UUID userId = userList.get(0).getId();
        Optional<User> result = userService.findById(userId);
        assertTrue(result.isPresent());
        assertEquals("Pau", result.get().getName());
    }
    @Test
    void findUserByIdReturnsEmpty(){
        UUID id = UUID.randomUUID();
        Optional<User> result = userService.findById(id);
        assertFalse(result.isPresent());
    }

    @Test
    void existByEmailReturnsTrue(){
        assertTrue(userService.existByEmail("pau@pau.com"));
    }
    @Test
    void existByEmailReturnsFalse(){
        assertFalse(userService.existByEmail("falso@falso.com"));
    }

}