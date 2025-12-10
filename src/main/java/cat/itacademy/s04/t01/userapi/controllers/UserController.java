package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static List<User> userList = new ArrayList<>();

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String name){
        if(name == null || name.isBlank()) return userList;
        return userList.stream().
                filter(u->u.getName() != null &&
                        u.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
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
