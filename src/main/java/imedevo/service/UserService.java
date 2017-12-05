package imedevo.service;

import imedevo.model.User;
import imedevo.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public List<User> getAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> delete(Integer id) {
        return userRepository.delete(id);
    }

}
