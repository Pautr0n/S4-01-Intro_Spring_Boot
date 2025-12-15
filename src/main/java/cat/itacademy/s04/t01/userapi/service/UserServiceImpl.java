package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userRequest) {

        UUID id = UUID.randomUUID();
        User newUser = new User(id, userRequest.getName(), userRequest.getEmail());
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchByName(String name) {
        if (name == null || name.isBlank()){
            throw new NullPointerException("Cannot provide null or empty string");
        }
        return userRepository.searchByName(name);
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public User existByEmail(String email) {
        return null;
    }
}
