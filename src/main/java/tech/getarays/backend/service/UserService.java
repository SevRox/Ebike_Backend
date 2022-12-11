package tech.getarays.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarays.backend.NotFoundExeption;
import tech.getarays.backend.model.User;
import tech.getarays.backend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundExeption("User by id: " + id + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Long deleteUserByLogin(String login){
        return userRepository.deleteBylogin(login);
    }

    public List<User> findUserByLogin(String login) {
        return userRepository.findAllBylogin(login);
    }
}

