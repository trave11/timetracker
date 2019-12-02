package com.timetracker.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("User with " + id + " id not found!"));

        existingUser.setName(user.getName());
        existingUser.setProjects(user.getProjects());
        userRepository.save(existingUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
