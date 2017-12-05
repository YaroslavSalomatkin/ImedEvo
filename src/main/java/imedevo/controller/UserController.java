package imedevo.controller;

import imedevo.util.ErrorBody;
import imedevo.util.NoSuchUserException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import imedevo.model.User;
import imedevo.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Optional<User> mayBeUser = userService.getById(id);

        return mayBeUser.map(Object.class::cast)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest()
                        .body(new ErrorBody("there is no user with ID = " + id)));
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        User saved = userService.save(user);

        return ResponseEntity.created(URI.create("/users/" + saved.getId())).build();
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Integer id,
                           @RequestBody User user) {

        user.setId(id);
        userService.save(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {

        userService.delete(id)
                .orElseThrow(NoSuchUserException::new);

    }
}