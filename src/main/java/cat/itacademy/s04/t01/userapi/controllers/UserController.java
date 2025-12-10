package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static List<User> userList = new ArrayList<>();

    @GetMapping
    public List<User> getUsers(){
        return userList;
    }

    @PostMapping
    public User insertUser(@RequestBody User userRequest){
        UUID id = UUID.randomUUID();

        User newUser = new User(id, userRequest.getName(), userRequest.getEmail());

        userList.add(newUser);

        return newUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return userList.stream().filter(u->u.getId().equals(id)).
                findFirst().orElseThrow(()->new UserNotFoundException("NotFound(404)"));
    }

}
