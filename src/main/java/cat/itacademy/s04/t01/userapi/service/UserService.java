package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    List<User> findAll();
    List<User> searchByName(String name);
    User findById(UUID id);
    User existByEmail(String email);


}
