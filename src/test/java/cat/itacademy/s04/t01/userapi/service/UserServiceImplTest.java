package cat.itacademy.s04.t01.userapi.service;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void createUserSuccesful(){
        User user = new User(null, "Tron", "tron@tron.com");
        assertInstanceOf(user.getClass(), userService.createUser(user));
    }

    @Test
    void findAllUsersReturnsThree(){
        assertEquals(3,userService.findAll().size());
        assertArrayEquals(userList.toArray(),userService.findAll().toArray());
    }

}