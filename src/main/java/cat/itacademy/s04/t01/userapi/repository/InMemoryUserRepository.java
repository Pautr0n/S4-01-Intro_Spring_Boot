package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static List<User> userList = new ArrayList<>();

    public InMemoryUserRepository(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public User save(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userList);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userList.stream().filter(u->u.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> searchByName(String name) {
        return userList.stream()
                .filter(u->u.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userList.stream()
                .anyMatch(u->u.getEmail().equalsIgnoreCase(email));
    }
}
